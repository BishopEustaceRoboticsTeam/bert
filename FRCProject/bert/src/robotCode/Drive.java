package robotCode;
//This class will handle moving the robot

//imports
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive {
	
	final double WHEEL_DIAMETER = 0.101; // in meters (4 inches)
	final double PI = 3.14159;
	final double TICKS_PER_REVOLUTION = 250; //the number of ticks the encoder gets per one revolution
	final double DIST_PER_TICK_LEFT = 0.0013245033;  //0.0012692034;//(WHEEL_DIAMETER * PI)/ TICKS_PER_REVOLUTION;//Circumference/ number of ticks in one revolution
	final double DIST_PER_TICK_RIGHT = 0.0013831258; //0.0012692034;
	//883 ticks for 90 degree turn with just one motor
	
	//create the sensor vars
	Encoder leftEncoder = new Encoder(RobotValues.LEFT_ENCODER_A,  RobotValues.LEFT_ENCODER_B, RobotValues.REVERSE_LEFT_ENCODER_DIRECTION, EncodingType.k2X);
	Encoder rightEncoder = new Encoder(RobotValues.RIGHT_ENCODER_A, RobotValues.RIGHT_ENCODER_B, RobotValues.REVERSE_RIGHT_ENCODER_DIRECTION, EncodingType.k2X);
	
	//global class vars go here
	
	//375 ticks per wheel for 90 degree turn with both motors
	final int TICKS_PER_90_RIGHT = 384;
	final int TICKS_PER_90_LEFT = 385;
	
	final int WHEEL_RADIUS = 3; //in inhes
	final double pi = 3.1459;
	F310 remote;
	Talon frMotor, flMotor, brMotor, blMotor; 
	
	
	//create a drive states variable
	private States.Drive currentDriveState = States.Drive.CONTROLLER_DRIVE; 
	
	//vars for rightAngleTurn()
	private boolean isLeft = true;
	
	//vars for distanceDrive():
		//hold the distance the user want to drive to
		private double driveDistance = 0;
		//hold the current distance of the robot
		private double currentDistance = 0;
		//the min speed of the robot
		private final double minSpeed = 0.5; //(must be between 0 - 1)
		//the constant for the proportional controller to be multiplied 
		private double Pc = 2;
		//the integral constant to multiply by the sum of the errors
		private final double Ic = 0; //zero right now because we are not using
		//the current error between the two motors
		private double currentError = 0;
		//the sum of all the error over time
		private double totalError = 0;
		//the value between -.25 and .25 that is added to the motors
		private double motorDifferential;
		
		
	//tells when the current state has finished
	private boolean stateCompleted = false;
	
	private RobotDrive driver;
	
	public Drive(F310 _rc){
		//constructor
		frMotor = new Talon(RobotValues.FRONT_RIGHT_MOTOR);
		flMotor = new Talon(RobotValues.FRONT_LEFT_MOTOR);
		brMotor = new Talon(RobotValues.BACK_RIGHT_MOTOR);
		blMotor = new Talon(RobotValues.BACK_LEFT_MOTOR);
		remote = _rc;
		driver = new RobotDrive(flMotor, blMotor, frMotor, brMotor);
		driver.setSafetyEnabled(false);
		leftEncoder.setDistancePerPulse(DIST_PER_TICK_LEFT);
		rightEncoder.setDistancePerPulse(DIST_PER_TICK_RIGHT);
		SmartDashboard.putNumber("P:", Pc);
		
	}
	public void setsafety(boolean enable){
		driver.setSafetyEnabled(enable);
	}
	private void notCompleted(){
		stateCompleted = false;
	}
	private void completed(){ 
		stateCompleted = true;
		currentDriveState = States.Drive.CONTROLLER_DRIVE;
		
	}
	public boolean Done(){
		return stateCompleted;
	}

	
	//this will be called in the main loop for both telop and auton
	public void update(){
		
		switch(currentDriveState){
			case CONTROLLER_DRIVE:
				controllerDrive();
				break;
			case DISTANCE_DRIVE:
				distanceDrive();
				break;
			case RIGHT_ANGLE_TURN:
				rightAngleTurn();
				break;
			case OVERRIDE:
				//override give the user control again
				startControllerDrive();
				break;
				
		}
		
	}
	
	public void startControllerDrive(){
		currentDriveState = States.Drive.CONTROLLER_DRIVE;
		//set the stateCompleted variable to true to allow for a new state.
		completed();
	}
	
	//controlleDrive method that needs to be called in teleop periodic
	//it will drive the robot using the f310 remote
		private void controllerDrive(){
     		driver.arcadeDrive(-remote.getLeftStickY(), -remote.getLeftStickX());
		}

	
	//allow the user to override all other states to get control of
	//the robot in the event of a disaster 
	public void override(){
		currentDriveState = States.Drive.OVERRIDE;
		notCompleted();
	}

	
	//method that will drive the robot straight using encoders
	//it should only be called once and not in a loop
	public void startDistanceDrive(double distance){
		notCompleted();
		resetEncoders();
		currentDriveState = States.Drive.DISTANCE_DRIVE;
		driveDistance = distance;
		
		Pc = SmartDashboard.getNumber("P:");
		//reset all the vars needed
		totalError = 0;
		
		
	}
	
	//this method is internal to the drive class and will get called
	//in the update if distance drive is true
	private void distanceDrive(){
		//code to control distance drive
		
		//calculate the current distance by taking the average of the 
		//the two motors. TODO: do a more accurate calculation of the distance
		currentDistance = (getLeftEncoderDistance() + getRightEncoderDistance())/2;
		SmartDashboard.putNumber("Distance: ", currentDistance);
		
		printEncoderValues();
		//check to see if the current distance has reach the desired distance
		if(currentDistance >= driveDistance){
			completed();
		}
		else{
			//calculate the current error
			currentError = getLeftEncoderDistance() - getRightEncoderDistance();
			SmartDashboard.putNumber("Current error: ", currentError);
		
			//added the current error to the total error;
			totalError += currentError;
		
			//calculate the motorDiffernetial
			//the current error time the proportional const + the total error times the intergral const 
			motorDifferential = currentError*Pc; //+ totalError*Ic;
		
			//make sure the the motorDifferential plus the min motor speed 
			//is not over the max motor value of 1
			
			//for dubuging purposes put the motordiff on the smartdash
			SmartDashboard.putNumber("Motor Diff: ", motorDifferential);
			
			
			if(Math.abs((motorDifferential) + minSpeed) > 1){
				if(motorDifferential < 0){
					motorDifferential = -(1-minSpeed);
				}
				else{
					motorDifferential = (1-minSpeed);
				}
			}
			
			//for dubuging purposes put the motordiff on the smartdash
			//set the motor to the speeds to what 
			driver.tankDrive((minSpeed - motorDifferential), (minSpeed + motorDifferential));
			SmartDashboard.putNumber("LEFT Motor speed: ", (minSpeed - motorDifferential));
			SmartDashboard.putNumber("RIGHT Motor speed: ", (minSpeed + motorDifferential));
		}
	}
		
		
	public void startRightAngleTurn(boolean left){
		//turn off all states
		notCompleted();
		resetEncoders();
		currentDriveState = States.Drive.RIGHT_ANGLE_TURN;
		isLeft = left;
	}
	
	//rightAngleTurn(var leftOrRight) //this will use the encoders to make
	//a perfect right angle turn //need to decide what var type to use. enum? int? boolean
	private void rightAngleTurn(){
		//leftTurn
		if (isLeft){
			if(getLeftEncoderCount() > -TICKS_PER_90_LEFT && getRightEncoderCount() < TICKS_PER_90_LEFT){
				driver.arcadeDrive(0, .4);
				printEncoderValues();
			}
			else{
				//where done turning
				completed();
			}
		}
		
		//rightTurn
		else{
			
			if(getLeftEncoderCount() < TICKS_PER_90_RIGHT && getRightEncoderCount() > -TICKS_PER_90_RIGHT){
				driver.arcadeDrive(0, -.4);
				printEncoderValues();
			}
			else{
				//where done turning
				completed();
			}
		}
		
		
	}
	
	//drive all motors at the same speed
	public void debugDrive(double speed){
		driver.drive(speed, 0);
	}
	//turn(int degree) //degree is 0-360 that will turn the robot using the encoders
	//to the specified angle from the start position. counter clockwise! (90 is to the left)

	//function to reset both encoders
	public void resetEncoders(){
		//this will reset the distances tracked by the encoders
		leftEncoder.reset();
		rightEncoder.reset();
	}
	//function to get left encoder distance
	private double getLeftEncoderDistance(){
		return leftEncoder.get()* DIST_PER_TICK_LEFT;
	}
	//function to get right encoder distance
	private double getRightEncoderDistance(){
		return rightEncoder.get()* DIST_PER_TICK_RIGHT;
	}
	//function to get left ticks
	private int getLeftEncoderCount(){
		return leftEncoder.get();
	}
	//function to get right ticks
	private int getRightEncoderCount(){
		return rightEncoder.get();
	}
	//function to print out encoder values
	private void printEncoderValues(){
		SmartDashboard.putNumber("Left Encoder", getLeftEncoderCount());
		SmartDashboard.putNumber("Right Encoder", getRightEncoderCount());
		SmartDashboard.putNumber("Left Encoder dist", getLeftEncoderDistance());
		SmartDashboard.putNumber("Right Encoder dist", getRightEncoderDistance());
	}
	
}
