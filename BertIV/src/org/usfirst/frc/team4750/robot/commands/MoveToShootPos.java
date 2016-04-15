package org.usfirst.frc.team4750.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4750.robot.Robot;
/**
 *
 */

public class MoveToShootPos extends Command {
	boolean direction;
    public MoveToShootPos() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooter.setShooterAimerMotorSpeed(0.3);
    	direction=true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(direction && Robot.shooter.getUpperLimit()){
    		Robot.shooter.setShooterAimerMotorSpeed(-0.3);
    		direction=false;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (!direction && Robot.shooter.getHorizLimit());
    }

    // Called once after isFinished returns true
    protected void end() {
    	direction=!direction;
    	Robot.shooter.setShooterAimerMotorSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	direction=!direction;
    	end();
    }
}
