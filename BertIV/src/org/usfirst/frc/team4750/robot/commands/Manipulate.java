package org.usfirst.frc.team4750.robot.commands;

import org.usfirst.frc.team4750.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Manipulate extends Command {
	boolean l;
	//NOT DONE YET!!!!!
	//0,45,90,135,180,225,270,315
	
    public Manipulate(boolean l) {
		// TODO Auto-generated method stub
        requires(Robot.manipulator);
        this.l=l;
    }

    @Override
    protected void initialize() {
		// TODO Auto-generated method stub
    	
    }

    @Override
    protected void execute() {
		// TODO Auto-generated method stub
    	SmartDashboard.putBoolean("Is Manipulator executing?", true);
		if (Robot.oi.getRightStickPOV() == 0){
			Robot.manipulator.setManipulatorMotorSpeed(0.5);
			System.out.println("UP");
			System.out.print(Robot.oi.getRightStickPOV());
		}
		else if(Robot.oi.getRightStickPOV() == 180){
			Robot.manipulator.setManipulatorMotorSpeed(-0.5);
			System.out.println("DOWN");
			System.out.print(Robot.oi.getRightStickPOV());
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
