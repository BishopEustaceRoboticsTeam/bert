package org.usfirst.frc.team4750.robot.commands;

import org.usfirst.frc.team4750.robot.CommandParameters;
import org.usfirst.frc.team4750.robot.Robot;
import org.usfirst.frc.team4750.robot.RobotValues;
import org.usfirst.frc.team4750.robot.subsystems.Shooter;
import org.usfirst.frc.team4750.robot.subsystems.Shooter.ShooterPos;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SetAimAngle extends Command{
	
	boolean direction;
	private int count=0;
	
	public SetAimAngle(CommandParameters.ShooterArmDirection direction) {
		// TODO Auto-generated constructor stub
		requires(Robot.shooter);
		
		SmartDashboard.putBoolean("Is anything working?", true);
		
		SmartDashboard.putBoolean("Direction:",this.direction);
		switch(direction){
			case UP:
				this.direction = true;
				break;
			case DOWN:
				this.direction = false;
				break;
		}
		
		switch(Robot.shooter.getCurrentPos()){
			case VERTICAL:
				if(this.direction){
					//This shouldn't change anything; targetPos should already be currentPos. This 
					//statement is mostly for readability and to make it easier to extend this switching 
					//mechanism to other states.
					Robot.shooter.setTargetPos(ShooterPos.VERTICAL); 
				}
				else {
					Robot.shooter.setTargetPos(ShooterPos.HIGH_GOAL);
				}  //if direction is true, then targetPos will remain unchanged; i.e., it will be VERTICAL.
				break;
			case HIGH_GOAL:
				if(this.direction){
					Robot.shooter.setTargetPos(ShooterPos.VERTICAL);
				} else {
					Robot.shooter.setTargetPos(ShooterPos.LOW_GOAL);
				}
				break;
			case LOW_GOAL:
				if(this.direction){
					Robot.shooter.setTargetPos(ShooterPos.HIGH_GOAL);
				} else{
					Robot.shooter.setTargetPos(ShooterPos.PICK_UP);
				}
				break;
			case PICK_UP:
				if(this.direction){
					Robot.shooter.setTargetPos(ShooterPos.LOW_GOAL);
				} else{
					//This shouldn't change anything; targetPos should already be currentPos. This 
					//statement is mostly for readability and to make it easier to extend this switching 
					//mechanism to other states.
				
					Robot.shooter.setTargetPos(ShooterPos.PICK_UP);
				}	
				break;
		}
		
		SmartDashboard.putBoolean("Is SetAimAngle.execute running?", false);
		SmartDashboard.putString("Current Position:", Robot.shooter.getCurrentPos().toString());
		SmartDashboard.putString("Target Position:", Robot.shooter.getTargetPos().toString());
		
		
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
		SmartDashboard.putBoolean("Is SetAimAngle.execute running?", false);
		SmartDashboard.putString("Current Position:", Robot.shooter.getCurrentPos().toString());
		SmartDashboard.putString("Target Position:", Robot.shooter.getTargetPos().toString());
		
		if (Robot.shooter.getTargetPos() != Robot.shooter.getCurrentPos()) {
			if(direction){
				Robot.shooter.setShooterAimerMotorSpeed(0.5);
			} else{
				Robot.shooter.setShooterAimerMotorSpeed(-0.5);
			}
		}
//			switch (targetPos) {
//				case HIGH_GOAL:
//					Robot.shooter.setShooterAimerMotorSpeed(0.5);
//					break;
//				case LOW_GOAL:
//					switch(currentPos){
//						case HIGH_GOAL:
//							Robot.shooter.setShooterAimerMotorSpeed(-0.5);
//							break;
//						case PICK_UP:
//							Robot.shooter.setShooterAimerMotorSpeed(0.5);
//							break;
//					}
//					break;
//				case PICK_UP:
//					Robot.shooter.setShooterAimerMotorSpeed(-0.5);
//					break;
//			}

	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		SmartDashboard.putBoolean("Is SetAimAngle.execute running?", true);
		SmartDashboard.putString("Current Position:", Robot.shooter.getCurrentPos().toString());
		SmartDashboard.putString("Target Position:", Robot.shooter.getTargetPos().toString());
		
		
		//Robot.shooter.setShooterAimerMotorSpeed(Robot.oi.getShooterY());
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		SmartDashboard.putString("Current Position:", Robot.shooter.getCurrentPos().toString());
		SmartDashboard.putString("Target Position:", Robot.shooter.getTargetPos().toString());

		return (Robot.shooter.getIRSensor(Robot.shooter.getTargetPos()))||(Robot.shooter.getCurrentPos()==Robot.shooter.getTargetPos());
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
		//SmartDashboard.putBoolean("Is SetAimAngle executing?", false);
		//SmartDashboard.putBoolean("Is SetAimAngle.execute() running?", false);
		//SmartDashboard.putBoolean("Is SetAimAngle.isFinished() running?", false);
		
		Robot.shooter.setCurrentPos(Robot.shooter.getTargetPos());
		Robot.shooter.setShooterAimerMotorSpeed(0);
		
		SmartDashboard.putBoolean("Is SetAimAngle.execute running?", false);
		SmartDashboard.putString("Current Position:", Robot.shooter.getCurrentPos().toString());
		SmartDashboard.putString("Target Position:", Robot.shooter.getTargetPos().toString());
		

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		//SmartDashboard.putBoolean("Has SetAimAngle.interrupted() run?", true);
		
		//SmartDashboard.putBoolean("Is SetAimAngle executing?", false);
		//SmartDashboard.putBoolean("Is SetAimAngle.execute() running?", false);
		//SmartDashboard.putBoolean("Is SetAimAngle.isFinished() running?", false);
		
		end();	
	}
	
	
	
}