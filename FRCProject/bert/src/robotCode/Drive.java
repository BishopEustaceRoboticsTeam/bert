package robotCode;
//This class will handle moving the robot

//imports
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

public class Drive {
	//global class vars go here
	
	//375 ticks per wheel for 90 degree turn with both motors
	final int TICKS_PER_90_RIGHT = 384;
	final int TICKS_PER_90_LEFT = 385;
	
	final int WHEEL_RADIUS = 3; //in inhes
	final double pi = 3.1459;
	F310 remote;
	Talon frMotor, flMotor, brMotor, blMotor; 
	RobotIO robotIO;
	
	//create a drive states variable
	private States.Drive currentDriveState = States.Drive.CONTROLLER_DRIVE; 
	
	//vars for rightAngleTurn()
	private boolean isLeft = true;
	
	//vars for distanceDrive()
	private double driveDistance = 0;
	
	//tells when the current state has finished
	private boolean stateCompleted = false;
	
	private RobotDrive driver;
	
	public Drive(F310 _rc, RobotIO _IO){
		//constructor
		frMotor = new Talon(RobotValues.FRONT_RIGHT_MOTOR);
		flMotor = new Talon(RobotValues.FRONT_LEFT_MOTOR);
		brMotor = new Talon(RobotValues.BACK_RIGHT_MOTOR);
		blMotor = new Talon(RobotValues.BACK_LEFT_MOTOR);
		remote = _rc;
		robotIO = _IO;
		driver = new RobotDrive(flMotor, blMotor, frMotor, brMotor);
		driver.setSafetyEnabled(false);
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
		robotIO.resetEncoders();
		currentDriveState = States.Drive.DISTANCE_DRIVE;
		driveDistance = distance;
		
	}
	
	//this method is internal to the drive class and will get called
	//in the update if distance drive is true
	private void distanceDrive(){
		//code to control distance drive
		//the drive distance is driveDistance;
		driver.tankDrive(0.75, 0.75);
		completed();
	}
		
		
	public void startRightAngleTurn(boolean left){
		//turn off all states
		notCompleted();
		robotIO.resetEncoders();
		currentDriveState = States.Drive.RIGHT_ANGLE_TURN;
		isLeft = left;
	}
	
	//rightAngleTurn(var leftOrRight) //this will use the encoders to make
	//a perfect right angle turn //need to decide what var type to use. enum? int? boolean
	private void rightAngleTurn(){
		//leftTurn
		if (isLeft){
			if(robotIO.getLeftEncoderCount() > -TICKS_PER_90_LEFT && robotIO.getRightEncoderCount() < TICKS_PER_90_LEFT){
				driver.arcadeDrive(0, .4);
				robotIO.printEncoderValues();
			}
			else{
				//where done turning
				completed();
			}
		}
		
		//rightTurn
		else{
			
			if(robotIO.getLeftEncoderCount() < TICKS_PER_90_RIGHT && robotIO.getRightEncoderCount() > -TICKS_PER_90_RIGHT){
				driver.arcadeDrive(0, -.4);
				robotIO.printEncoderValues();
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
	
}
