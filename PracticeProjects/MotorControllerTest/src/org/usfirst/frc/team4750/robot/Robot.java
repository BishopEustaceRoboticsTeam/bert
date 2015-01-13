
package org.usfirst.frc.team4750.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	Talon motor_1 = new Talon(0);
	//Compressor compress = new Compressor();
	Joystick joy_1 = new Joystick(0);
	PowerDistributionPanel pdp = new PowerDistributionPanel();
	
	//double current = pdp.getCurrent;
	
    public void robotInit() {
    	
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	
    	

    }
    
    public void teleopInit(){
    	
    	for (int i = 0; i <= 10; i++){
    		motor_1.set(-1);
    		System.out.println("Full back");
    		Timer.delay(1);
    		motor_1.set(1);
    		System.out.println("Full forward");
    		Timer.delay(1);
    		System.out.println("current is " + pdp.getCurrent(0));
    	}
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	
        //motor_1.set(joy_1.getY());
    	//System.out.println("The current is " + pdp.getCurrent(1));
    	
    	
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
