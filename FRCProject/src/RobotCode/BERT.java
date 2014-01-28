package RobotCode;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.AnalogModule;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Dashboard;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.image.*;



//import edu.wpi.first.wpilibj.DigitalModule;
import edu.wpi.first.wpilibj.DriverStation;
//import edu.wpi.first.wpilibj.Solenoid;
//import edu.wpi.first.wpilibj.th

public class BERT extends IterativeRobot {

    // SENSOR PORTS
    static final int IRLEFTPORT = 1;
    static final int IRRIGHTPORT = 2;
    static final int IRFRONTPORT = 3;
   // static final int IRBACKPORT = 4;
    static final int IRTESTPORT = 6;
    static final int DIGITALSENSORPORT = 1;
    // SERVO PORTS
  //  static final int FRONTSERVOPORT = 4;
    static final int BACKSERVOPORT = 5;
    // SENSOR PARAMS
    static final double ROBOTBASEWIDTH = 17.75;// in inches
    // DO NOT EDIT NEXT THREE LINES
    //DigitalInput goalFinder = new DigitalInput(DIGITALSENSORPORT);
    RobotDrive drive = new RobotDrive(1, 2); //Maps to DSC ports
    Joystick leftStick = new Joystick(1); //Logitech setup in Driver Station
    Joystick rightStick = new Joystick(2); //Logitech setup in Driver Station
    Jaguar motorOne = new Jaguar(3);
   // Sensors sensor = new Sensors(IRLEFTPORT, IRRIGHTPORT, IRFRONTPORT, IRBACKPORT, DIGITALSENSORPORT, IRTESTPORT);// Reference to Sensor Class: IRLeftPort, IRRightPort, IRFrontPort, IRBackPort, TestPort
    //Servo disk_stopper = new Servo(FRONTSERVOPORT);
    Servo disk_stopper_two = new Servo(BACKSERVOPORT);
    AxisCamera camera;
    Timer testTimer = new Timer();
    Aligning Pizza = new Aligning(ROBOTBASEWIDTH);
    Autonomy autoBert = new Autonomy();
    boolean stop = true;
    Timer updateTimer = new Timer();
    StateEstimator state_; 
    

//    LiftControl actuator_left = new LiftControl();
//    LiftControl actuator_right = new LiftControl();
    //Dashboard dash = DriverStation.getInstance().getDashboardPackerLow();
    // constructor
    BERT() {
        // need to disable this if we are not running the watchdod
        System.out.println("Disabling the Safety");
        drive.setSafetyEnabled(false);
    }

    public void robotInit() {

        // SmartDashboard.putDouble("Please Work! ", Pizza.getAlingingAngle(sensor.getIRLeftInches(), sensor.getIRRightInches()));

        Timer.delay(10.0);
        System.out.println("Hello. This is BERT. What would you like today: TeleOp or Autonomous?");
        camera = AxisCamera.getInstance();
        camera.writeResolution(AxisCamera.ResolutionT.k320x240);
        
    }

    // run 'autonomous' mode in driver station
    //@override
    public void autonomousPeriodic() {
       //System.out.println("autonomousPeriodic");     
    }
    public void autonomousInit(){
        System.out.println("autonomousInit");
        if(camera.freshImage()){
            
            
        }
    }
    public void disabledInit(){
        System.out.println("disabledInit");
    }
    public void disabledPeriodic(){
        //System.out.println("disabledPeriodic");
    }
    public void teleopInit(){
        System.out.println("teleopInit");
        testTimer.reset();
        testTimer.start();
    }
    public void teleopPeriodic(){
        //System.out.println("teleopPeriodic");
        /*testTimer.reset();
        testTimer.start();
        for( int i = 0; i <= 1000000; i++){
            int a = 50;
            
        }*/
        System.out.println("Time is " + testTimer.get());
    }
    public void testInit(){
        System.out.println("testInit");
    }
    public void testPeriodic(){
        //System.out.println("testPeriodic");
    }
    
        
   
    }
        
    

