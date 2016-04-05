//package org.usfirst.frc.team4750.robot.subsystems;
//
//import org.usfirst.frc.team4750.robot.RobotValues;
//
//import edu.wpi.first.wpilibj.Servo;
//import edu.wpi.first.wpilibj.command.Subsystem;
//
///**
// *
// */
//public class ShooterServo extends Subsystem {
//    
//    // Put methods for controlling this subsystem
//    // here. Call these from Commands.
//
//	Servo shooterServo = new Servo(RobotValues.SHOOTER_SERVO_PORT);
//	
//    public void initDefaultCommand() {
//        // Set the default command for a subsystem here.
//        //setDefaultCommand(new MySpecialCommand());
//    }
//    
//	public void setShooterServo(boolean position){
//		//This is just an abbreviated if-statement. The format is: (condition) ? (value if true) : (value if false)
//		//0 is the extended position on the servo; 1 is fully retracted.
//		shooterServo.set(position ? 0 : 1);
//		
//	}
//
//}
//
