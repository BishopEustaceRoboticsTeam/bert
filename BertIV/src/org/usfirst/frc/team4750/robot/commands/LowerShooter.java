package org.usfirst.frc.team4750.robot.commands;

import org.usfirst.frc.team4750.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LowerShooter extends Command {

	Timer timer = new Timer();
	final static int STOP_TIME= 1;
	
    public LowerShooter() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    	Robot.shooter.setShooterAimerMotorSpeed(-0.3);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() { }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (timer.get()>=STOP_TIME);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.setShooterAimerMotorSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
