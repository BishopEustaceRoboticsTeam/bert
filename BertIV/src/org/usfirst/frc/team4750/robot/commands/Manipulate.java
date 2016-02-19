package org.usfirst.frc.team4750.robot.commands;

import org.usfirst.frc.team4750.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Manipulate extends Command {
	
	//NOT DONE YET!!!!!
	
	boolean direction;
	
    public Manipulate(boolean direction) {
		// TODO Auto-generated method stub
        requires(Robot.manipulator);
        this.direction=direction;
    }

    @Override
    protected void initialize() {
		// TODO Auto-generated method stub
    	
    }

    @Override
    protected void execute() {
		// TODO Auto-generated method stub
    	SmartDashboard.putBoolean("Is Manipulator executing?", true);
		if (direction){
			Robot.manipulator.setManipulatorMotorSpeed(0.5);
		}
		else{
			Robot.manipulator.setManipulatorMotorSpeed(-0.5);
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
