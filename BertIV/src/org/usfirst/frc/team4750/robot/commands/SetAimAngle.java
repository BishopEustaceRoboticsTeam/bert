package org.usfirst.frc.team4750.robot.commands;

import org.usfirst.frc.team4750.robot.Robot;

import org.usfirst.frc.team4750.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

public class SetAimAngle extends Command{
	
	public SetAimAngle() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		Robot.shooter.setShooterAimerMotorSpeed(Robot.oi.getRightTwist());
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
