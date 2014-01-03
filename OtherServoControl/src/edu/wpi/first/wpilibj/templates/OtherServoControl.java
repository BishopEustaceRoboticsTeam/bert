/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Joystick;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */


public class OtherServoControl extends SimpleRobot {
    I2C i2c;
    Gyro gyro = new Gyro(1); //gyro on analogue port 1
    double accelX, accelY, accelZ;
    Joystick leftJoystick = new Joystick(1);
    Joystick rightJoystick = new Joystick(2);
    Servo servo = new Servo(5);
//    ADXL345_I2C accel = new ADXL345_I2C(1, ADXL345_I2C.DataFormat_Range.k2G); //k2g means 2 times the acceleration ov gravity because the acceleration is very small
    
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
//        i2c.setCompatabilityMode(true); //vital to  get accelerometer to work
         while(isEnabled() && isAutonomous()){
//            accelX = accel.getAcceleration(ADXL345_I2C.Axes.kX);
//            accelY = accel.getAcceleration(ADXL345_I2C.Axes.kY);
//            accelZ = accel.getAcceleration(ADXL345_I2C.Axes.kZ);
//            Timer.delay(0.10); //give time to get the data    
            
            
            
            
             
             
             
         }
        
        
        
        
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
//        i2c.setCompatabilityMode(true); //vital to  get accelerometer to work
        
        while(isEnabled() && isOperatorControl()){      
//            accelX = accel.getAcceleration(ADXL345_I2C.Axes.kX);
//            accelY = accel.getAcceleration(ADXL345_I2C.Axes.kY);
//            accelZ = accel.getAcceleration(ADXL345_I2C.Axes.kZ);
//            Timer.delay(0.10); //give time to get the data    
            
            if(leftJoystick.getTrigger()){
                //if the left trigger is pressed run this code
                servo.setAngle(160);
                //Timer.delay(0.5); //wait one second for this to happen
                
            }
            
            if ( rightJoystick.getTrigger()){
                //the code here runs if the right triger is pressed
                servo.setAngle(0);
                //Timer.delay(0.5);//delay half a second to give time for the servo to move
                
            }
           if (leftJoystick.getRawButton(6) || rightJoystick.getRawButton(6)){
               //this will run if button 6 is pressed on the left joystick or the right joystick
               servo.set(0);
               //Timer.delay(0.5);//wait half a second
               
               
               
           }
            
            if (leftJoystick.getRawButton(5) || rightJoystick.getRawButton(5)){
                //this code will run if button 5 is pressed on either joystick
                servo.set(0.8); // move the servo
                //Timer.delay(0.5); //give half a second for the servo to move
                
            }
            
            
        }
        
        

    }
    
    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test() {
    
    }
}
