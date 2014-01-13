/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.frcsimulator.test;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Watchdog;

/**
 *
 * @author benjamin
 */
public class FRCBotWatchdogExample extends SimpleRobot{
    @Override
    public void robotInit(){
	System.out.println("Watchdog example class. Enables the watchdog but only feeds it in operatorControl().");
	getWatchdog().setEnabled(true);
    }
    @Override
    public void operatorControl(){
	while(isOperatorControl() && isEnabled()){
	    getWatchdog().feed();
	    Timer.delay(0.05);
	}
    }
}
