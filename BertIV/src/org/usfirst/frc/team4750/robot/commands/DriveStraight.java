package org.usfirst.frc.team4750.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4750.robot.Robot;
import org.usfirst.frc.team4750.robot.subsystems.DriveTrain;

public class DriveStraight extends Command {
	
	private double dist;
	
	public DriveStraight(double distance){
		requires(Robot.driveTrain);
		dist = distance;
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		SmartDashboard.putBoolean("Is DriveStraight executing?", true);
		Robot.driveTrain.straightDrive(dist);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Robot.driveTrain.isDone();
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		SmartDashboard.putBoolean("Is DriveStraight executing?", false);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}

}
