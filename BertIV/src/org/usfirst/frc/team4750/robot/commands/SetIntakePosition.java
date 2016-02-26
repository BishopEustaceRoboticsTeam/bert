package org.usfirst.frc.team4750.robot.commands;

import org.usfirst.frc.team4750.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetIntakePosition extends Command{

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		requires(Robot.shooter);
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		Robot.shooter.setShooterAimerMotorSpeed(0.5);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Robot.shooter.getLowerLimitSwitch();
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.shooter.setShooterAimerMotorSpeed(0);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}

}
