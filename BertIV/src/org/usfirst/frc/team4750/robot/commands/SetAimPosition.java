package org.usfirst.frc.team4750.robot.commands;

import org.usfirst.frc.team4750.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.DigitalInput;

public class SetAimPosition extends Command {

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		requires(Robot.shooter);
		
	}

	protected void execute() {
		// TODO Auto-generated method stub
		Robot.shooter.setShooterAimerMotorSpeed(-0.5);
	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Robot.shooter.getUpperLimitSwitch();
	}

	protected void end() {
		// TODO Auto-generated method stub
		Robot.shooter.setShooterAimerMotorSpeed(0.5);
	}

	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}
}
