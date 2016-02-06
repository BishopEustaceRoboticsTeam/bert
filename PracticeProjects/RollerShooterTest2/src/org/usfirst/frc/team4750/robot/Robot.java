
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
	
	Servo servo1 = new Servo(8);
	
	Talon shooterJoint = new Talon(0);
	Talon leftRoller = new Talon(1);
	Talon rightRoller = new Talon(2);
	
	Talon manipulator1 = new Talon(3);
	Talon manipulator2 = new Talon(4);
	Talon manipulatorRoller = new Talon(5);
	
	Talon leftDrive = new Talon(6);
	Talon rightDrive = new Talon(7);
    
	RobotDrive driveTrain = new RobotDrive(leftDrive, rightDrive);
	RobotDrive roller = new RobotDrive(leftRoller, rightRoller);
	
	public void robotInit() {
        
    }
    
	
    public void autonomousInit() {
    
    }

    
    public void autonomousPeriodic() {
    
    }

    
    public void teleopPeriodic() {
        driveTrain.tankDrive(leftStick.getRawAxis(0), rightStick.getRawAxis(0));
        roller.arcadeDrive(leftStick.getRawAxis(3), 0);
        shooterJoint.set(rightStick.getRawAxis(3));
        
        
    }
    
    
    public void testPeriodic() {
    
    }
    
}
