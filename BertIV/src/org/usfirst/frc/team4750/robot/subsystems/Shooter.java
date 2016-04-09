package org.usfirst.frc.team4750.robot.subsystems;

import org.usfirst.frc.team4750.robot.RobotValues;
//import org.usfirst.frc.team4750.robot.commands.SetAimAngle;
import org.usfirst.frc.team4750.robot.commands.SetAimAngle;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem{

	Talon leftShooterMotor = new Talon(RobotValues.LEFT_SHOOTER_MOTOR_PORT);
	Talon rightShooterMotor = new Talon(RobotValues.RIGHT_SHOOTER_MOTOR_PORT);
	Victor shooterAimerMotor = new Victor(RobotValues.SHOOTER_AIMER_MOTOR_PORT);
	
//	DigitalInput verticalLimit = new DigitalInput(RobotValues.VERTICAL_LIMIT_PORT);
//	DigitalInput highGoalLimit = new DigitalInput(RobotValues.HIGH_GOAL_LIMIT_PORT);
//	DigitalInput lowGoalLimit = new DigitalInput(RobotValues.LOW_GOAL_LIMIT_PORT);
//	DigitalInput pickUpLimit = new DigitalInput(RobotValues.PICK_UP_LIMIT_PORT);
	
	DigitalInput switchPos1 = new DigitalInput(RobotValues.SWITCH_POS_1_PORT);
	DigitalInput switchPos2 = new DigitalInput(RobotValues.SWITCH_POS_2_PORT);
	DigitalInput switchPos3 = new DigitalInput(RobotValues.SWITCH_POS_3_PORT);
	
	private ShooterPos currentPos;
	private ShooterPos targetPos;
	
	public static enum ShooterPos{
		VERTICAL, HIGH_GOAL, LOW_GOAL, PICK_UP;
	}
	
	public Shooter() {
		// TODO Auto-generated constructor stub
		currentPos=ShooterPos.VERTICAL;
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		//setDefaultCommand(new SetAimAngle());
		//setDefaultCommand(new SetAimAngle());
	}
	
	public ShooterPos getCurrentPos(){
		return currentPos;
	}
	
	public void setCurrentPos(ShooterPos currentPos){
		this.currentPos = currentPos;
	}
	
	public ShooterPos getTargetPos(){
		return targetPos;
	}
	public void setTargetPos(ShooterPos targetPos){
		this.targetPos = targetPos;
	}
	
	public boolean getSwitchPos(ShooterPos shooterPos){
		boolean isActivated=false;
		switch(shooterPos){
			case VERTICAL:
				isActivated=switchPos1.get() && switchPos2.get() && !switchPos3.get();
				break;
			case HIGH_GOAL:
				isActivated=switchPos1.get() && !switchPos2.get() && !switchPos3.get();
				break;
			case LOW_GOAL:
				isActivated=switchPos1.get() && !switchPos2.get() && switchPos3.get();
				break;
			case PICK_UP:
				isActivated=!switchPos1.get() && !switchPos2.get() && switchPos3.get();
				break;
			
		}
		return isActivated;
	}
	
//	public boolean getIRSensor(ShooterPos shooterPos){
//		boolean isActivated=true;
//		
//		switch(shooterPos){
//			case VERTICAL:
//				isActivated=verticalLimit.get();
//				break;
//			case HIGH_GOAL:
//				isActivated=highGoalLimit.get();
//				break;
//			case LOW_GOAL:
//				isActivated=lowGoalLimit.get();
//				break;
//			case PICK_UP:
//				isActivated=pickUpLimit.get();
//				break;
//		}
//		
//		return !isActivated;
//	}
//	
	public void setLeftShooterMotorSpeed(double speed){
		leftShooterMotor.set(speed);
	}
	
	public void setRightShooterMotorSpeed(double speed){
		rightShooterMotor.set(speed);
	}
	
	public void setShooterAimerMotorSpeed(double speed){
		shooterAimerMotor.set(speed);
	}
		
	
	
	//Future self, implement these three:
	public int getLeftMotorEncoder(){
		return -1;
	}
	public int getRightMotorEncoder(){
		return -1;
	}
	public int getAimerGyro(){
		return -1;
	}
	
//	public boolean getUpperLimit(){
//		return !highGoalLimit.get();
//	}
//	
//	public boolean getLowerLimit(){
//		return !verticalLimit.get();
//	}
//	
}
