package org.usfirst.frc.team4750.robot.commands;

import org.usfirst.frc.team4750.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ManualAim extends Command {

	public ManualAim() {
		requires(Robot.shooter);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		if (Robot.oi.getRightY() > 0){
			Robot.shooter.setShooterAimerMotorSpeed(0.5);
			SmartDashboard.putString("Motor Direction?", "forward");
		}
		
		else if (Robot.oi.getRightY() < 0){
			Robot.shooter.setShooterAimerMotorSpeed(-0.5);
			SmartDashboard.putString("Motor Direction?", "reverse");
		}
		
		else{
			Robot.shooter.setShooterAimerMotorSpeed(0);
			SmartDashboard.putString("Motor Direction?", "stopped");
		}
		
		SmartDashboard.putBoolean("Is ManualAim executing?", true);		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return  false; //Robot.oi.getRightY()==0;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.shooter.setShooterAimerMotorSpeed(0);
		SmartDashboard.putBoolean("Is ManualAim executing?", false);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		SmartDashboard.putBoolean("Is ManualAim executing?", false);
		end();
	}

}
