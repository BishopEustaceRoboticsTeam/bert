package robotCode;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;

public class RobotIO {
	
	
	final double WHEEL_DIAMETER = 0.101; // in meters (4 inches)
	final double PI = 3.14159;
	final double TICKS_PER_REVOLUTION = 250; //the number of ticks the encoder gets per one revolution
	final double DIST_PER_TICK = 0.0012692034;//(WHEEL_DIAMETER * PI)/ TICKS_PER_REVOLUTION;//Circumference/ number of ticks in one revolution
	//883 ticks for 90 degree turn with just one motor
	
	//create the sensor vars
	Encoder leftEncoder = new Encoder(RobotValues.LEFT_ENCODER_A,  RobotValues.LEFT_ENCODER_B, RobotValues.REVERSE_LEFT_ENCODER_DIRECTION, EncodingType.k2X);
	Encoder rightEncoder = new Encoder(RobotValues.RIGHT_ENCODER_A, RobotValues.RIGHT_ENCODER_B, RobotValues.REVERSE_RIGHT_ENCODER_DIRECTION, EncodingType.k2X);
	
	public void RobotIO(){
		
		
		leftEncoder.setDistancePerPulse(DIST_PER_TICK);
		rightEncoder.setDistancePerPulse(DIST_PER_TICK);
		
	}
	
	//function to reset both encoders
	public void resetEncoders(){
		//this will reset the distances tracked by the encoders
		leftEncoder.reset();
		rightEncoder.reset();
	}
	//function to get left encoder distance
	public double getLeftEncoderDistance(){
		return leftEncoder.get()* DIST_PER_TICK;
	}
	//function to get right encoder distance
	public double getRightEncoderDistance(){
		return rightEncoder.get()* DIST_PER_TICK;
	}
	//function to get left ticks
	public int getLeftEncoderCount(){
		return leftEncoder.get();
	}
	//function to get right ticks
	public int getRightEncoderCount(){
		return rightEncoder.get();
	}
	//function to print out encoder values
	public void printEncoderValues(){
		SmartDashboard.putNumber("Left Encoder", getLeftEncoderCount());
		SmartDashboard.putNumber("Right Encoder", getRightEncoderCount());
		SmartDashboard.putNumber("Left Encoder dist", getLeftEncoderDistance() * DIST_PER_TICK);
		SmartDashboard.putNumber("Right Encoder dist", getRightEncoderDistance()* DIST_PER_TICK);
	}
	
	/*
	 * SmartDashboard.putNumber("PDP Current (port 1)", pdp.getCurrent(1));
    	SmartDashboard.putNumber("PDP Power (watts)", pdp.getTotalPower());
    	SmartDashboard.putNumber("PDP Voltage (volts)", pdp.getVoltage());
    	SmartDashboard.putNumber("PDP Energy (joules)", pdp.getTotalEnergy());
    	
	 * 
	 * 
	 * */
	
}
