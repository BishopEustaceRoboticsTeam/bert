
//negetive value means counter 
//clockwise positive means clockwise
//each mid range should be 22 inches or less to work properly
//recalbrate the left sensor
//right one jumps around alot
//front ir need to be recal
package RobotCode;

import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Aligning {
    
    double distBetweenSensors_;
    double angle_;
    Aligning(double distBetweenSensors){
        
        distBetweenSensors_ = distBetweenSensors;
    }
   
    public double getAlingingAngle(double LeftDist, double RightDist){
        angle_ = Math.toDegrees(MathUtils.atan2(LeftDist-RightDist, distBetweenSensors_));
        return angle_;
    }
   
    public void updateAngleToDashboard(){
        SmartDashboard.putNumber("Aligning_Angle", angle_);        
    }
    
}
