package RobotCode;

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




//import edu.wpi.first.wpilibj.DigitalModule;
import edu.wpi.first.wpilibj.DriverStation;
//import edu.wpi.first.wpilibj.Solenoid;
//import edu.wpi.first.wpilibj.th

public class BERT extends SimpleRobot {

    // SENSOR PORTS
    static final int IRLEFTPORT = 1;
    static final int IRRIGHTPORT = 2;
    static final int IRFRONTPORT = 3;
    static final int IRBACKPORT = 4;
    static final int IRTESTPORT = 6;
    static final int DIGITALSENSORPORT = 1;
    // SERVO PORTS
    static final int FRONTSERVOPORT = 4;
    static final int BACKSERVOPORT = 5;
    // SENSOR PARAMS
    static final double ROBOTBASEWIDTH = 17.75;// in inches
    // DO NOT EDIT NEXT THREE LINES
    //DigitalInput goalFinder = new DigitalInput(DIGITALSENSORPORT);
    RobotDrive drive = new RobotDrive(1, 2); //Maps to DSC ports
    Joystick leftStick = new Joystick(1); //Logitech setup in Driver Station
    Joystick rightStick = new Joystick(2); //Logitech setup in Driver Station
    Jaguar motorOne = new Jaguar(3);
    Sensors sensor = new Sensors(IRLEFTPORT, IRRIGHTPORT, IRFRONTPORT, IRBACKPORT, DIGITALSENSORPORT, IRTESTPORT);// Reference to Sensor Class: IRLeftPort, IRRightPort, IRFrontPort, IRBackPort, TestPort
    Servo disk_stopper = new Servo(FRONTSERVOPORT);
    Servo disk_stopper_two = new Servo(BACKSERVOPORT);
    AxisCamera camera;
    Timer testTimer = new Timer();
    Aligning Pizza = new Aligning(ROBOTBASEWIDTH);
    Autonomy autoBert = new Autonomy();
    boolean stop = true;
    Timer updateTimer = new Timer();

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
        //camera = AxisCamera.getInstance();
        //camera.writeResolution(AxisCamera.ResolutionT.k320x240);
    }

    // run 'autonomous' mode in driver station
    public void autonomous() {

        System.out.println("mode: autonomous");
        double duration = 15.0;
        double entry_angle = 0.0;
        
        while (isEnabled() && isAutonomous()) {

//            entry_angle = Pizza.getAlingingAngle(sensor.getIRLeftInches(), sensor.getIRRightInches());
//            System.out.println(entry_angle);

            // send values to the dashboard
//            if (autoBert.DriveStraight(sensor.getIRLeftInches(), sensor.getIRRightInches(), sensor.getIRFrontInches(), drive)) {
//                //System.out.println("Distance good to drop!");
//                
//                    //System.out.println("There is a Goal! Release all");
//                    autoBert.dropDisks(4, disk_stopper, disk_stopper_two);
//                    break;
//                
//
//            }
             if (autoBert.noSensorAuto(drive)) {
                //System.out.println("Distance good to drop!");
                
                    //System.out.println("There is a Goal! Release all");
                    autoBert.dropDisks(4, disk_stopper, disk_stopper_two);
                    break;
                

            } 
            Pizza.updateAngleToDashboard();
            sensor.updateIRSensorsDashboard();
            
            //used to delay the updating of the dashboard
            Timer.delay(0.25);

//            //drive.drive();
//
//
//
//            // turn the vehicle 90 degrees righ
//            drive.drive(-0.4, 0);
//            Timer.delay(1.0); //good for testing
//            drive.drive(0.0, 0.0);
        }
        System.out.println("ENDING AUTO!");
    }

    //run 'teleoperated' in driver station   
    public void operatorControl() {

        System.out.println("mode: operatorControl");

        while (isOperatorControl() && isEnabled()) {

            //System.out.println("Test value = " + sensor.getTestSensor() + " " + sensor.getTestSensorAvg());

            //DO NOT EDIT FOLLOWING LINE
            drive.tankDrive(rightStick, leftStick);//should be left and right, but we're driving backwards
            Timer.delay(0.20);
            //used to delay the updating of the dashboard
            Pizza.getAlingingAngle(sensor.getIRLeftInches(), sensor.getIRRightInches());
            Pizza.updateAngleToDashboard();
            sensor.updateIRSensorsDashboard();
            //sensor.printAccelData();
            //sensor.printGyroData(); 


            if (leftStick.getTrigger()) {
               // autoBert.dropDiskBool =true;
                autoBert.state = 1;
                System.out.println("Release all");
               autoBert.dropDisks(4, disk_stopper, disk_stopper_two);// not needed if new code works
            } else if (leftStick.getRawButton(3)) {
                System.out.println("Release one");
                autoBert.state2 = 1;
                autoBert.dropDisks(1, disk_stopper, disk_stopper_two);// not needed if new code works
            }
           //autoBert.timeStopDisk(4, disk_stopper, disk_stopper_two);
           //autoBert.timeStopDisk(1, disk_stopper, disk_stopper_two);


        }

    }

    public void test() {

        System.out.println("mode: Test");
        TestDrive test_drive = new TestDrive(drive);
        
        //testTimer.start();
        
        while (isTest() && isEnabled()) {
            System.out.println(", " + sensor.getIRLeftInches() + ", " + sensor.getIRRightInches() + ", " +sensor.getIRFrontInches() );
            
            test_drive.driveTenMeters();
        }
    }
}