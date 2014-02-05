package RobotCode;

// Only uncomment the ones we need in this class!
//import edu.wpi.first.wpilibj.AnalogModule;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.Dashboard;
//import edu.wpi.first.wpilibj.SimpleRobot;
//import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.Servo;
//import edu.wpi.first.wpilibj.camera.AxisCamera;
//import edu.wpi.first.wpilibj.camera.AxisCameraException;
//import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.IterativeRobot;
//import edu.wpi.first.wpilibj.image.*;

//import edu.wpi.first.wpilibj.DigitalModule;
//import edu.wpi.first.wpilibj.DriverStation;
//import edu.wpi.first.wpilibj.Solenoid;

public class BERT extends IterativeRobot {

    // SENSOR PORTS
    //static final int IRLEFTPORT = 1;
    //static final int IRRIGHTPORT = 2;
    //static final int IRFRONTPORT = 3;
    // static final int IRBACKPORT = 4;
    //static final int IRTESTPORT = 6;
    //static final int DIGITALSENSORPORT = 1;

    // DO NOT EDIT NEXT THREE LINES
    boolean stop = true;
    StateEstimator state_ = new StateEstimator();
    F310 rc_ = new F310(1);

    // Constructor, gets called before robotInit().
    BERT() {
        // Need to disable this if we are not running the watchdog.
        //System.out.println("Disabling the Safety");
        //drive.setSafetyEnabled(false);
    }

    public void robotInit() {
        System.out.println("Hello. This is BERT. What would you like today: TeleOp or Autonomous?");
        // SmartDashboard.putDouble("Please Work! ", Pizza.getAlingingAngle(sensor.getIRLeftInches(), sensor.getIRRightInches()));
        //Timer.delay(10.0);
        //camera = AxisCamera.getInstance();
        //camera.writeResolution(AxisCamera.ResolutionT.k640x480);
    }


    //@override
    public void autonomousPeriodic() {
        //System.out.println("autonomousPeriodic");     


    }

    //@override
    public void autonomousInit() {
        System.out.println("autonomousInit");

	// TODO: push this to a separate class
	/*
        if (camera.freshImage()) {
            try {
                testImage = camera.getImage();
            } catch (AxisCameraException AxisException) {
                System.out.println("AxisException");

            } catch (NIVisionException NIException) {
                System.out.println("AxisException");

            }

            try {
                testImage.write("picture");
            } catch (NIVisionException NIException) {
                System.out.println("NIVisionException");

            }
	    }*/
    }
    
    
    //@override
    public void disabledInit() {
        System.out.println("disabledInit");
    }

    //@override
    public void disabledPeriodic() {
        //System.out.println("disabledPeriodic");
    }

    //@override
    public void teleopInit() {
        System.out.println("teleopInit");
    }

    //@override
    public void teleopPeriodic() {
        //System.out.println("teleopPeriodic");
    }

    //@override
    public void testInit() {
        System.out.println("testInit");
    }

    //@override
    public void testPeriodic() {
        //System.out.println("testPeriodic");
    }
}
