
package org.usfirst.frc.team4750.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.CameraServer;

public class Robot extends IterativeRobot {
    
	Joystick leftStick = new Joystick(0);
	Joystick rightStick = new Joystick(1);

	//Talon manipulator1 = new Talon(3);
	//Talon manipulator2 = new Talon(4);
	//Talon manipulatorRoller = new Talon(5);
	
	Talon frontLeftDrive = new Talon(RobotValues.FRONT_LEFT_MOTOR_PORT);
	Victor backRightDrive = new Victor(RobotValues.BACK_RIGHT_MOTOR_PORT);
	Talon frontRightDrive = new Talon(RobotValues.FRONT_RIGHT_MOTOR_PORT);
	Victor backLeftDrive = new Victor(RobotValues.BACK_LEFT_MOTOR_PORT);
	
	Talon rightRollerMotor = new Talon(RobotValues.RIGHT_ROLLER_MOTOR_PORT);
	Talon leftRollerMotor = new Talon(RobotValues.LEFT_ROLLOR_MOTOR_PORT);
	Talon shooterJointMotor = new Talon(RobotValues.SHOOTER_JOINT_MOTOR_PORT);
	Victor manipulatorMotor = new Victor(RobotValues.MANIPULATOR_MOTOR_PORT);
	
	Servo servo1 = new Servo(RobotValues.SHOOTER_SERVO_PORT);

	
	RobotDrive driveTrain = new RobotDrive(frontLeftDrive, backLeftDrive, frontRightDrive, backRightDrive);
	RobotDrive roller = new RobotDrive(leftRollerMotor, rightRollerMotor);
	
	public void robotInit() {
		 CameraServer server;

		 server = CameraServer.getInstance();
		 server.setQuality(50);
		 //the camera name (ex "cam0") can be found through the roborio web interface
		 server.startAutomaticCapture("cam0");
		 /**
		  * start up automatic capture you should see the video stream from the
		  * webcam in your FRC PC Dashboard.
		  */	    		          
	}
		    

    
    
	
    public void autonomousInit() {
    
    }

    
    public void autonomousPeriodic() {
    
    }

    
    public void teleopPeriodic() {
        
    	driveTrain.tankDrive(-leftStick.getRawAxis(RobotValues.JOYSTICK_Y_AXIS), -rightStick.getRawAxis(RobotValues.JOYSTICK_Y_AXIS));
        roller.arcadeDrive(leftStick.getRawAxis(RobotValues.JOYSTICK_Z_AXIS), 0);
        shooterJointMotor.set(rightStick.getRawAxis(RobotValues.JOYSTICK_Z_AXIS));
        
        if(rightStick.getRawButton(RobotValues.MANIPULATOR_BUTTON)){
        	manipulatorMotor.set(RobotValues.MANIPULATOR_SPEED);
        }
        else if(leftStick.getRawButton(RobotValues.MANIPULATOR_BUTTON)){
        	manipulatorMotor.set(-RobotValues.MANIPULATOR_SPEED);
        } else{
        	manipulatorMotor.set(0);
        }
        
        if(rightStick.getTrigger()){
        	servo1.set(0);
        }
        
        else{
        	servo1.set(1);
        }
        
        Timer.delay(0.005);		// wait for a motor update time
               
    }
    
    public void testPeriodic() {
    
    }
    
}