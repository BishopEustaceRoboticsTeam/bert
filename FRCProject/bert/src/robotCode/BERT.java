
package robotCode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Solenoid;
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
	Joystick joy = new Joystick(RobotValues.JOYSTICK_USB_PORT);
	//Pneumatics Class:
	Pneumatics pneu = new Pneumatics();
	//Roller Class:
	Rollers rollers = new Rollers(joy);
	//Actuators Class:
	Actuators actuator = new Actuators(pneu);
	//Robot input output
	//RobotIO inputOutput = new RobotIO();
	//Drive Class:
	Drive drive = new Drive(rc);

	//Camera Vars:
	final int CAMERA_QUALITY = 50;
	final String CAMERA_NAME = "cam0"; //the camera name (ex "cam0") can be found through the roborio web interface
	CameraServer server;
	
	//Autonomous class
	Autonomous auto;
	
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
    	 auto = new Autonomous(drive, actuator, pneu);
   
    }
    
    
    //****AUTONOMOUS****:
    
    //this method is called once at the start of autonomous
    
    public void autonomousInit(){
    	// TODO: read your switch here to know which autonomous mode you're doing
    	
    }

    /**
     * This function is called periodically during autonomous
     */
    
    public void autonomousPeriodic() {
    	auto.update();
    	//if(automode == TOTE) {
    		//if(drivestate == forward) {
    			//keep driving forward
    			
    		//} else if (drivestate== FORWARDDONE) {
    			
    		//}
    	//}
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
    	rollers.update();
    	//auto.toteToZone();
    	//auto.binToZone();
    	//auto.driveToZone();
    	if(rc.getR3Button()){
    		drive.startFineControl();
    	}
    	
    	if(joy.getRawButton(2)){
    		rollers.startRollerIntake();
    	}
    	
    	if(joy.getRawButton(3)){
    		rollers.startRollerEject();
    	}
    	
    	if(joy.getRawButton(4)){
    		pneu.lower();
    	}
    	
    	if(joy.getRawButton(5)){
    		pneu.lift();
    	}
    	
    	if(joy.getRawButton(6)){
    		pneu.startLock();
    	}
    	
    	if(joy.getRawButton(7)){
    		pneu.startUnlock();
    	}
    	
    	if(joy.getRawButton(8)){
    		pneu.startRollerIn();
    	}
    	
    	if(joy.getRawButton(9)){
    		pneu.startRollerOut();
    	}
    	
    	if(rc.getYButton()){
    		drive.override();
    	}
    	
    	if(rc.getBButton()){
    		drive.startRightAngleTurn(true);
    	}
    	
    	if(rc.getXButton()){
    		drive.startRightAngleTurn(false);
    	}
    	
    	if(rc.getAButton()){
    		drive.startDistanceDrive(2);
    	}
    	
    	if(rc.getRBButton()){
    		auto.resetStates();
    	}
    	
    }
    
    //****DISABLED****:
    
    //this runs once at the start of disable
    public void disabledInit(){
    	drive.resetEncoders();
    	
    }
     
    //this runs periodically during disabled (loop)
    public void disabledPeriodic(){
    	auto.displayAutoMode();
    	
    	
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
