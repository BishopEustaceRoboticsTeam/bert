package org.usfirst.frc.team4750.robot.commands;

import org.usfirst.frc.team4750.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Lift extends Command {
	
	boolean direction;
	
    public Lift(boolean direction) {
		// TODO Auto-generated method stub
        requires(Robot.lifter);
        this.direction=direction;
    }

    @Override
    protected void initialize() {
		// TODO Auto-generated method stub
    	
    }

    @Override
    protected void execute() {
		// TODO Auto-generated method stub
    	SmartDashboard.putBoolean("Is Lift executing?", true);
		if (direction){
			Robot.lifter.setLifterMotorSpeed(0.5);
		}
		else{
			Robot.lifter.setLifterMotorSpeed(-0.5);
		}
    }

    @Override
    protected boolean isFinished() {
		// TODO Auto-generated method stub
    	if (direction){
    		return Robot.lifter.isLifterUp();
    	}
    	else {
    		return Robot.lifter.isLifterDown();
    	}
    }

    @Override
    protected void end() {
		// TODO Auto-generated method stub
		SmartDashboard.putBoolean("Is Lift executing?", false);
    }

    protected void interrupted() {
		// TODO Auto-generated method stub
    	end();
    }
}
