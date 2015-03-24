
package robotCode;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.VisionException;

import edu.wpi.first.wpilibj.Joystick;
import robotCode.LED.*;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.USBCamera;
import edu.wpi.first.wpilibj.DigitalInput;
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
	Joystick joy_left = new Joystick(RobotValues.JOYSTICK_LEFT_USB_PORT);
	Joystick joy_right = new Joystick(RobotValues.JOYSTICK_RIGHT_USB_PORT);
	
	//Pneumatics Class:
	Pneumatics pneu = new Pneumatics();
	
	//Roller Class:
	Rollers rollers = new Rollers(rc);
	
	//Actuators Class:
	Lifter lifter = new Lifter(pneu);
	
	//Servos
	Servo servo_1 = new Servo(RobotValues.SERVO_1_PORT);
	Servo servo_2 = new Servo(RobotValues.SERVO_2_PORT);
	
	//Robot input output
	//RobotIO inputOutput = new RobotIO();
	//Drive Class:
	Drive drive = new Drive(joy_left, joy_right);

	//Camera Vars:
	final int CAMERA_QUALITY = 50;
	final String CAMERA_NAME = "cam0"; //the camera name (ex "cam0") can be found through the roborio web interface
	CameraServer server;
	int session;
	Image frame;
	//USBCamera camera = new USBCamera(CAMERA_NAME);
	
	//Smart dashboard vars:
	SmartDashboard dashboard = new SmartDashboard();
	
	//Autonomous class
	Autonomous auto;
	
	//teleop vars
	private boolean rollerIn = false; 
	private boolean lock = false;
	private boolean rollerButtonPressed = false;
	private boolean fineButtonPressed = false;
	private boolean lockButtonPressed = false;
	
	DigitalInput toteSensor = new DigitalInput(RobotValues.TOTE_SENSOR_PORT);
	//PDP object
	PowerDistributionPanel pdp = new PowerDistributionPanel();
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	
    public void robotInit() {
    	
    	
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

        try{
        // the camera name (ex "cam0") can be found through the roborio web interface
        session = NIVision.IMAQdxOpenCamera(CAMERA_NAME, NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        
        NIVision.IMAQdxConfigureGrab(session);
        }
        catch(VisionException e){
        	SmartDashboard.putString("Camera", "VisionException");
        	
        }
        //NIVision.IMAQdxStartAcquisition(session);
    	LED.setLEDs(LEDModes.GREEN);
    	 
    	
    	
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
    	
    	
    	auto = new Autonomous(drive, lifter, pneu, rollers);
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
    	
    	//camera stuff:
        try{
        	NIVision.IMAQdxGrab(session, frame, 1);
        	CameraServer.getInstance().setImage(frame);
        }
        catch(VisionException e){
        	SmartDashboard.putString("Camera", "VisionException Auto");
        	
        }
    	
 
    }
    
    //****TELEOP****:
    
   //this method is called once at the start of teleop
    public void teleopInit(){
    	//setup the camera
    	//NIVision.IMAQdxStartAcquisition(session);
    	
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
    	//do some camera stuff
    	try{
    		NIVision.IMAQdxGrab(session, frame, 1);
            CameraServer.getInstance().setImage(frame);
        }
        catch(VisionException e){
        	SmartDashboard.putString("Camera", "VisionException tele");
        	
        }
    	
    	SmartDashboard.putBoolean("Tote position: ",toteSensor.get());
    	
    	
    	
    	//update all the classes
    	drive.update();
    	rollers.update();
    	pneu.update();
    	lifter.update();
    	
    	
    	//here is where the buttons are mapped
    	
    	//---Driver 1---
    	
    	//r3 sets fine control
    	if(joy_left.getRawButton(RobotValues.FINE_CONTROL_BUTTON) || joy_right.getRawButton(RobotValues.FINE_CONTROL_BUTTON)){
    		if(!fineButtonPressed){
    		    drive.startFineControl();
    		    fineButtonPressed = true;
    		}
    		else{
    			drive.startControllerDrive();
    			fineButtonPressed = false;
    		}
    		
    	}
    	
    	//override
    	if(joy_left.getRawButton(RobotValues.OVERRIDE_BUTTON) || joy_right.getRawButton(RobotValues.OVERRIDE_BUTTON)){
    		drive.override();
    	}
    	
    	//---Driver 2---
    	
    	//the lifter
    	
    	//stack
    	if(rc.getYButton()){
    		lifter.startStack();
    	}
    	
    	//place
    	if(rc.getAButton()){
    		lifter.startPlace();
    	}
    	
    	//reset the lifter
    	if(rc.getStartButton()){
    		lifter.startReset();
    	}
    	
    	//the rollers
    	
    	//roller piston
    	// need to switch to remote button
    	if(rc.getL3Button()){
	    		if(!rollerIn){
	    			pneu.startRollerIn();
	    			rollerIn = true;
	    		} else {
	    			pneu.startRollerOut();
	    			rollerIn = false;
	    		}
    		}
    		
    	
    	
    	
    	//the locking mech
    	
    	
    	//lock
    	if(rc.getXButton()){
   			pneu.startLock();
    	}
    	
    	if(rc.getBButton()){
    		pneu.startUnlock();
    	}
    
    	
    	//lifter dubug stuff
    	
    	//need to switch to remote button
    	if(rc.getLBButton()){
    		pneu.lift();
    	}
    	
    	if(rc.getRBButton()){
    		pneu.lower();
    	}
    	
    	
    }
    
    //****DISABLED****:
    
    //this runs once at the start of disable
    public void disabledInit(){
    	//NIVision.IMAQdxStopAcquisition(session);
    	
    	try{
    	NIVision.IMAQdxStartAcquisition(session);
    	}
    	catch(VisionException e)
    	{
    		SmartDashboard.putString("Camera", "VisionException Disabled");
    		
    	}
    	drive.resetEncoders();
    	
    }
     
    //this runs periodically during disabled (loop)
    public void disabledPeriodic(){
    	try{
    		NIVision.IMAQdxGrab(session, frame, 1);
            CameraServer.getInstance().setImage(frame);
        }
        catch(VisionException e){
        	SmartDashboard.putString("Camera", "VisionException Disabled");
        	
        }
    	
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
    	
    if(rc.getAButton()){
    	servo_1.set(0.5);
    	servo_2.set(0.5);
    }
    
    if(rc.getBButton()){
    	servo_1.set(1);
    	servo_2.set(1);
    }
    
    if(rc.getXButton()){
    	servo_1.set(0);
    	servo_2.set(0);
    }
    /**	
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
    	
    */
    }
    
}
