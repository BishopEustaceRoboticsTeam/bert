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
    Servo X1testServo = new Servo(10);
    Servo Y1testServo = new Servo(5);
    Servo X2testServo = new Servo(6);
    Servo Y2testServo = new Servo(7);
    Joystick leftStick = new Joystick(1);
    Joystick rightStick = new Joystick(2);
    
    
    double joystick1X, joystick1Y, mapped1X, mapped1Y, mapped2X, mapped2Y, joystick2X, joystick2Y;
    
    
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
        joystick1X = leftStick.getAxis(Joystick.AxisType.kX);
        joystick1Y = leftStick.getAxis(Joystick.AxisType.kY);
        joystick2X = rightStick.getAxis(Joystick.AxisType.kX);
        joystick2Y = rightStick.getAxis(Joystick.AxisType.kY);
        
        mapped1Y = joystick1Y/2 + 0.5;
        mapped1X = joystick1X/2 + 0.5;
        mapped2X = joystick2X/2 + 0.5;
        mapped2Y = joystick2Y/2 + 0.5;
        
        X1testServo.set(mapped1X);
        Y1testServo.set(mapped1Y);
        X2testServo.set(mapped2X);
        Y2testServo.set(mapped2Y);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {       
        joystick1X = leftStick.getAxis(Joystick.AxisType.kX);
        joystick1Y = leftStick.getAxis(Joystick.AxisType.kY);
        joystick2X = rightStick.getAxis(Joystick.AxisType.kX);
        joystick2Y = rightStick.getAxis(Joystick.AxisType.kY);
        
        mapped1Y = joystick1Y/2 + 0.5;
        mapped1X = joystick1X/2 + 0.5;
        mapped2X = joystick2X/2 + 0.5;
        mapped2Y = joystick2Y/2 + 0.5;
        
        X1testServo.set(mapped1X);
        Y1testServo.set(mapped1Y);
        X2testServo.set(mapped2X);
        Y2testServo.set(mapped2Y);
        
        
        //System.out.println("X Servo Value = " + XtestServo.get());
        //System.out.println("Left Joystick X Value = " + leftStick.getX());
        //System.out.println("Y Servo Value = " + YtestServo.get());
        //System.out.println("Left Joystick Y Value = " + leftStick.getY());
        
    
    }
    
}
