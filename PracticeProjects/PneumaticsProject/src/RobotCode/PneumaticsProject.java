/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package RobotCode;


import edu.wpi.first.wpilibj.IterativeRobot;






/**
 * 
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class PneumaticsProject extends IterativeRobot {

    
    Pneumatics p;
    
    
    public void robotInit() {


	int pressure_switch_channel = 5;
	int compressor_relay_channel = 6;
	p = new Pneumatics(pressure_switch_channel, compressor_relay_channel);
        
        int solenoid_1_forward = 7;
        int solenoid_1_backward = 2;
        p.addNewDoubleSolenoid(solenoid_1_forward, solenoid_1_backward);
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

        
        p.startCompressor();

    }
    
    public void diabledPeriodic() {

	p.stopCompressor();

    }



    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
