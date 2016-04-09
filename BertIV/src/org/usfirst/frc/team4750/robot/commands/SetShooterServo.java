//package org.usfirst.frc.team4750.robot.commands;
//
//import org.usfirst.frc.team4750.robot.Robot;
//
//import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//
//public class SetShooterServo extends Command{
//	
//	public SetShooterServo() {
//		// TODO Auto-generated constructor stub
//		requires(Robot.shooterServo);
//	}
//	
//	
//	@Override
//	protected void initialize() {
//		// TODO Auto-generated method stub
//		
//		//true sets the servo to the extended position; false retracts it.
//		Robot.shooterServo.setShooterServo(true);
//
//		SmartDashboard.putBoolean("Is SetShooterServo executing?", true);
//	}
//
//	@Override
//	protected void execute() {
//		// TODO Auto-generated method stub
//	}
//
//	@Override
//	protected boolean isFinished() {
//		// TODO Auto-generated method stub
//		return false;
//	}  	
//
//	@Override
//	protected void end() {
//		// TODO Auto-generated method stub
//		SmartDashboard.putBoolean("Is SetShooterServo executing?", false);
//		Robot.shooterServo.setShooterServo(false);
//	}
//
//	@Override
//	protected void interrupted() {
//		// TODO Auto-generated method stub
//		end();
//	}
//
//}
