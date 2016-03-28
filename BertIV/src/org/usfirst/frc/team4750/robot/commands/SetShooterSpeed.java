package org.usfirst.frc.team4750.robot.commands;

import org.usfirst.frc.team4750.robot.CommandParameters;
import org.usfirst.frc.team4750.robot.Robot;
import org.usfirst.frc.team4750.robot.RobotValues;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SetShooterSpeed extends Command {
	
	boolean direction;
	
	public SetShooterSpeed(CommandParameters.RollerDirection direction) {
		//  TODO Auto-generated constructor stub
		requires(Robot.shooter);
		switch(direction){
			case OUT:
				this.direction = true;
				break;
			case IN:
				this.direction = false;
				break;
		}
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		SmartDashboard.putBoolean("Is SetShooterSpeed executing?", true);
		if (direction){
			Robot.shooter.setLeftShooterMotorSpeed(RobotValues.SHOOTER_SPEED_OUT);
			Robot.shooter.setRightShooterMotorSpeed(-RobotValues.SHOOTER_SPEED_OUT);
		} else {
			Robot.shooter.setLeftShooterMotorSpeed(RobotValues.SHOOTER_SPEED_IN);
			Robot.shooter.setRightShooterMotorSpeed(-RobotValues.SHOOTER_SPEED_IN);
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		SmartDashboard.putBoolean("Is SetShooterSpeed executing?", false);
		Robot.shooter.setLeftShooterMotorSpeed(0);
		Robot.shooter.setRightShooterMotorSpeed(0);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}

}
