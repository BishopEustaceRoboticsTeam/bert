/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package RobotCode;


import edu.wpi.first.wpilibj.IterativeRobot;
//import edu.wpi.first.wpilibj.DigitalOutput;
//import edu.wpi.first.wpilibj.Relay;




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
  //DigitalOutput do_test_;
  //Relay relay_;
    
    public void robotInit() {


        System.out.println("RobotInit");

        //relay_ = new Relay(1);
        

    //do_test_ = new DigitalOutput(1);
        int pressure_switch_channel = 1; // should be a digital input
	int compressor_relay_channel = 1; // should be a relay port
	p = new Pneumatics(pressure_switch_channel, compressor_relay_channel);
	
        //int solenoid_1_forward = 7;
        //int solenoid_1_backward = 5;
        //p.addNewDoubleSolenoid(solenoid_1_forward, solenoid_1_backward);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    public void teleopInit() {
        System.out.println("teleopInit");
        //do_test_.set(true);
        //p.startCompressor();
	//boolean switchval = p.pressureSwitchValue();
	//System.out.println("swith is " + switchval);
	//p.enableLiveWindowMode();
    }
    
    
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {

        
            //p.startCompressor();

    }
    
    public void disabledPeriodic() {

        //System.out.println("disabledperiodic!!!");
        //do_test_.set(false);
        p.stopCompressor();

    }



    /**
     * This function is called periodically during test mode
     */
    public void testInit() {
        System.out.println("testInit");
  
        
        //relay_.set(Relay.Value.kForward);
//do_test_.set(true);
        p.startCompressor();
	//int val = getDefaultDigitalModule();
	//System.out.println("default digital module: " + val);

	boolean switchval = p.pressureSwitchValue();
	System.out.println("swith is " + switchval);
    	//p.enableLiveWindowMode();
    
    }

    
}
