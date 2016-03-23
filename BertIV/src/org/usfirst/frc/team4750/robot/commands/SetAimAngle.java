package org.usfirst.frc.team4750.robot.commands;

import org.usfirst.frc.team4750.robot.Robot;
import org.usfirst.frc.team4750.robot.RobotValues;
import org.usfirst.frc.team4750.robot.subsystems.Shooter;
import org.usfirst.frc.team4750.robot.subsystems.Shooter.ShooterPos;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SetAimAngle extends Command{
	
	ShooterPos targetPos;
	ShooterPos currentPos;
	
	public SetAimAngle(ShooterPos targetPos) {
		// TODO Auto-generated constructor stub
		requires(Robot.shooter);
		SmartDashboard.putBoolean("Has SetAimAngle.SetAimAngle() run?", true);
		
		this.targetPos = targetPos;
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		SmartDashboard.putBoolean("Has SetAimAngle.initialize() run?",true);

		if (targetPos != currentPos) {
			switch (targetPos) {
				case HIGH_GOAL:
					Robot.shooter.setShooterAimerMotorSpeed(0.5);
					break;
				case LOW_GOAL:
					switch(currentPos){
						case HIGH_GOAL:
							Robot.shooter.setShooterAimerMotorSpeed(-0.5);
							break;
						case PICK_UP:
							Robot.shooter.setShooterAimerMotorSpeed(0.5);
							break;
					}
					break;
				case PICK_UP:
					Robot.shooter.setShooterAimerMotorSpeed(-0.5);
					break;
			}
		}

	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		SmartDashboard.putBoolean("Has SetAimAngle.execute run?", true);
		
		
		//Robot.shooter.setShooterAimerMotorSpeed(Robot.oi.getShooterY());
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		SmartDashboard.putBoolean("Has SetAimAngle.isFinished() run?", true);
		return Robot.shooter.getIRSensor(targetPos);
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
		SmartDashboard.putBoolean("Has SetAimAngle.end() run?", true);
		
		//SmartDashboard.putBoolean("Is SetAimAngle executing?", false);
		//SmartDashboard.putBoolean("Is SetAimAngle.execute() running?", false);
		//SmartDashboard.putBoolean("Is SetAimAngle.isFinished() running?", false);
		
		currentPos = targetPos;
		Robot.shooter.setShooterAimerMotorSpeed(0);
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