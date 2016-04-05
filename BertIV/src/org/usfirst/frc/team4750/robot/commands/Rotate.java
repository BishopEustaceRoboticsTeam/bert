//package org.usfirst.frc.team4750.robot.commands;
//
//import org.usfirst.frc.team4750.robot.Robot;
//
//import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.command.Command;
//
//
///**
// *
// */
//public class Rotate extends Command {
//	
//	Timer timer = new Timer();
//	double time;
//	double speed;
//	
//    public Rotate(double time, double speed) {
//        requires(Robot.driveTrain);
//        this.time = time;
//        this.speed = speed;
//    }
//
//    // Called just before this Command runs the first time
//    protected void initialize() {
//    	timer.start();
//    	Robot.driveTrain.setLeftDriveMotor(speed);
//    	Robot.driveTrain.setRightDriveMotor(-speed);
//    }
//
//    // Called repeatedly when this Command is scheduled to run
//    protected void execute() {
//    }
//
//    // Make this return true when this Command no longer needs to run execute()
//    protected boolean isFinished() {
//        if(timer.get() > time){
//        	return true;
//        } else{
//        	return false;
//        }
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
