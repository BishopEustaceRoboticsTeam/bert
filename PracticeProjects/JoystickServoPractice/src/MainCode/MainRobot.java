/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package MainCode;

import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Joystick;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class MainRobot extends SimpleRobot {

           
    Joystick leftStick = new Joystick(1); //Logitech setup in Driver Station
    Joystick rightStick = new Joystick(2); //Logitech setup in Driver Station

    //MainRobot() {
        //System.out.println("Woot Woot!  Constructor");
        
        //System.out.println("Disabling the Safety");
        //drive.setSafetyEnabled(false);
        
    //}
    
    
    
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {

    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {

    }

    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test() {

        System.out.println("Test mode");
        
        //TestDrive test_drive = new TestDrive(drive);

        double axis = leftStick.getAxis(Joystick.AxisType.kX);
        double x = leftStick.getX();
        
        
        ServoPractice servo_test = new ServoPractice(10);

        servo_test.getServoValue();
        servo_test.getServoAngle();

        //testTimer.start();
        while (isTest() && isEnabled()) {
            //        System.out.println(", " + sensor.getIRLeftInches() + ", " + sensor.getIRRightInches() + ", " +sensor.getIRFrontInches() );
            //System.out.println("garret smells");
            //test_drive.driveTenMeters();

            double xval = leftStick.getX();
            System.out.println("left stick value: " + xval);
            
            if (rightStick.getTrigger()) {
                servo_test.myservo_.set(0);
            }
            if (leftStick.getTrigger()) {
                servo_test.myservo_.set(1);
            }
            if (leftStick.getRawButton(4)) {
                servo_test.myservo_.set(0.5);
            }
            //servo_test.myservo_.setAngle(120);
            double servoangle = servo_test.getServoAngle();
            System.out.println("servo angle is: " + servoangle);
            double servovalue = servo_test.getServoValue();
            System.out.println("servo value is: " + servovalue);

        }
    
    }
}