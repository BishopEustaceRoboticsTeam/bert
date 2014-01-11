package visionpractice;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import java.nio.ByteBuffer;

public class CVConvert {
    
    public static void rgbToGray(IplImage input, IplImage output) {
        System.out.println("convert image from rgb to gray");
        cvCvtColor(input, output, CV_RGB2GRAY);
    }
    
    public static void rgbToRed(IplImage input, IplImage output) {
        System.out.println("convert color image to red channel only");
        
        // TODO: make sure output image is allocated.
        
        // There is probably an opencv function for this, but 
        // this example is to understand how images are parsed.
        
        // The Java way.
        ByteBuffer input_buf = input.getByteBuffer();
        ByteBuffer output_buf = output.getByteBuffer();
        
        int color = 2;  //0=blue, 1=green, 2=red
        
        for (int y=0; y<input.height(); ++y) { 
            for (int x=0; x<input.width(); ++x) {
                
                int index = y*input.widthStep() + x*input.nChannels() + color;
                int value = input_buf.get(index) & 0xFF;

                // Sets the pixel to a value (greyscale).
                output_buf.put(y*output.widthStep()+x, (byte)value);
            }
        }
    }
        

}
