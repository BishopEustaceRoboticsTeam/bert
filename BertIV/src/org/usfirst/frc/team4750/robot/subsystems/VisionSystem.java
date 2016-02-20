package org.usfirst.frc.team4750.robot.subsystems;

import org.usfirst.frc.team4750.robot.RobotValues;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.CannyOptions;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class VisionSystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	int session;
	
	Image inputFrame;
	Image processedFrame;
	
	public VisionSystem(){
		//Create images
		inputFrame = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
		Image processedFrame = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 0);

		//The camera name ("cam0") was found through the roborio web interface
		session = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(session);
        
		NIVision.IMAQdxStartAcquisition(session);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void detectTarget(){
		NIVision.IMAQdxGrab(session, inputFrame, 1);

		NIVision.imaqColorThreshold(processedFrame, inputFrame, 255, NIVision.ColorMode.HSV, RobotValues.TARGET_HUE_RANGE, RobotValues.TARGET_SATURATION_RANGE, RobotValues.TARGET_VALUE_RANGE);
		/* There are CannyOpions constructors with parameters; the most useful one seems to be CannyEdge(double sigma, double upperThreshold, double
		 * lowerThreshold, int windowSize). I have no idea what any of these parameters do, but the last three seem to match three of the four
		 * options in GRIP. The fourth GRIP option if a checkbox, and since sigma isn't a boolean I suspect this will be the hardest one to figure out.
		 */
		NIVision.imaqCannyEdgeFilter(processedFrame,processedFrame,new CannyOptions());
		
		CameraServer.getInstance().setImage(inputFrame);
		

    }
}

