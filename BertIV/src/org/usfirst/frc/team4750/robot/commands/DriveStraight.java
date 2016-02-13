package org.usfirst.frc.team4750.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

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
		Robot.driveTrain.straightDrive(dist);
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
