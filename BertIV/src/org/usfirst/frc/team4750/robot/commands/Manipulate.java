package org.usfirst.frc.team4750.robot.commands;

import org.usfirst.frc.team4750.robot.Robot;
import org.usfirst.frc.team4750.robot.RobotValues;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Manipulate extends Command {
	
	//NOT DONE YET!!!!!
	//0,45,90,135,180,225,270,315
	
    public Manipulate() {
		// TODO Auto-generated method stub
        requires(Robot.manipulator);
        
    }

    @Override
    protected void initialize() {
		// TODO Auto-generated method stub
    	
    }

    @Override
    protected void execute() {
		// TODO Auto-generated method stub
    	SmartDashboard.putBoolean("Is Manipulator executing?", true);
    	SmartDashboard.putNumber("POV value:", Robot.oi.getRightStickPOV());
    	
		if (Robot.oi.getRightStickPOV() == 315 || Robot.oi.getRightStickPOV() == 0 || Robot.oi.getRightStickPOV() == 45){
			Robot.manipulator.setManipulatorMotorSpeed(RobotValues.MANIPULATOR_SPEED);
			SmartDashboard.putString("Manipulator Direction", "UP");
		}
		else if(Robot.oi.getRightStickPOV() == 135 || Robot.oi.getRightStickPOV() == 180 || Robot.oi.getRightStickPOV() == 225){
			Robot.manipulator.setManipulatorMotorSpeed(-RobotValues.MANIPULATOR_SPEED);
			SmartDashboard.putString("Manipulator Direction", "DOWN");
		}
		else{
			Robot.manipulator.setManipulatorMotorSpeed(0);
		}
    }

    @Override
    protected boolean isFinished() {
		// TODO Auto-generated method stub
    	return false;
    }

    @Override
    protected void end() {
		// TODO Auto-generated method stub
		SmartDashboard.putBoolean("Is Manipulator executing?", false);
    }

    protected void interrupted() {
		// TODO Auto-generated method stub
    	end();
    }
}
