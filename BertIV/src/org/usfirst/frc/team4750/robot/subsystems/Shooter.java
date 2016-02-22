package org.usfirst.frc.team4750.robot.subsystems;

import org.usfirst.frc.team4750.robot.RobotValues;
import org.usfirst.frc.team4750.robot.commands.ManualAim;
import org.usfirst.frc.team4750.robot.commands.SetAimAngle;
import org.usfirst.frc.team4750.robot.commands.Shoot;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem{

	Victor leftShooterMotor = new Victor(RobotValues.LEFT_SHOOTER_MOTOR_PORT);
	Victor rightShooterMotor = new Victor(RobotValues.RIGHT_SHOOTER_MOTOR_PORT);
	Victor shooterAimerMotor = new Victor(RobotValues.SHOOTER_AIMER_MOTOR_PORT);
	
	Servo shooterServo = new Servo(RobotValues.SHOOTER_SERVO_PORT);
	
	
	public Shooter() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new SetAimAngle());
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
	
	public void setShooterServo(boolean position){
		//This is just an abbreviated if-statement. The format is: (condition) ? (value if true) : (value if false)
		//0 is the extended position on the servo; 1 is fully retracted.
		shooterServo.set(position ? 0 : 1);
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
