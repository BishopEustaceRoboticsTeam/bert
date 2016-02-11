package org.usfirst.frc.team4750.robot.subsystems;

import org.usfirst.frc.team4750.robot.RobotValues;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem{

	Talon leftShooterMotor = new Talon(RobotValues.LEFT_SHOOTER_MOTOR_PORT);
	Talon rightShooterMotor = new Talon(RobotValues.RIGHT_SHOOTER_MOTOR_PORT);
	Talon shooterAimerMotor = new Talon(RobotValues.SHOOTER_AIMER_MOTOR_PORT);
	
	Servo shooterServo = new Servo(RobotValues.SHOOTER_SERVO_PORT);
	
	
	public Shooter() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
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
	
	public void setShooterServo(boolean position){
		if(position){
			shooterServo.set(RobotValues.SERVO_SHOOT_POS);
		}
		else{
			shooterServo.set(RobotValues.SERVO_DEFAULT_POS);
		}
	}
	
	
	//Future self, do these:
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
