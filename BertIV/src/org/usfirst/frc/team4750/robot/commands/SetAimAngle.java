package org.usfirst.frc.team4750.robot.commands;

import org.usfirst.frc.team4750.robot.Robot;
import org.usfirst.frc.team4750.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SetAimAngle extends Command{
	
	public SetAimAngle() {
		// TODO Auto-generated constructor stub
		requires(Robot.shooter);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		SmartDashboard.putBoolean("Is SetAimAngle executing?", true);
		Robot.shooter.setShooterAimerMotorSpeed(Robot.oi.getRightTwist());
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		//return Robot.oi.getRightTwist()==0;
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		SmartDashboard.putBoolean("Is SetShooter executing?", false);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();	
	}
	
	
	
}
