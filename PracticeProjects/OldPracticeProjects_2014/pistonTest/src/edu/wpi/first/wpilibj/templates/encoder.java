/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class encoder extends IterativeRobot {
    //                            1 = channel a 2 == channel b true
    Encoder encoder = new Encoder(1, 2, false, EncodingType.k4X);
    int count;
    boolean stopped;
    boolean direction;
    int rawCount;
    
    
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
    
    public void teleopInit(){
        
        encoder.setMaxPeriod(.1);
        encoder.start();
        encoder.setMinRate(10); 
        encoder.setDistancePerPulse(5);
        //encoder.setReverseDirection();
        encoder.setSamplesToAverage(7);
        
    }
    public void teleopPeriodic() {
        
        rawCount = encoder.getRaw();
        System.out.println("rawCount = " + rawCount);
        direction = encoder.getDirection();
        System.out.println("Drirection = " + direction);
        stopped = encoder.getStopped();
        System.out.println("Stoped? = " + stopped);
        count = encoder.get();
        System.out.println("count = " + count);

  
 

 
        
       
    }
    public void disabledInit(){
        encoder.stop();
        encoder.reset();
        
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
