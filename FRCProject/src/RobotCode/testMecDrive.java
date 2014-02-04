package RobotCode;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive.MotorType.*;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;



public class testMecDrive extends IterativeRobot {
    
    //RobotDrive myDrive = new RobotDrive(1, 2, 3, 4);
    //Joystick moveStick, rotateStick;
    //Joystick rightStick = new Joystick(2);
    //Joystick leftStick = new Joystick(1);
    //Joystick controller = new Joystick(1);
    F310 remote = new F310(1);
    Victor FrontLeft;
    Victor FrontRight;
    Victor RearLeft;
    Victor RearRight;
    
    
   double kFrontLeft, kFrontRight, kRearLeft, kRearRight;
   double dFL, dFR, dRL, dRR, leftYAxis, rightYAxis;
   
   
    
    
    
    
  
    public void robotInit() {

        
        //moveStick = new Joystick(1);
        //rotateStick = new Joystick(2);
        System.out.println("WE HAVE LIFT OFF");
        FrontRight = new Victor(1);
        FrontLeft = new Victor(2);        
        RearLeft = new Victor(3);
        RearRight = new Victor(4);
        
    }
//   
//       
//      public void driveForwardBackward(){
//          
//         
//        //this is improved testing code for the mech wheels, drives the robot forward and backward
//        kFrontLeft = leftStick.getAxis(Joystick.AxisType.kY);
//        kFrontRight = leftStick.getAxis(Joystick.AxisType.kY);
//        kRearLeft = leftStick.getAxis(Joystick.AxisType.kY);
//        kRearRight = leftStick.getAxis(Joystick.AxisType.kY);
//        
//        dFL = kFrontLeft;
//        dFR = kFrontRight;
//        dRL = kRearLeft;
//        dRR = kRearRight;
//        
//        FrontLeft.set(dFL);
//        FrontRight.set(dFR);
//        RearLeft.set(dRL);
//        RearRight.set(dRR);
//        
//        
//        
//        }
//      
//        
//      public void rotateRobot(){  
//       
//        
//        
//        kFrontLeft = rightStick.getAxis(Joystick.AxisType.kX);
//        kFrontRight = rightStick.getAxis(Joystick.AxisType.kX);//needs to be inverted
//        kRearLeft = rightStick.getAxis(Joystick.AxisType.kX);
//        kRearRight = rightStick.getAxis(Joystick.AxisType.kX);//needs to be inverted
//        
//        
//        dFL = kFrontLeft;
//        dRL = kRearLeft;
//        dFR = kFrontRight/(-1);
//        dRR = kRearRight/(-1);
//        
//        FrontLeft.set(dFL);
//        FrontRight.set(dFR);
//        RearLeft.set(dRL);
//        RearRight.set(dRR);
//        
//      }
//      
      public void strafeRobot(){
//      
        kFrontLeft = remote.getLeftStickX() *-1;//leftStick.getAxis(Joystick.AxisType.kX);
        kFrontRight = remote.getLeftStickX() *-1;//leftStick.getAxis(Joystick.AxisType.kX);
        kRearLeft = remote.getLeftStickX() *-1;//leftStick.getAxis(Joystick.AxisType.kX);
        kRearRight = remote.getLeftStickX() *-1;//leftStick.getAxis(Joystick.AxisType.kX);
      
        dFL = kFrontLeft ;
        dFR = kFrontRight ;
        dRL = kRearLeft * -1;
        dRR = kRearRight * -1;
        
        FrontLeft.set(dFL);
        FrontRight.set(dFR);
        RearLeft.set(dRL);
        RearRight.set(dRR);
        
      }
      public void teleopPeriodic(){
//        leftYAxis = leftStick.getAxis(Joystick.AxisType.kY) * -1;
//        rightYAxis = rightStick.getAxis(Joystick.AxisType.kY);
//        FrontLeft.set(leftYAxis);
//        FrontRight.set(rightYAxis);
//        RearLeft.set(leftYAxis);
//        RearRight.set(rightYAxis);
//        //System.out.println(leftYAxis);
//        
//        if(leftStick.getRawButton(3)){
//            strafeRobot();
        leftYAxis =  remote.getLeftStickY() * -1;
        rightYAxis = remote.getRightStickY();
        FrontLeft.set(leftYAxis);
        FrontRight.set(rightYAxis);
        RearLeft.set(leftYAxis);
        RearRight.set(rightYAxis);
        //System.out.println(leftYAxis);
        //System.out.println(rightYAxis);
        //if(remote.getLBButton()){
            strafeRobot();
            
        }
        if(remote.getRBButton()){
        leftYAxis =  remote.getLeftStickY() * -1;
        rightYAxis = remote.getRightStickY();
        leftYAxis = leftYAxis/2;
        rightYAxis = rightYAxis/2;
        
        FrontLeft.set(leftYAxis);
        FrontRight.set(rightYAxis);
        RearLeft.set(leftYAxis);
        RearRight.set(rightYAxis);
                      
        }
        
        
        
//            
        }
//        
          
//      }
      public void autonomousPeriodic(){
;
        
        }
        //this is testing code for driving the robot with mec wheels
       // public void Drive(){    
            
    //myDrive.mecanumDrive_Polar(moveStick.getY(), moveStick.getX(), rotateStick.getTwist());
       // Timer.delay(0.01);
       // }

        
    }