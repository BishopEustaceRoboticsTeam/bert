
package org.usfirst.frc.team4750.robot.subsystems;

import org.usfirst.frc.team4750.robot.RobotValues;
import org.usfirst.frc.team4750.robot.commands.JoystickDrive;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Encoder;
/**
 *
 */
public class DriveTrain extends Subsystem {
    
	final double WHEEL_DIAMETER = 0.101; // in meters (4 inches)
	final double PI = 3.14159;
	final double TICKS_PER_REVOLUTION = 250; //the number of ticks the encoder gets per one revolution
	final double DIST_PER_TICK_LEFT = 0.0013245033;  //0.0012692034;//(WHEEL_DIAMETER * PI)/ TICKS_PER_REVOLUTION;//Circumference/ number of ticks in one revolution
	final double DIST_PER_TICK_RIGHT = 0.0013831258; //0.0012692034;
	//883 ticks for 90 degree turn with just one motor
	final double RIGHT_ANGLE_TURN_SPEED = 0.6;
	
	//create the sensor vars
	Encoder leftEncoder = new Encoder(RobotValues.LEFT_ENCODER_A,  RobotValues.LEFT_ENCODER_B, RobotValues.REVERSE_LEFT_ENCODER_DIRECTION, EncodingType.k2X);
	Encoder rightEncoder = new Encoder(RobotValues.RIGHT_ENCODER_A, RobotValues.RIGHT_ENCODER_B, RobotValues.REVERSE_RIGHT_ENCODER_DIRECTION, EncodingType.k2X);
	
	//global class vars go here
	
	//375 ticks per wheel for 90 degree turn with both motors
	final int TICKS_PER_90_RIGHT = 384;
	final int TICKS_PER_90_LEFT = 385;
	
	private final double DISTANCE_PER_90 = 0.47876; //0.6; //in meters
	
	final int WHEEL_RADIUS = 3; //in inches
	final double pi = 3.14159;
	
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
	
	Victor rightMotor = new Victor(RobotValues.RIGHT_MOTOR_PORT);
	Victor leftMotor = new Victor(RobotValues.LEFT_MOTOR_PORT);
	
	
	private RobotDrive driver = new RobotDrive(leftMotor, rightMotor);
	
	
	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	private double getRightEncoderDistance(){
		double distanceRight = rightEncoder.get() * DIST_PER_TICK_RIGHT;
		return distanceRight;
	}
	
	private double getLeftEncoderDistance(){
		double distanceLeft = leftEncoder.get() * DIST_PER_TICK_LEFT;
		return distanceLeft;
	}
	
	private void printEncoderValues(){
		
	}
	
	public void controllerDrive(Joystick driveStick){
		//driver.tankDrive(-left.getRawAxis(1), -right.getRawAxis(1));
		driver.arcadeDrive(-driveStick.getRawAxis(1), -driveStick.getRawAxis(0));
	}
	
	public void straightDrive(double driveDistance){
		currentDistance = (getLeftEncoderDistance() + getRightEncoderDistance())/2;
		SmartDashboard.putNumber("Distance: ", currentDistance);
		
		printEncoderValues();
		//check to see if the current distance has reach the desired distance
		if(currentDistance >= driveDistance){
			stop();
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
	
	private void rightTurn(){
		//rightTurn
		if(getLeftEncoderDistance() < DISTANCE_PER_90 && getRightEncoderDistance() > -DISTANCE_PER_90){
			driver.arcadeDrive(0, -RIGHT_ANGLE_TURN_SPEED);
			printEncoderValues();
		}
		
		else{
			//where done turning
			stop();
		}
	}
		
	private void leftTurn(){
		//leftTurn
		if(getLeftEncoderDistance() > -DISTANCE_PER_90 && getRightEncoderDistance() < DISTANCE_PER_90){
			driver.arcadeDrive(0, RIGHT_ANGLE_TURN_SPEED);
			printEncoderValues();
		}
		else{
			//where done turning
			stop();
		}
	}
	
	
	public void stop(){
		driver.drive(0,0);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new JoystickDrive());
    }
}

