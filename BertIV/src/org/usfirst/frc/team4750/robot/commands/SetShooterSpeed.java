package org.usfirst.frc.team4750.robot.commands;

import org.usfirst.frc.team4750.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SetShooterSpeed extends Command {
	
	public SetShooterSpeed() {
		// TODO Auto-generated constructor stub
		requires(Robot.shooter);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		//sets shooter speed
		SmartDashboard.putBoolean("Is SetShooterSpeed executing?", true);
		Robot.shooter.setLeftShootSpeed(1);
		Robot.shooter.setRightShootSpeed(-1);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		//return Robot.oi.getLeftTwist()==0;
		return false;
	}

	@Override
	protected void end() {
		//stopping rollers when interrupted
		SmartDashboard.putBoolean("Is SetShooterSpeed executing?", false);
		Robot.shooter.setLeftShootSpeed(0);
		Robot.shooter.setRightShootSpeed(0);
	}

	@Override
	protected void interrupted() {
		//is called when button is released
		end();
	}

}
