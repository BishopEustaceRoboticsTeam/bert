package visionpractice;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;

public class CVFilters {
    
    public static void smooth(IplImage image) {
        System.out.println("smooth image");
        cvSmooth(image, image, CV_GAUSSIAN, 5);
    }
}
