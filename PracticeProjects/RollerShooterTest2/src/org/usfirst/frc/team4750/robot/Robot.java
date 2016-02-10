
package org.usfirst.frc.team4750.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;

public class Robot extends IterativeRobot {
    
	Joystick leftStick = new Joystick(0);
	Joystick rightStick = new Joystick(1);
	
	Servo servo1 = new Servo(7);
	
	Talon shooterJoint = new Talon(6);
	Talon leftRoller = new Talon(5);
	Talon rightRoller = new Talon(4);
	
	//Talon manipulator1 = new Talon(3);
	//Talon manipulator2 = new Talon(4);
	//Talon manipulatorRoller = new Talon(5);
	
	Talon frontLeftDrive = new Talon(0);
	Talon frontRightDrive = new Talon(2);
	Victor backLeftDrive = new Victor(3);
	Victor backRightDrive = new Victor(1);
    
	RobotDrive driveTrain = new RobotDrive(frontLeftDrive, backLeftDrive, frontRightDrive, backRightDrive);
	RobotDrive roller = new RobotDrive(leftRoller, rightRoller);
	
	public void robotInit() {
        
    }
    
	
    public void autonomousInit() {
    
    }

    
    public void autonomousPeriodic() {
    
    }

    
    public void teleopPeriodic() {
        
    	driveTrain.tankDrive(-leftStick.getRawAxis(1), -rightStick.getRawAxis(1));
        roller.arcadeDrive(leftStick.getRawAxis(3), 0);
        shooterJoint.set(rightStick.getRawAxis(3));
        
        if(rightStick.getTrigger()){
        	servo1.set(1);
        }
        
        else{
        	servo1.set(0);
        }
               
    }
    
    public void testPeriodic() {
    
    }
    
}