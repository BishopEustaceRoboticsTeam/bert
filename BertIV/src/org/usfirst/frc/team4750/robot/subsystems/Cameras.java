package org.usfirst.frc.team4750.robot.subsystems;

import org.usfirst.frc.team4750.robot.RobotValues;
import org.usfirst.frc.team4750.robot.commands.UpdateCameraFeed;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.USBCamera;

/**
 *
 */

public class Cameras extends Subsystem {
	
	public enum CameraPos{
		FRONT, BACK
	}

	
	public static CameraPos cameraPos = CameraPos.FRONT;
   
	static CameraServer server;
	static USBCamera frontCamera;
	static USBCamera backCamera;
	static Image image;
	
	public Cameras(){
		frontCamera = new USBCamera(RobotValues.FRONT_CAMERA_NAME);
		backCamera = new USBCamera(RobotValues.BACK_CAMERA_NAME);
		
		image = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		
		server = CameraServer.getInstance();
		server.setQuality(50); //TODO: Make constant for this
		
		frontCamera.openCamera();
		frontCamera.setFPS(20); //TODO: make a fps a global const 
		frontCamera.updateSettings();
		
		backCamera.openCamera();
		backCamera.setFPS(20); //TODO: make a fps a global const
		backCamera.updateSettings();
		
		//Set default camera to automatically send default camera to dashboard
		frontCamera.startCapture(); //start default camera (USB)*/
	}
	
	
	
	public static void update(){
		switch(cameraPos){
			case FRONT:
				frontCamera.getImage(image);				
				break;
			case BACK:
				backCamera.getImage(image);
				break;
		}
		server.setImage(image);
	}
	
	public static void setCameraPos(CameraPos newCameraPos){
		cameraPos = newCameraPos;
		switch(cameraPos) {
		case BACK:
			frontCamera.stopCapture();
			backCamera.startCapture();
			break;
		case FRONT:
			backCamera.stopCapture();
			frontCamera.startCapture();
			break;
		}
	}
	
	public static CameraPos getCameraPos(){
		return cameraPos;
	}
		
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new UpdateCameraFeed());
    }
}

