
package robotCode;

import edu.wpi.first.wpilibj.Joystick;
import robotCode.LED.*;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.USBCamera;
//import edu.wpi.first.wpilibj.Solenoid;
//import edu.wpi.first.wpilibj.Talon;
//import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
	Lifter lifter = new Lifter(pneu);
	
	//Robot input output
	//RobotIO inputOutput = new RobotIO();
	//Drive Class:
	Drive drive = new Drive(rc);

	//Camera Vars:
	final int CAMERA_QUALITY = 50;
	final String CAMERA_NAME = "cam0"; //the camera name (ex "cam0") can be found through the roborio web interface
	CameraServer server;
	//USBCamera camera = new USBCamera(CAMERA_NAME);
	
	//Autonomous class
	Autonomous auto;
	
	//teleop vars
	private boolean rollerIn = false; 
	private boolean lock = false;
	private boolean rollerButtonPressed = false;
	private boolean R3ButtonPressed = false;
	private boolean lockButtonPressed = false;
	//PDP object
	PowerDistributionPanel pdp = new PowerDistributionPanel();
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	
    public void robotInit() {
    	//set up the camera for viewing
    	//server = CameraServer.getInstance();
        //server.setQuality(CAMERA_QUALITY);
        //server.startAutomaticCapture(CAMERA_NAME);
    	//if(camera != null){
    	    //server = CameraServer.getInstance();
            //server.setQuality(CAMERA_QUALITY);
            
            //server.startAutomaticCapture(camera);
    	//}
    	//else{
    		//SmartDashboard.putString("Camera Status:", "Not Working");
    	//}
    	
    	
    	auto = new Autonomous(drive, lifter, pneu);
    	pdp.clearStickyFaults();
    	 
    }
    
    
    //****AUTONOMOUS****:
    //this method is called once at the start of autonomous
    
    public void autonomousInit(){
    	//set the auto mode based on the switch position
    	auto.setAutoMode();
    	
    }

    /**
     * This function is called periodically during autonomous
     */
    
    public void autonomousPeriodic() {
    	auto.update();
    	
    	//update everything for auto
    	drive.update();
    	rollers.update();
    	pneu.update();
    	lifter.update();
 
    }
    
    //****TELEOP****:
    
   //this method is called once at the start of teleop
    public void teleopInit(){
    	//start the led light show
    	LED.setLEDs(LEDModes.RAINBOW);
    	
    	//set the drive mode to controllerDrive
    	drive.startControllerDrive();
    	rollers.startRollerControl();
    }
    
    /**
     * This function is called periodically during operator control
     */
   
    public void teleopPeriodic() {
    	//update all the classes
    	drive.update();
    	rollers.update();
    	pneu.update();
    	lifter.update();
    	
    	
    	//here is where the buttons are mapped
    	
    	//---Driver 1---
    	
    	//r3 sets fine control
    	if(rc.getR3Button()){
    		if(!R3ButtonPressed){
    		    drive.startFineControl();
    		    R3ButtonPressed = true;
    		}
    		else{
    			drive.startControllerDrive();
    			R3ButtonPressed = false;
    		}
    		
    	}
    	
    	//left 90 degree turn
    	if(rc.getXButton()){
    		drive.startRightAngleTurn(true);
    	}
    	
    	//right 90 degree turn
    	if(rc.getBButton()){
    		drive.startRightAngleTurn(false);
    	}
    	
    	//override
    	if(rc.getYButton()){
    		drive.override();
    	}
    	
    	//test the backStraightDrive
    	if(rc.getAButton()){
    		drive.startBDistanceDrive(1);    		
    	}
    	//---Driver 2---
    	
    	//the lifter
    	
    	//stack
    	if(joy.getRawButton(RobotValues.STACK)){
    		lifter.startStack();
    	}
    	
    	//place
    	if(joy.getRawButton(RobotValues.PLACE)){
    		lifter.startPlace();
    	}
    	
    	//reset the lifter
    	if(joy.getRawButton(RobotValues.RESET_LIFTER)){
    		lifter.startReset();
    	}
    	
    	//the rollers
    	
    	//roller piston
    	if(joy.getRawButton(RobotValues.ROLLER_IN_OUT)){
    		if (!rollerButtonPressed) {
	    		if(!rollerIn){
	    			pneu.startRollerIn();
	    			rollerIn = true;
	    		} else {
	    			pneu.startRollerOut();
	    			rollerIn = false;
	    		}
    		}
    		rollerButtonPressed = true;
    	} else {
    		rollerButtonPressed = false;
    	}
    	
    	
    	
    	//the locking mech
    	
    	
    	//lock
    	if(joy.getRawButton(RobotValues.TOTE_LOCK_UNLOCK)){
    		if(!lockButtonPressed){
    			if(!lock){
    				pneu.startLock();
    				lock = true;
    			}
    			else{
    				pneu.startUnlock();
    				lock = false;
    			}
    			lockButtonPressed = true;
    		}
    		else{
    			lockButtonPressed = false;
    			
    		}
    	}
    	
    	//lifter dubug stuff
    	
    	if(joy.getRawButton(RobotValues.LIFTER_DOWN_BUTTON)){
    		pneu.lift();
    	}
    	
    	if(joy.getRawButton(RobotValues.LIFTER_UP_BUTTON)){
    		pneu.lower();
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
    	//update all the classes
    	drive.update();
    	rollers.update();
    	pneu.update();
    	lifter.update();
    	
    	
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
    	
    	if(rc.getL3Button()){
    		drive.startBDistanceDrive(5);
    	}
    	
    	if(rc.getRBButton()){
    		auto.resetStates();
    	}
    
    }
    
}
