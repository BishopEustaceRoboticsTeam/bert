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
		Robot.shooter.setShooterAimerMotorSpeed(-Robot.oi.getRightY() / 2);
		SmartDashboard.putBoolean("Is ManualAim executing?", true);
		SmartDashboard.putNumber("Right Twist:", Robot.oi.getRightTwist());
		SmartDashboard.putNumber("Left Twist:", Robot.oi.getLeftTwist());
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Robot.oi.getLeftY()==0;
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
	}

}
