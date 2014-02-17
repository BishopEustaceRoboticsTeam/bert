/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotCode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.*;
import edu.wpi.first.wpilibj.image.NIVision.MeasurementType;

/**
 *
 * @author bert3
 */
public class VisionProcessor {

    //Camera constants used for distance calculation
    final int Y_IMAGE_RES = 480;		//X Image resolution in pixels, should be 120, 240 or 480
    final double VIEW_ANGLE = 49;		//Axis M1013
    //final double VIEW_ANGLE = 41.7;		//Axis 206 camera
    //final double VIEW_ANGLE = 37.4;  //Axis M1011 camera
    final double PI = 3.141592653;

    //Score limits used for target identification
    final int RECTANGULARITY_LIMIT = 40;
    final int ASPECT_RATIO_LIMIT = 55;

    //Score limits used for hot target determination
    final int TAPE_WIDTH_LIMIT = 50;
    final int VERTICAL_SCORE_LIMIT = 50;
    final int LR_SCORE_LIMIT = 50;

    //Minimum area of particles to be considered
    final int AREA_MINIMUM = 150;

    //Maximum number of particles to process
    final int MAX_PARTICLES = 8;

    AxisCamera camera_;
    CriteriaCollection cc;

    public class Scores {
        double rectangularty;
        double aspectRatioVertical;
        double aspectRatioHorizontal;        
    }

    public class TargetReport {
        int verticalIndex;
        int horizontalIndex;
        boolean hot;
        double totalScore;
        double leftScore;
        double rightScore;
        double tapeWidthScore;
        double verticalScore;
    }
    

    VisionProcessor() {

    }

    public void VisionInit() {
        //this needs to be out in robot init
        camera_ = AxisCamera.getInstance();
        cc.addCriteria(MeasurementType.IMAQ_MT_AREA, AREA_MINIMUM, 65535, false);

    }

    public void update() {
         TargetReport target = new TargetReport();
         int horizontalTargets[] = new int[MAX_PARTICLES];
         int verticalTargets[] = new int[MAX_PARTICLES];
         int verticalTargetCount, horizontalTargetCount;
                 
        try {
            if (camera_.freshImage()) {
                ColorImage image = camera_.getImage();
                //convert the rbg image into a binary image only allowing certain hsv values
                BinaryImage thresholdImage = image.thresholdHSV(10, Y_IMAGE_RES, MAX_PARTICLES, Y_IMAGE_RES, Y_IMAGE_RES, Y_IMAGE_RES);
                //take the small particals out of the image
                BinaryImage filteredImage = thresholdImage.particleFilter(cc);
                
                //create array of score objects (see class created above) that is as long as there are particals in the image
                Scores scores[] = new Scores[filteredImage.getNumberParticles()];
                
                //set the counts = to zero
                horizontalTargetCount = verticalTargetCount = 0;

            }
        } catch (NIVisionException ex) {
            ex.printStackTrace();

        } catch (AxisCameraException ex) {
            ex.printStackTrace();

        }

    }

    // TODO  do stuff
}
