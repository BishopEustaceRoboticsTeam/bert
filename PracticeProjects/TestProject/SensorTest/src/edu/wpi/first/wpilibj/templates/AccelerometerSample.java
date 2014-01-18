/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.SimpleRobot;

 
    public class AccelerometerSample extends SimpleRobot{
        
        ADXL345_I2C accel; 
        double accelerationX;
        double accelerationY;
        double accelerationZ;
        ADXL345_I2C.AllAxes accelerations;
    
        AccelerometerSample()
        {
            accel = new ADXL345_I2C(1, ADXL345_I2C.DataFormat_Range.k2G);     
        }
        /**
         * This function is called once each time the robot enters operator control.
         */
     public void operatorControl(){
         while(isOperatorControl() && isEnabled())
         {
             accelerationX = accel.getAcceleration(ADXL345_I2C.Axes.kX);
             accelerationY = accel.getAcceleration(ADXL345_I2C.Axes.kX);
             accelerationZ = accel.getAcceleration(ADXL345_I2C.Axes.kX);
             
             
             accelerations = accel.getAccelerations();
             accelerationX = accelerations.XAxis;
         }
       }
     }
     
     
     
     
         

