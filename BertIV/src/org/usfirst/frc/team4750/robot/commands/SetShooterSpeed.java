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
		// TODO Auto-generated method stub
		SmartDashboard.putBoolean("Is SetShooterSpeed executing?", true);
		Robot.shooter.setLeftShooterMotorSpeed(Robot.oi.getLeftTwist());
		Robot.shooter.setRightShooterMotorSpeed(-Robot.oi.getLeftTwist());
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		//return Robot.oi.getLeftTwist()==0;
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		SmartDashboard.putBoolean("Is SetShooterSpeed executing?", false);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}

}
