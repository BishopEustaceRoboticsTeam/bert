









package RobotCode;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.*;

/**
 *
 * @author Robotics
 */
public class Autonomy {

    static final double[] TURN90DLEFT = {1.0, -1.0};
    static final double[] TURN90DRIGHT = {1.0, 1.0};
    static final double[] TURN45DLEFT = {1.0, 0.75};
    static final double[] TURN45RIGHT = {1.0, 0.75};
    static final double FRONTSERVOOPEN = 0.4;
    static final double FRONTSERVOCLOSE = 1.1;
    static final double BACKSERVOOPEN = 0.0;
    static final double BACKSERVOCLOSE = 0.6;
    static final double SHORTRANGEUPPER = 30;
    static final double SHORTRANGELOWER = 0.7;
    static final double LONGRANGEUPPER = 320;
    static final double LONGRANGELOWER = 21;
    static final double NOSENSORTIME = 6.0; // time in seconds
    static final boolean dropDiskBool = false;
    static final boolean dropDiskBool2 = false;
    int state = 0; // state 0 = do nothing, state 1 = button is pressed, state 2 = check for time
    int state2 = 0; // state 0 = do nothing, state 1 = button is pressed, state 2 = check for time
    Timer diskStopTimer = new Timer();
    //static final int DROPMULTIDISK_DO_NOTHING = 0;
    //static final int DROPMULTIDISK_TRIG_IS_PRESSED = 1;
    //static final int DROPMULTIDISK_CHECKING_TIMER = 2;
    
    //static final int DROPONEDISK_DO_NOTHING = 0;
    //static final int DROPONEDISK_BUTTON_IS_PRESSED = 1;
    //static final int DROPONEDISK_CHECKING_TIMER = 2;
    
    

    
    public boolean DriveStraight(double IRLeft, double IRRight, double IRFront, RobotDrive drive) {

        double midIRDistance = (IRRight + IRLeft)/2;
        double distance = 0.0;
        if(midIRDistance >= SHORTRANGELOWER && midIRDistance <= SHORTRANGEUPPER){
            distance = midIRDistance;
            
        }
        else if(IRFront >= LONGRANGELOWER && IRFront <= LONGRANGEUPPER){
            distance = IRFront;
            
        }
        else{
            
            return false;
        }
        
        if(distance > 100){
            //continue driving straight
            //System.out.println(">100");
            SmartDashboard.putString("Autonomy stage = ", ">100");
            drive.drive(-0.30, 0.0);
        } else if (distance <= 100 && distance > 30) {
            //System.out.println("<100 >20");
            SmartDashboard.putString("Autonomy stage = ", "<100 & >20");
            //closer slow down
            drive.drive(-0.30, 0);
        } else if (distance <= 30 && distance > 10) {
            //System.out.println("<20 >10");
            SmartDashboard.putString("Autonomy stage = ", "<20 & >10");
            //switch using the mid ir
            drive.drive(-0.25, 0);
        } else if (distance <= 10 && distance > 2.5) {
            //we are getting close!
            //System.out.println("<10 >2.5");
            SmartDashboard.putString("Autonomy stage = ", "<10 & >2.5");
            drive.drive(-0.2, 0.0);
        } else if (distance <= 2.5) {
            //System.out.println("Dumping!");
            SmartDashboard.putString("Autonomy stage = ", "Dumping!");
            //speed to algin and stop down
            drive.drive(-0.5, 0);
            Timer.delay(0.2);
            drive.drive(0.0, 0.0);
            
            return true;

        } 
        return false;
    }

