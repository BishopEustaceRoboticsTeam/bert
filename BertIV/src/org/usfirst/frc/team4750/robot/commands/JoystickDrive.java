package org.usfirst.frc.team4750.robot.commands;

import org.usfirst.frc.team4750.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

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
		Robot.driveTrain.controllerDrive(Robot.oi.getLeftStick(), Robot.oi.getRightStick());
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
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
		
	}

}
