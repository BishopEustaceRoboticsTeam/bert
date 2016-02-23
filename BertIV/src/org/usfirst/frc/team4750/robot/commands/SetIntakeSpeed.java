package org.usfirst.frc.team4750.robot.commands;

import org.usfirst.frc.team4750.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetIntakeSpeed extends Command {

	public SetIntakeSpeed(){
		requires(Robot.shooter);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		Robot.shooter.setLeftShootSpeed(-0.5);
		Robot.shooter.setRightShootSpeed(0.5);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.shooter.setLeftShootSpeed(0);
		Robot.shooter.setRightShootSpeed(0);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}

}
