package org.usfirst.frc.team4750.robot.commands;

import org.usfirst.frc.team4750.robot.Robot;
import org.usfirst.frc.team4750.robot.RobotValues;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SetShooterSpeed extends Command {
	
	int direction;
	
	public SetShooterSpeed(boolean direction) {
		//  TODO Auto-generated constructor stub
		requires(Robot.shooter);
		if(direction){
			this.direction = 1;
		} else{
			this.direction = -1;
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
		//direction is either 1 or -1; 1 will make the motors shoot, -1 will reverse their direction (intake).
		if (direction == 1){
		Robot.shooter.setLeftShooterMotorSpeed(direction * RobotValues.SHOOTER_SPEED_OUT);
		Robot.shooter.setRightShooterMotorSpeed(direction * -RobotValues.SHOOTER_SPEED_OUT);
		}
		if (direction == -1){
			Robot.shooter.setLeftShooterMotorSpeed(direction * RobotValues.SHOOTER_SPEED_IN);
			Robot.shooter.setRightShooterMotorSpeed(direction * -RobotValues.SHOOTER_SPEED_IN);
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
