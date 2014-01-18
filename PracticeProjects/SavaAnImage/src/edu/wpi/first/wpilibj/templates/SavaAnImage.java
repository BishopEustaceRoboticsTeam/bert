/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.*;
import edu.wpi.first.wpilibj.image.NIVision.MeasurementType;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class SavaAnImage extends IterativeRobot {

    AxisCamera camera;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
<<<<<<< HEAD
        camera = AxisCamera.getInstance();

=======
       camera = AxisCamera.getInstance();
      
>>>>>>> aa2cf43ad309653cb5166359290c1395bde94ddb
    }

    /**
     * This function is called periodically during autonomous
     */
<<<<<<< HEAD
    public void autonomousInit() {
        System.out.println("Saving an image....");
        
        if (camera.freshImage()) {
            try {
                ColorImage image = camera.getImage();
                image.write("/testImage.jpg");

            } catch (NIVisionException ex) {
                System.out.println("NIvisionException");
=======
    public void autonomousPeriodic() {

    }
    public void autonomousInit(){
        System.out.println("Saving an image.......");
         if(camera.freshImage()){
           try {
               ColorImage image = camera.getImage();
               image.write("/testImage.png");
               
           } catch (NIVisionException ex) {
               System.out.println("NIVision excepption");
>>>>>>> aa2cf43ad309653cb5166359290c1395bde94ddb
                ex.printStackTrace();
            } catch (AxisCameraException ex) {
                System.out.println("Axis exception");
                ex.printStackTrace();
                System.out.println("AxisCameraException");

            }
<<<<<<< HEAD

        }

    }

    public void autonomousPeriodic() {

=======
               
               
     
         }
       
        
>>>>>>> aa2cf43ad309653cb5166359290c1395bde94ddb
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

}
