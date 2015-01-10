/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Encoder;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class EncoderTest extends IterativeRobot {
    Encoder encoder = new Encoder(1, 2, true, EncodingType.k4X);
    int Count, rawCount;
    double distance, period, rate, RPS;
    boolean direction, stopped;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     * True is left
     * false is right
     */
    
    public void robotInit() {
    System.out.println("Power is on.");    
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
        encoder.start();
        }
    
    public void teleopPeriodic() {
        
        Count = encoder.get();
        System.out.println("Count = " + Count);
        rawCount = encoder.getRaw();
        System.out.println("Raw Count = " + rawCount);
        distance = encoder.getDistance();
        System.out.println("Distance = " + distance);
        rate = encoder.getRate() / 250;
        System.out.println("Rate = " + rate);
        direction = encoder.getDirection();
        System.out.println("Direction = " + direction);
        stopped = encoder.getStopped();
        System.out.println("Stopped = " + stopped);
    }
    
    /**
     * This function is called periodically during test mode
     */
   public void disableInit(){   
//        encoder.setMaxPeriod(period);
//        encoder.setMinRate(rate);
//        encoder.setDistancePerPulse(distance);
//        encoder.setReverseDirection(true);
//        encoder.setSamplesToAverage(rawCount);
        encoder.stop();
        encoder.reset();
    }
    public void testPeriodic() {
    
    }
    
}
