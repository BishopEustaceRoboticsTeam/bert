package org.usfirst.frc.team4750.robot.commands;

import org.usfirst.frc.team4750.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ManualAim extends Command {

	public ManualAim() {
		requires(Robot.shooter);
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
		Robot.shooter.setLeftShooterMotorSpeed(Robot.oi.getLeftTwist());
		Robot.shooter.setRightShooterMotorSpeed(-Robot.oi.getLeftTwist());
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.shooter.setShooterAimerMotorSpeed(0);
		Robot.shooter.setLeftShooterMotorSpeed(0);
		Robot.shooter.setRightShooterMotorSpeed(0);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
