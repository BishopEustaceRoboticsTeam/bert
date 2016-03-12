package org.usfirst.frc.team4750.robot.commands;

import org.usfirst.frc.team4750.robot.Robot;
import org.usfirst.frc.team4750.robot.RobotValues;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SetAimAngle extends Command{
	
	public SetAimAngle() {
		// TODO Auto-generated constructor stub
		requires(Robot.shooter);
		SmartDashboard.putBoolean("Has SetAimAngle.SetAimAngle() run?", true);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		SmartDashboard.putBoolean("Has SetAimAngle.initialize() run?",true);
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		SmartDashboard.putBoolean("Has SetAimAngle.execute run?", true);
		Robot.shooter.setShooterAimerMotorSpeed(Robot.oi.getShooterY());
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		SmartDashboard.putBoolean("Has SetAimAngle.isFinished() run?", true);
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		SmartDashboard.putBoolean("Has SetAimAngle.end() run?", true);
		
		//SmartDashboard.putBoolean("Is SetAimAngle executing?", false);
		//SmartDashboard.putBoolean("Is SetAimAngle.execute() running?", false);
		//SmartDashboard.putBoolean("Is SetAimAngle.isFinished() running?", false);
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		SmartDashboard.putBoolean("Has SetAimAngle.interrupted() run?", true);
		
		//SmartDashboard.putBoolean("Is SetAimAngle executing?", false);
		//SmartDashboard.putBoolean("Is SetAimAngle.execute() running?", false);
		//SmartDashboard.putBoolean("Is SetAimAngle.isFinished() running?", false);
		
		end();	
	}
	
	
	
}