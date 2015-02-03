
package robotCode;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */


public class BERT extends IterativeRobot {
	//Controller Class:
	F310 rc = new F310(RobotValues.F310_REMOTE_USB_PORT_ONE);
	
	//Pneumatics Class:
	Pneumatics pneu = new Pneumatics();
	
	//Robot input output
	RobotIO inputOutput = new RobotIO();
	
	//Drive Class:
	Drive drive = new Drive(rc, inputOutput);

	//Camera Vars:
	final int CAMERA_QUALITY = 50;
	final String CAMERA_NAME = "cam0"; //the camera name (ex "cam0") can be found through the roborio web interface
	CameraServer server;
	
	//PDP object
	//PowerDistributionPanel pdp = new PowerDistributionPanel();
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	
    public void robotInit() {
    	//set up the camera for viewing
        //server = CameraServer.getInstance();
        //server.setQuality(CAMERA_QUALITY);
        //server.startAutomaticCapture("CAMERA_NAME");
        
   
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
    	//set the drive mode to controllerDrive
    	drive.startControllerDrive();
    }
    
    /**
     * This function is called periodically during operator control
     */
   
    public void teleopPeriodic() {
    	drive.update();
    	
    	//inputOutput.printEncoderValues();
    	
    	if(rc.getYButton()){
    		drive.override();
    	}
    	if(rc.getBButton()){
    		//make sure it finished the previous task
    		if(drive.Done()){
    			drive.startRightAngleTurn(true);
    		}
    	} 
    	if(rc.getXButton()){
    		//make sure it finished the previous task
    		if(drive.Done()){
    			drive.startRightAngleTurn(false);
    		}
    	}
    	if(rc.getAButton()){
    		if(drive.Done()){
    			drive.startDistanceDrive(1);
    		}
    	}
    	
    }
    
    //****DISABLED****:
    
    //this runs once at the start of disable
    public void disabledInit(){
    	inputOutput.resetEncoders();
    	
    }
    
    //this runs periodically during disabled (loop)
    public void disabledPeriodic(){
    	System.out.println("Disabled!");
    	
    	
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
