package org.usfirst.frc.team4750.robot.commands;

import org.usfirst.frc.team4750.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SetShooterServo extends Command{
	
	boolean position;
	
	public SetShooterServo(boolean position) {
		// TODO Auto-generated constructor stub
		//requires(Robot.shooter);
		this.position=position;
	}
	
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		SmartDashboard.putBoolean("Is SetShooterServo executing?", true);
		Robot.shooter.setShooterServo(0);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}  	

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		SmartDashboard.putBoolean("Is SetShooterServo executing?", false);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		Robot.shooter.setShooterServo(1);
		end();
	}

}
