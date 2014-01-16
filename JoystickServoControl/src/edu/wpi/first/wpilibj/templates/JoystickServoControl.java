/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class JoystickServoControl extends IterativeRobot {
    Servo XtestServo = new Servo(10);
    Servo YtestServo = new Servo(5);
    Joystick leftStick = new Joystick(1);
    
    double joystickX, joystickY, mappedX, mappedY;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {

    }

    /**
     * This function is called periodically during autonomous
     */
    
    
    
    public void autonomousPeriodic() {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {       
        joystickX = leftStick.getAxis(Joystick.AxisType.kX);
        joystickY = leftStick.getAxis(Joystick.AxisType.kY);
        
        mappedY = joystickY/2 + 0.5;
        mappedX = joystickX/2 + 0.5; 
        XtestServo.set(mappedX);
        YtestServo.set(mappedY);
        System.out.println("X Servo Value = " + XtestServo.get());
        System.out.println("Left Joystick X Value = " + leftStick.getX());
        System.out.println("Y Servo Value = " + YtestServo.get());
        System.out.println("Left Joystick Y Value = " + leftStick.getY());
        
    
    }
    
}
