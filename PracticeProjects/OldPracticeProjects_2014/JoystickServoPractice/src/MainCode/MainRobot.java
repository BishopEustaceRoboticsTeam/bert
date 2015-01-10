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
    //Joystick rightStick = new Joystick(2); //Logitech setup in Driver Station

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
        double x_axis;
        x_axis = leftStick.getX();
        double y_axis;
        y_axis = leftStick.getY();
        Joystick.AxisType X;

        System.out.println("x value is " + x_axis);
        System.out.println("y vaule is " + y_axis);
        


    }

    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test() {

        System.out.println("Test mode");

        ServoPractice servo_test = new ServoPractice(10);

        servo_test.getServoValue();
        servo_test.getServoAngle();

        //TestDrive test_drive = new TestDrive(drive);
        double x_axis;
        x_axis = leftStick.getX();
        double y_axis;
        y_axis = leftStick.getY();
        Joystick.AxisType X;

        System.out.println("x value is " + x_axis);
        System.out.println("y vaule is " + y_axis);

//        if (leftStick.getAxis(X axis)) {
//            servo_test.myservo_.set(left, right);
//    }
//        
        //ServoPractice servo_test = new ServoPractice(10);
        //servo_test.getServoValue();
        //servo_test.getServoAngle();
        //testTimer.start();
        while (isTest() && isEnabled()) {
            //        System.out.println(", " + sensor.getIRLeftInches() + ", " + sensor.getIRRightInches() + ", " +sensor.getIRFrontInches() );
            //System.out.println("garret smells");
            //test_drive.driveTenMeters();
            //System.out.println("left stick value: " + xval);
            //double getY = leftStick.getY();
            //System.out.println("left stick value" + yval);

            boolean yes;
            //(leftStick.getX(-1)) { 
            servo_test.myservo_.set(-1);
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
