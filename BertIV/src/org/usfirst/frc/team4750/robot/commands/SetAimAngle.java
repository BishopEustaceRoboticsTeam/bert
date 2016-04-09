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
		this.direction=direction;
		SmartDashboard.putBoolean("IS ANYTHING WORKING??",true);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		switch(Robot.shooter.getCurrentPos()){
			case VERTICAL:
				if(direction){
					Robot.shooter.setTargetPos(ShooterPos.VERTICAL);
				} else {
					Robot.shooter.setTargetPos(ShooterPos.HIGH_GOAL);
					SmartDashboard.putString("DID IT WORK?", "YES!!!");
					SmartDashboard.putString("TARGET POSITION:", Robot.shooter.getTargetPos().toString());
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
		
		SmartDashboard.putString("CURRENT POSITION:", Robot.shooter.getCurrentPos().toString());
		SmartDashboard.putString("TARGET POSITION:", Robot.shooter.getTargetPos().toString());		
		if(Robot.shooter.getCurrentPos()!=Robot.shooter.getTargetPos()){
			SmartDashboard.putBoolean("Is motor Activated?",true);
			if(direction){
				Robot.shooter.setShooterAimerMotorSpeed(-0.3);
				SmartDashboard.putBoolean("Is motor Activated?",true);
			}else{
				Robot.shooter.setShooterAimerMotorSpeed(0.3);
			}
		}
	}


	@Override
	protected void execute() {
//		// TODO Auto-generated method stub
//		//SmartDashboard.putBoolean("Has SetAimAngle.execute run?", true);
		SmartDashboard.putString("CURRENT POSITION:", Robot.shooter.getCurrentPos().toString());
		SmartDashboard.putString("TARGET POSITION:", Robot.shooter.getTargetPos().toString());
	}

	@Override
	protected boolean isFinished() {
//		// TODO Auto-generated method stub
//		//SmartDashboard.putBoolean("Has SetAimAngle.isFinished() run?", true);
		SmartDashboard.putString("CURRENT POSITION:", Robot.shooter.getCurrentPos().toString());
		SmartDashboard.putString("TARGET POSITION:", Robot.shooter.getTargetPos().toString());
//		SmartDashboard.putBoolean("Is motor activated?",false);
		
		return (Robot.shooter.getIRSensor(Robot.shooter.getTargetPos())) || 
			   (Robot.shooter.getCurrentPos()==Robot.shooter.getTargetPos());

	}

	@Override
	protected void end() {
//		// TODO Auto-generated method stub
//		SmartDashboard.putBoolean("Is motor activated?",false);
		SmartDashboard.putString("CURRENT POSITION:", Robot.shooter.getCurrentPos().toString());
		SmartDashboard.putString("TARGET POSITION:", Robot.shooter.getTargetPos().toString());
		
		Robot.shooter.setShooterAimerMotorSpeed(0);
		Robot.shooter.setCurrentPos(Robot.shooter.getTargetPos());
//		//SmartDashboard.putBoolean("Is SetAimAngle executing?", false);
//		//SmartDashboard.putBoolean("Is SetAimAngle.execute() running?", false);
//		//SmartDashboard.putBoolean("Is SetAimAngle.isFinished() running?", false);
		SmartDashboard.putString("CURRENT POSITION:", Robot.shooter.getCurrentPos().toString());
		SmartDashboard.putString("TARGET POSITION:", Robot.shooter.getTargetPos().toString());
		
	}

	@Override
	protected void interrupted() {
//		// TODO Auto-generated method stub
//		SmartDashboard.putBoolean("Has SetAimAngle.interrupted() run?", true);
//		
//		//SmartDashboard.putBoolean("Is SetAimAngle executing?", false);
//		//SmartDashboard.putBoolean("Is SetAimAngle.execute() running?", false);
//		//SmartDashboard.putBoolean("Is SetAimAngle.isFinished() running?", false);
//		
		end();	
	}
	
	
	
}