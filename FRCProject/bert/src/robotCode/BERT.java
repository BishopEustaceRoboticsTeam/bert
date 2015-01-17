
package robotCode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Talon;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */


public class BERT extends IterativeRobot {
	//Controller vars:
	final int F310_REMOTE_USB_PORT = 0;
	F310 rc = new F310(F310_REMOTE_USB_PORT);
	
	//Pneumatics vars:
	final int LIFTER_SOLENOID_PORT = 0;
	final int LOCK_SOLENOID_PORT = 1;
	Pneumatics pneu = new Pneumatics(LIFTER_SOLENOID_PORT, LOCK_SOLENOID_PORT);
	
	//Motor controller PWM ports:
	final int FRONT_RIGHT_MOTOR = 0;
	final int FRONT_LEFT_MOTOR = 1;
	final int BACK_RIGHT_MOTOR = 2;
	final int BACK_LEFT_MOTOR = 3;
	
	
	//Digital sensor ports:
	
	
	//Analog sensor ports:
	
	//Camera vars:
	final int CAMERA_QUALITY = 50;
	final String CAMERA_NAME = "cam0"; //the camera name (ex "cam0") can be found through the roborio web interface
	CameraServer server;
	
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	
    public void robotInit() {
    	//set up the camera for viewing
        server = CameraServer.getInstance();
        server.setQuality(CAMERA_QUALITY);
        server.startAutomaticCapture("CAMERA_NAME");
        
   
    }
    
    
    //****AUTONOMOUS****:
    
    //this method is called once at the start of autonomous
    
    public void autonomousInit(){
    	
    	
    }

    /**
     * This function is called periodically during autonomous
     */
    
    public void autonomousPeriodic() {

    }

  //****TELEOP****:
    
    
   //this method is called once at the start of teleop
    public void teleopInit(){
    
    }
    
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        
    }
    
    //****DISABLED****:
    
    //this runs once at the start of disable
    public void disabledInit(){
    	
    	
    }
    
    //this runs periodically during disabled (loop)
    public void disabledPeriodic(){
    	
    	
    }
    
    //****TEST****:
    
    //this method is called once at the start of test
    public void testInit(){
    	
    	
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
