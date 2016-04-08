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
	Talon shooterAimerMotor = new Talon(RobotValues.SHOOTER_AIMER_MOTOR_PORT);
	DigitalInput lowerLimit = new DigitalInput(RobotValues.LOWER_LIMIT_PORT);
	DigitalInput upperLimit = new DigitalInput(RobotValues.UPPER_LIMIT_PORT);
	
	public Shooter() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		//setDefaultCommand(new SetAimAngle());
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
	
	public boolean getUpperLimit(){
		return !upperLimit.get();
	}
	
	public boolean getLowerLimit(){
		return !lowerLimit.get();
	}
	
}