    public boolean Simplealigning(double IRLeft, double IRRight, double IRFront, RobotDrive drive) {
        //get average of small ir distance sensors
        double midIRDistance = (IRLeft + IRRight) / 2;
        drive.drive(0.50, 0.50); //drive robot straight
        //check distance 
        //distance > 24 in (loop)
        //10<dist<24 
        //if (midIRDistance <= 10) {
        //5<dist<10
        //drive slowly
        // drive.drive(0.20, 0.20);
        //loop to check distance 
        //dist<5
        //drop frisbees
        //check angle
        //-10<angle<10
        //loop to check distane 
        //angle>10
        //kturn right 
        //loop to check distance 
        //angle<-10
        //kturn left
        //loop to check distance 
        if (IRFront <= 40) {
        }

        return false;
    }

    public void dropDisks(int numDisks, Servo frontServo, Servo backServo) {

        if (numDisks >= 4) {

            frontServo.set(FRONTSERVOOPEN);
            backServo.set(BACKSERVOOPEN);
            Timer.delay(3.0);
            frontServo.set(FRONTSERVOCLOSE);
            //backServo.set(BACKSERVOCLOSE);

        } else if (numDisks == 1) {

            backServo.set(BACKSERVOCLOSE);
            Timer.delay(0.09);
            frontServo.set(FRONTSERVOOPEN);
            Timer.delay(0.8);  // wait for 1 disk to drop
            frontServo.set(FRONTSERVOCLOSE);
            Timer.delay(0.8);   // is this necessary?  (garrett,021613)
            backServo.set(BACKSERVOOPEN);
            Timer.delay(0.9);
            backServo.set(BACKSERVOCLOSE);

        } else {
            System.out.println("[AUTONOMY] WARN - invalid number of disks entered");
        }

    }
    void timeStopDisk(int numDisks, Servo frontServo, Servo backServo) {
        if (numDisks == 4) {
            if (state == 1 && (state2 != 2 && state2 != 1)) {
                diskStopTimer.stop(); // may be need if button is pressed alot
                diskStopTimer.reset(); // same as above
                diskStopTimer.start();
                state = 2;
            }
        } else if (state == 2 && (state2 != 2 && state2 != 1)) {

            frontServo.set(FRONTSERVOOPEN);
            backServo.set(BACKSERVOOPEN);
            //Timer.delay(3.0);
            if (diskStopTimer.get() > 3000) {
                frontServo.set(FRONTSERVOCLOSE);
                //backServo.set(BACKSERVOCLOSE); // we might want this to protect the front servo
                diskStopTimer.stop();
                diskStopTimer.reset();
                state = 0;
            }

        } //backServo.set(BACKSERVOCLOSE);
        else if (numDisks == 1) {
            if (state2 == 1 && (state != 2 && state != 1)) {
                diskStopTimer.stop(); //may not be needed
                diskStopTimer.reset(); //may not be needed
                diskStopTimer.start();
                state2 = 2;
            }
            if (state2 == 2 && (state != 2 && state != 1)) {
                backServo.set(BACKSERVOCLOSE);
                if (diskStopTimer.get() > 90000 && diskStopTimer.get() < 890000) {
                    frontServo.set(FRONTSERVOOPEN);
                } //Timer.delay(0.8);  // wait for 1 disk to drop
                else if (diskStopTimer.get() >= 890000 && diskStopTimer.get() < 1690000) {
                    frontServo.set(FRONTSERVOCLOSE);
                } //Timer.delay(0.8);   // is this necessary?  (garrett,021613)
                else if (diskStopTimer.get() >= 1690000 && diskStopTimer.get() < 2490000) {
                    backServo.set(BACKSERVOOPEN);
                } //Timer.delay(0.9);
                else if (diskStopTimer.get() >= 3390000 && diskStopTimer.get() < 4000000) {
                    backServo.set(BACKSERVOCLOSE);
                    state2 = 0;
                    diskStopTimer.stop();
                    diskStopTimer.reset();
                }
            }


        } else {
            System.out.println("[AUTONOMY] WARN - invalid number of disks entered/timer error");
        }
        

    }
    public boolean noSensorAuto(RobotDrive drive){
        drive.drive(-0.45, 0);
        Timer.delay(NOSENSORTIME);
        drive.drive(0.0, 0.0);
        
        return true;
    }
}
