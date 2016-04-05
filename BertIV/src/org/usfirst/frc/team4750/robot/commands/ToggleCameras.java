//package org.usfirst.frc.team4750.robot.commands;
//
//import org.usfirst.frc.team4750.robot.Robot;
//import org.usfirst.frc.team4750.robot.subsystems.Cameras;
//
//import edu.wpi.first.wpilibj.command.Command;
//
///**
// *
// */
//public class ToggleCameras extends Command {
//
//    public ToggleCameras() {
//        // Use requires() here to declare subsystem dependencies
//        requires(Robot.cameras);
//    	
//    }
//
//    // Called just before this Command runs the first time
//    protected void initialize() {
//    	switch(Cameras.getCameraPos()) {
//    		case FRONT:
//    			Cameras.setCameraPos(Cameras.CameraPos.BACK);
//    			break;
//    		case BACK:
//    			Cameras.setCameraPos(Cameras.CameraPos.FRONT);
//    			break;
//    	}
//    }
//
//    // Called repeatedly when this Command is scheduled to run
//    protected void execute() { }
//
//    // Make this return true when this Command no longer needs to run execute()
//    protected boolean isFinished() {
//        return true;
//    }
//
//    // Called once after isFinished returns true
//    protected void end() {
//    }
//
//    // Called when another command which requires one or more of the same
//    // subsystems is scheduled to run
//    protected void interrupted() {
//    }
//}
