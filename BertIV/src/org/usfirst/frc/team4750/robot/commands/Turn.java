package org.usfirst.frc.team4750.robot.commands;

import org.usfirst.frc.team4750.robot.Robot;
import org.usfirst.frc.team4750.robot.RobotValues;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Turn extends Command {

	Timer timer = new Timer();
	private double driveTime;
	private int turnDirection;
	
    public Turn(double time, int direction) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
        driveTime = time;
        turnDirection = direction;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(turnDirection == -1){
    		Robot.driveTrain.setRightDriveMotor(1);
    	}
    	
    	else if(turnDirection == 1){
    		Robot.driveTrain.setLeftDriveMotor(1);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(timer.get() > driveTime){
			return true;
		} else {
			return false;
		}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.setDriveMotors(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
