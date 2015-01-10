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
import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.image.*;

//import edu.wpi.first.wpilibj.DigitalModule;
//import edu.wpi.first.wpilibj.DriverStation;
//import edu.wpi.first.wpilibj.Solenoid;
public class BERT extends IterativeRobot {

    // Robot reference frame.
    // Shooter is the front of the robot
    // PWM Port
    static final int FRONT_LEFT_PORT = 2;
    static final int FRONT_RIGHT_PORT = 1;
    static final int BACK_LEFT_PORT = 3;
    static final int BACK_RIGHT_PORT = 4;
    static final int SHOOTER_LEFT_PORT = 5;
    static final int SHOOTER_RIGHT_PORT = 6;

    // relay ports
    static final int RELAY_PORT = 1; //relay port compressor power

    // digatal IO ports
    static final int COMPRESSOR_SWITCH_PORT = 1; //Digital io port (compressor switch) 
    static final int TEAM_COLOR_PORT_NUMBER = 4;
    static final int BUMP_SWITCH_IO_PORT = 2;

    // solenoid breakout ports 
    static final int SINGLESOLENOID_PORT1 = 1;
    //static final int SINGLESOLENOID_PORT2 = 2;

    // analog ports
    static final int SONARPORT = 1;

    boolean auto_already_shot_ = false;
    boolean first_run = true;
    boolean stop = true;
    boolean state = true;
    StateEstimator state_ = new StateEstimator(SONARPORT, TEAM_COLOR_PORT_NUMBER, BUMP_SWITCH_IO_PORT);
    F310 rc_ = new F310(1);
    MecDrive drive_ = new MecDrive(rc_, state_, FRONT_LEFT_PORT, FRONT_RIGHT_PORT, BACK_LEFT_PORT, BACK_RIGHT_PORT);
    Pneumatics p = new Pneumatics(RELAY_PORT, COMPRESSOR_SWITCH_PORT);
    int solenoid1, solenoid2;
    Autonomous auto;
    Shooter shooter_ = new Shooter(SHOOTER_LEFT_PORT, SHOOTER_RIGHT_PORT, state_, p);

    boolean autonomousOrNaw = true;

    double shooting_time = 0;

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
        solenoid1 = p.addNewSingleSolenoid(SINGLESOLENOID_PORT1);
        auto = new Autonomous(state_, drive_);
        autonomousOrNaw = state_.team_color_;
        p.startCompressor();
        System.out.println("the auto state is " + autonomousOrNaw);

    }

    public void autonomousInit() {
        System.out.println("autonomousInit");
        p.startCompressor();
//        shooter_.putPickerupperDown();
//        Timer.delay(0.35);
//        shooter_.putPickerupperUp();
//        

    }

    //@override
    public void autonomousPeriodic() {
        //System.out.println("autonomousPeriodic");
        if (first_run) {
            System.out.println("First run");
            shooter_.putPickerupperDown();
            Timer.delay(0.35);
            shooter_.putPickerupperUp();
            Timer.delay(.5);
            first_run = false;

        } 
        if (auto.driveToShoot() && !auto_already_shot_) {
            System.out.println("Done driving");
            // using because we can shoot
            //putting the jumper in mean we are not going to shoot.
            if (autonomousOrNaw) {
                auto_already_shot_ = true;
                System.out.println("Shootering");
                Timer.delay(1.5);
                shooter_.putPickerupperDown();
                Timer.delay(1.0); // allow time for shooter to drop
                shooter_.shoot();

            }

        }
        shooter_.update();

    }

    //@override
    //@override
    public void disabledInit() {
        //p.stopCompressor();
        System.out.println("disabledInit");

    }

    //@override
    public void disabledPeriodic() {
        //System.out.println("disabledPeriodic");
    }

    //@override
    public void teleopInit() {
        System.out.println("teleopInit");
        p.startCompressor();

    }

    //@override
    public void teleopPeriodic() {
        //System.out.println("teleopPeriodic");

        if (rc_.getRBButton()) {
            shooter_.shoot();
        } else if (rc_.getLBButton()) {
            shooter_.pass();

        }

        // manually lower shooter
        if (rc_.getYButton()) {
            shooter_.lowerShooter();
        }

//        if (rc_.getDpadX() <= -1){
//            shooting_time -= 0.01;
//            System.out.println("Shooting time = " + shooting_time);
//            shooter_.setShootingTimer(shooting_time);
//            Timer.delay(0.1);
//        }
//          if (rc_.getDpadX() >= 1){
//            System.out.println("Shooting time = " + shooting_time);
//            shooting_time += 0.01;
//            shooter_.setShootingTimer(shooting_time);
//            Timer.delay(0.1);
//        }
        if (rc_.getXButton()) {
            state_.setDriveMode(true);
        } else if (rc_.getBButton()) {
            state_.setDriveMode(false);
        }

        if (rc_.getAButton()) {
            p.changeSolenoidState(solenoid1);
        }
        drive_.update();
        shooter_.update();

    }

    //@override
    public void testInit() {
        System.out.println("testInit");
        p.startCompressor();
    }

    //@override
    public void testPeriodic() {
//      //System.out.println("testPeriodic");
//       //System.out.println("Distance =" + state_.getDistanceMetersToWall());
//      //state_.getNXTColor();
//        //public void debug(double leftMotor, double rightMotor, boolean shoot, double time, boolean retract) 
//        if(rc_.getRBButton())a{
//            //shoot
//            shooter_.debug(0, 0, true, counter, false);
//            
//        }
//        else if(rc_.getBButton()){
//            shooter_.debug(0, 0, false, counter, true);
//            
//        }
//        else if(rc_.getYButton()){
//            counter += 0.01;
//            
//            
//        }
//        else if (rc_.getXButton()){
//            counter -= 0.01;
//            
//        }
//        shooter_.debug(rc_.getLeftStickY(), -1*rc_.getLeftStickY(), false, 0, false);
//        System.out.println("Timer = " + counter);
//        
//        
//        
        shooter_.debug(rc_.getLeftStickY(), -1 * rc_.getLeftStickY(), false, 0, false);
    }
}
