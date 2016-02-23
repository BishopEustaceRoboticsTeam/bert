package org.usfirst.frc.team4750.robot.subsystems;

import java.util.Comparator;
import java.util.Vector;

import org.usfirst.frc.team4750.robot.RobotValues;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.CannyOptions;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		public double area;
		public double aspect;
	};
	
	//Return vars
	boolean targetDetected;
	double targetDistance, towerDistance;
	double targetVerticalAngle, targetHorizontalAngle;

	
	//Images
	Image inputFrame;
	Image processedFrame;
	
	int imaqError;
	
	//Constants
	NIVision.Range HUE_RANGE = new NIVision.Range(81,96);	//Hue range for the tape 
	NIVision.Range SAT_RANGE = new NIVision.Range(102,255);	//Saturation range for the tape
	NIVision.Range VAL_RANGE = new NIVision.Range(25,255);	//Value (luminosity) range for the tape
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
		processedFrame = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 0);
		criteria[0] = new NIVision.ParticleFilterCriteria2(NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA,
				AREA_MINIMUM, 100.0, 0, 0);
		
		//The camera name ("cam0") was found through the roborio web interface
//		session = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
//        NIVision.IMAQdxConfigureGrab(session);
//        
//		NIVision.IMAQdxStartAcquisition(session);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void detectTarget(){
//		NIVision.IMAQdxGrab(session, inputFrame, 1);
		NIVision.imaqReadFile(inputFrame, "/home/lvuser/SampleImages/image.jpg");
		
		NIVision.imaqColorThreshold(processedFrame, inputFrame, 255, NIVision.ColorMode.HSV, HUE_RANGE, SAT_RANGE, VAL_RANGE);
		
		int numParticles = NIVision.imaqCountParticles(processedFrame, 1);
		
		CameraServer.getInstance().setImage(inputFrame);
		
		criteria[0].lower = (float) AREA_MINIMUM;
		imaqError = NIVision.imaqParticleFilter4(processedFrame, processedFrame, criteria, filterOptions, null);
		
		numParticles = NIVision.imaqCountParticles(processedFrame, 1);
		
		if(numParticles > 0){
			Vector<ParticleReport> particles = new Vector<ParticleReport>();
			
			for(int particleIndex = 0; particleIndex < numParticles; particleIndex++){
				ParticleReport par = new ParticleReport();
				par.percentAreaToImageArea = NIVision.imaqMeasureParticle(processedFrame, particleIndex, 0, NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA);
				par.area = NIVision.imaqMeasureParticle(processedFrame, particleIndex, 0, NIVision.MeasurementType.MT_AREA);
				par.boundingRectTop = NIVision.imaqMeasureParticle(processedFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_TOP);
				par.boundingRectLeft = NIVision.imaqMeasureParticle(processedFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_LEFT);
				par.boundingRectBottom = NIVision.imaqMeasureParticle(processedFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_BOTTOM);
				par.boundingRectRight = NIVision.imaqMeasureParticle(processedFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_RIGHT);
				particles.add(par);
			}
			particles.sort(null);
			
			scores.aspect = aspectScore(particles.elementAt(0));
			scores.area = areaScore(particles.elementAt(0));
			
			targetDetected = scores.aspect > SCORE_MIN && scores.area > SCORE_MIN;
			
			targetDetected = scores.aspect > SCORE_MIN && scores.area > SCORE_MIN;
//			targetDistance = computeTargetDistance(processedFrame, particles.elementAt(0));
//			targetHorizontalAngle = computeHorizontalAngle(processedFrame, particles.elementAt(0));
//			targetVerticalAngle = computeVerticalAngle(processedFrame, particles.elementAt(0));
//			towerDistance = computeTowerDistance();

		} else {
			targetDetected = false;
		}
    }
    
    
    private double ratioToScore(double ratio){
    	/* This function is zero outside the interval [0,2]. Inside said interval, it maps the 
    	 * interval [0,1] to [0,100] linearly and the interval [1,2] to [0,100] linearly. 
    	 * It is equivalent to the following piecewise function:
    	 *
    	 * 		{0 			if x<0
    	 * f(x)={100x 		if 0<=x<=1
    	 *		{-100x+200  if 1<=x<=2  	
    	 * 		{0			if 2<x
    	 */
    	
    	return Math.max(0, -100*Math.abs(ratio-1)+100); 
    }
    
    private double areaScore(ParticleReport report){
    	double boundingArea = (report.boundingRectRight - report.boundingRectLeft)*(report.boundingRectTop - report.boundingRectBottom);
    	return ratioToScore((240/80)*report.area/boundingArea);
    }
    
    private double aspectScore(ParticleReport report){
    	return ratioToScore((12/20)*((report.boundingRectRight - report.boundingRectLeft)/(report.boundingRectTop - report.boundingRectBottom)));
    }
    
}