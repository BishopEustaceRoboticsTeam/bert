/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot {
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
    
    }
    public class AccererometerSample extends SimpleRobot{
        
        ADXL345_I2C accel; 
        double accelerationX;
        double accelerationY;
        double accelerationZ;
        ADXL345_I2C.AllAxes accelerations;
    
        AccererometerSample()
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
}
     
     
     
     
         

