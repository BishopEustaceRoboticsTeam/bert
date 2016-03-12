package org.usfirst.frc.team4750.robot.commands;

import org.usfirst.frc.team4750.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class JoystickDrive extends Command{
	public JoystickDrive(){
		requires(Robot.driveTrain);
	}
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		SmartDashboard.putBoolean("Is JoystickDrive executing?",true);
		Robot.driveTrain.controllerDrive(Robot.oi.getLeftStick(), Robot.oi.getRightStick());
		SmartDashboard.putNumber("Left stick Y-axis value: ", Robot.oi.getLeftStick().getRawAxis(1));
		SmartDashboard.putNumber("Left stick X-axis value: ", Robot.oi.getLeftStick().getRawAxis(0));
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.driveTrain.stop();
		SmartDashboard.putBoolean("Is JoystickDrive executing?",false);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
		
	}

}
