package org.usfirst.frc.team4750.robot.subsystems;

import org.usfirst.frc.team4750.robot.RobotValues;
//import org.usfirst.frc.team4750.robot.commands.SetAimAngle;
import org.usfirst.frc.team4750.robot.commands.SetAimAngle;
import org.usfirst.frc.team4750.robot.subsystems.Shooter.ShooterPos;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem{
	
	public enum ShooterPos{
		PICK_UP, LOW_GOAL, HIGH_GOAL, VERTICAL;
	}
	
	Talon leftShooterMotor = new Talon(RobotValues.LEFT_SHOOTER_MOTOR_PORT);
	Talon rightShooterMotor = new Talon(RobotValues.RIGHT_SHOOTER_MOTOR_PORT);
	Victor shooterAimerMotor = new Victor(RobotValues.SHOOTER_AIMER_MOTOR_PORT); //If we switch to Victors, make sure they are in brake mode.
	
	DigitalInput pickUpPosIRSensor = new DigitalInput(1);
	DigitalInput lowGoalPosIRSensor = new DigitalInput(2);
	DigitalInput highGoalPosIRSensor = new DigitalInput(3);
	DigitalInput verticalPosIRSensor = new DigitalInput(4);
	
	private ShooterPos targetPos;
	private ShooterPos currentPos;
	
	
	public Shooter() {
		// TODO Auto-generated constructor stub
		currentPos=ShooterPos.VERTICAL;
	}
	
	public ShooterPos getCurrentPos(){
		return currentPos;
	}
	
	public ShooterPos getTargetPos(){
		return targetPos;
	}
	
	public void setCurrentPos(ShooterPos currentPos){
		this.currentPos = currentPos;
	}
	
	public void setTargetPos(ShooterPos targetPos){
		this.targetPos = targetPos;
	}
	
	
	public boolean getIRSensor(ShooterPos shooterPos){
		boolean isSensorActivated = false;
		
		switch(shooterPos){
		 case VERTICAL:
				isSensorActivated = verticalPosIRSensor.get();
				break;
			case HIGH_GOAL:
				isSensorActivated = highGoalPosIRSensor.get();
				break;
			case LOW_GOAL:
				isSensorActivated = lowGoalPosIRSensor.get();
				break;
			case PICK_UP:
				isSensorActivated = pickUpPosIRSensor.get();
				break;
		}
		
		return !isSensorActivated;
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		//setDefaultCommand(new SetAimAngle());
	}

	
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
	
}
