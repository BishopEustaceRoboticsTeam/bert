/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author bert2
 */
public class GyroSample extends SimpleRobot {
        
        private RobotDrive myRobot; //Robot drive system
        private Gyro gyro;
        
        double Kp = 0.03;
        
        public GyroSample()
        {
            myRobot.setExpiration(0.1);
        }
    protected void Autonomous(){
        gyro.reset();
        while (isAutonomous()) {
            double angle = gyro.getAngle(); //get heading
                myRobot.drive(-1.0, angle*Kp); //drive to heading
                Timer.delay(0.004);
        }
        myRobot.drive(0.0, 0.0); //stop robot  
   }
}
