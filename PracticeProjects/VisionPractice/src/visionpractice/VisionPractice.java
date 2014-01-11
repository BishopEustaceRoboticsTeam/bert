package visionpractice;

import java.io.File;
import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;


public class VisionPractice {


    public static void main(String[] args) {
   
        // TODO: make input argument filename.
        String filename = "/home/bert5/random_image.jpg";
        
        // See if file/image actually exists.
        File f = new File(filename);
        if (!f.exists()) {
            System.out.println("ERROR: Could not find file: " + filename);
            return;
        }

        // Set up actual image structure we can pass around.
        IplImage image = cvLoadImage(filename);
        if (image == null) {
            System.out.println("ERROR: image created is null");
            return;
        }

        CVFilters.smooth(image);
        
        IplImage gray = cvCreateImage(cvSize(image.width(), image.height()), image.depth(), 1);
        CVConvert.rgbToGray(image, gray);
        cvSaveImage("gray_image.jpg", gray);
       
        // Convert to red channel only
        CVConvert.rgbToRed(image, gray);
        cvSaveImage("red_image.jpg", gray);
        
        // Save final image for debugging.
        cvSaveImage("final_image.jpg", image);
     
        // Proper cleanup of image.
        cvReleaseImage(image);

        System.out.println("that was great, thanks.");
    }
    
    
    
}
