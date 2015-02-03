package robotCode;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;

public class RobotIO {
	
	
	final int WHEEL_DIAMETER = 4; //in inches
	final double PI = 3.14159;
	final double TICKS_PER_REVOLUTION = 250;
	double DIST_PER_TICK = (WHEEL_DIAMETER * PI)/ TICKS_PER_REVOLUTION;
	//883 for just one motor
	
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
		return leftEncoder.getDistance();
	}
	//function to get right encoder distance
	public double getRightEncoderDistance(){
		return rightEncoder.getDistance();
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
		SmartDashboard.putNumber("Left Encoder", getLeftEncoderCount());;
		SmartDashboard.putNumber("Right Encoder", getRightEncoderCount());
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
