package org.usfirst.frc.team4750.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4750.robot.Robot;
import org.usfirst.frc.team4750.robot.RobotValues;
import org.usfirst.frc.team4750.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.Timer;

public class Drive extends Command {
	
	Timer timer = new Timer();
	double leftSpeed, rightSpeed;
	double driveTime;
	
	public Drive(double leftSpeed, double rightSpeed, double driveTime){
		requires(Robot.driveTrain);
		this.leftSpeed = leftSpeed;
		this.rightSpeed = rightSpeed;
		this.driveTime = driveTime;
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		timer.start();
		Robot.driveTrain.setLeftDriveMotor(leftSpeed);
		Robot.driveTrain.setRightDriveMotor(rightSpeed);		
	}

	@Override
	protected void execute() { }

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		if(timer.get() > driveTime){
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		//SmartDashboard.putBoolean("Is DriveStraight executing?", false);
		Robot.driveTrain.setDriveMotors(0);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}

}
