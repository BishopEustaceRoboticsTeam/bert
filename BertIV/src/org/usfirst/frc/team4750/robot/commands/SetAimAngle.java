package org.usfirst.frc.team4750.robot.commands;

import org.usfirst.frc.team4750.robot.Robot;
import org.usfirst.frc.team4750.robot.RobotValues;
import org.usfirst.frc.team4750.robot.subsystems.Shooter;
import org.usfirst.frc.team4750.robot.subsystems.Shooter.ShooterPos;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class SetAimAngle extends Command{
	boolean direction;
	public SetAimAngle(boolean direction) {
		// TODO Auto-generated constructor stub
		requires(Robot.shooter);
		SmartDashboard.putBoolean("Has SetAimAngle.SetAimAngle() run?", true);
		this.direction=direction;
		
		switch(Robot.shooter.getCurrentPos()){
			case VERTICAL:
				if(direction){
					Robot.shooter.setTargetPos(ShooterPos.VERTICAL);
				} else {
					Robot.shooter.setTargetPos(ShooterPos.HIGH_GOAL);
				}
				break;
			case HIGH_GOAL:
				if(direction){
					Robot.shooter.setTargetPos(ShooterPos.VERTICAL);
				} else{
					Robot.shooter.setTargetPos(ShooterPos.LOW_GOAL);
				}
				break;
			case LOW_GOAL:
				if(direction){
					Robot.shooter.setTargetPos(ShooterPos.HIGH_GOAL);
				} else{
					Robot.shooter.setTargetPos(ShooterPos.PICK_UP);
				}
				break;
			case PICK_UP:
				if(direction){
					Robot.shooter.setTargetPos(ShooterPos.LOW_GOAL);
				} else{
					Robot.shooter.setTargetPos(ShooterPos.PICK_UP);
				}
				break;
		}
		
		
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		SmartDashboard.putBoolean("Has SetAimAngle.initialize() run?",true);
		if(Robot.shooter.getCurrentPos()!=Robot.shooter.getTargetPos()){
			if(direction){
				Robot.shooter.setShooterAimerMotorSpeed(-0.3);
			}else{
				Robot.shooter.setShooterAimerMotorSpeed(0.3);
			}
		}
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		//SmartDashboard.putBoolean("Has SetAimAngle.execute run?", true);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		//SmartDashboard.putBoolean("Has SetAimAngle.isFinished() run?", true);
		return (Robot.shooter.getIRSensor(Robot.shooter.getTargetPos())) || 
			   (Robot.shooter.getCurrentPos()==Robot.shooter.getTargetPos());
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		SmartDashboard.putBoolean("Has SetAimAngle.end() run?", true);
		
		Robot.shooter.setShooterAimerMotorSpeed(0);
		Robot.shooter.setCurrentPos(Robot.shooter.getTargetPos());
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