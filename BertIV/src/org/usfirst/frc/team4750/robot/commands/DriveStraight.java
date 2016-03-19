package org.usfirst.frc.team4750.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4750.robot.Robot;
import org.usfirst.frc.team4750.robot.RobotValues;
import org.usfirst.frc.team4750.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.Timer;

public class DriveStraight extends Command {
	
	Timer timer = new Timer(); 
	
	public DriveStraight(){
		requires(Robot.driveTrain);
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		timer.start();
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		SmartDashboard.putBoolean("Is DriveStraight executing?", true);
		Robot.driveTrain.setDriveMotors(-1);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		if(timer.get() > RobotValues.DRIVE_TIME){
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		SmartDashboard.putBoolean("Is DriveStraight executing?", false);
		Robot.driveTrain.setDriveMotors(0);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}

}
