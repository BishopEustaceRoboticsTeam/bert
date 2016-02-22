package org.usfirst.frc.team4750.robot.subsystems;

import java.util.Comparator;

import org.usfirst.frc.team4750.robot.RobotValues;
import org.usfirst.frc.team4750.robot.Robot.ParticleReport;
import org.usfirst.frc.team4750.robot.Robot.Scores;

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
	
	/* This is a structure to hold various measurements of a particle (ie, a connected section of 
	 * the output of a binary mask). */
	public class ParticleReport implements Comparator<ParticleReport>, Comparable<ParticleReport>{
		double percentAreaToImageArea;
		double area;
		double boundingRectLeft;
		double boundingRectTop;
		double boundingRectRight;
		double boundingRectBottom;
		
		@Override
		public int compareTo(ParticleReport report) {
			// TODO Auto-generated method stub
			return (int) (report.area - this.area);
		}

		@Override
		public int compare(ParticleReport report1, ParticleReport report2) {
			// TODO Auto-generated method stub
			return (int) (report1.area - report2.area);
		}
		
	};
	
	//This is a structure to hold the scores of particles.
	public class Scores{
		public double AreaScore;
		public double AspectScore;
	};
	
	
	//Images
	Image inputFrame;
	Image processedFrame;
	
	int imaqError;
	
	//Constants
	NIVision.Range HUE_RANGE_1 = new NIVision.Range(0,63);	//Hue range for the tape 
	NIVision.Range SAT_RANGE_1 = new NIVision.Range(3,89);	//Saturation range for the tape
	NIVision.Range VAL_RANGE_1 = new NIVision.Range(90,213);	//Value (luminosity) range for the tape
	double AREA_MINIMUM = 0.5; //Default Area minimum for particle as a percentage of total image area
	double LONG_RATIO = 10/7; //A bounding retangle of the U-shaped target tape would have a length of 20 in. and a heigh of 14.
	double SCORE_MIN = 75.0;  //Minimum score to be considered a tote
	double VIEW_ANGLE = 52; //View angle fo camera, set to Axis m1011 by default, 64 for m1013, 51.7 for 206, 52 for HD3000 square, 60 for HD3000 640x480
	NIVision.ParticleFilterCriteria2[] criteria = new NIVision.ParticleFilterCriteria2[1];
	NIVision.ParticleFilterOptions2 filterOptions = new NIVision.ParticleFilterOptions2(0,0,1,1); //Don't know what these options do. They were the default in the example code, so until we learn more I'm sticking with them.
	Scores scores = new Scores();

	int session;
	
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

