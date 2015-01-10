/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class ServoControl extends SimpleRobot {
    //Create a new servo object called testServo
    Servo testServo = new Servo(5);

    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        //this shows the different ways to control a servo 
        
        
        for(int i = 0; i <= 180; i=i+4){
            //set the servo from 0 to 180 degrees ad 4 degree increments
            testServo.setAngle(i); //set the angle of the servo equal to i
            Timer.delay(0.1); //give time for the servo to move
            System.out.println("first");
        }
        Timer.delay(2); // delay for two seconds before the next loop
        for(int i = 180; i >= 0; i=i-4){
            //move the servo from 180 to at four degree increments
            testServo.setAngle(i); // set the angle of the servo arm to i 
            Timer.delay(0.1); //give time for the servo to move
            System.out.println("second");
        }
        //use the set command and not set angle 
        
        for(double i = 0.0; i <= 1.1; i=i+0.1){ 
            testServo.set(i); //set servo pos to the value of i
            Timer.delay(0.1); //give time for the servo to move
            System.out.println("third");
        }
        for(double i = 1.1; i >= 0.0; i=i-0.1){
            testServo.set(i); //set the servo pos to i
            Timer.delay(0.1); //give time for the servo to move
            System.out.println("fourth");
            
        }
            
    }
    
    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test() {
    
    }
}
