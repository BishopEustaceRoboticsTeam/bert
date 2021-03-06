
package org.usfirst.frc.team4750.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;





import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4750.robot.commands.DriveStraight;
import org.usfirst.frc.team4750.robot.commands.LowBarTurn;
import org.usfirst.frc.team4750.robot.commands.LowerShooterDrive;
//import org.usfirst.frc.team4750.robot.commands.SetAimAngle;
import org.usfirst.frc.team4750.robot.subsystems.DriveTrain;
//import org.usfirst.frc.team4750.robot.subsystems.Manipulator;
import org.usfirst.frc.team4750.robot.subsystems.Shooter;
import org.usfirst.frc.team4750.robot.subsystems.ShooterServo;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;

    Command autonomousCommand;
    
    //Subsystems:
    //public static final Manipulator manipulator = new Manipulator();
    public static final Shooter shooter = new Shooter();
    public static final ShooterServo shooterServo = new ShooterServo();
    public static final DriveTrain driveTrain = new DriveTrain();
    
    DigitalInput autoSwitch1 = new DigitalInput(RobotValues.AUTO_SWITCH_PORT_1);
    DigitalInput autoSwitch2 = new DigitalInput(RobotValues.AUTO_SWITCH_PORT_2);
    DigitalInput autoSwitch3 = new DigitalInput(RobotValues.AUTO_SWITCH_PORT_3);
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();	
        // instantiate the command used for the autonomous period
	
		if(autoSwitch1.get() && autoSwitch2.get() && !autoSwitch3.get()){
        	autonomousCommand = new LowerShooterDrive();
        }
		
        else if(autoSwitch1.get() && !autoSwitch2.get() && !autoSwitch3.get()){
        	autonomousCommand = new DriveStraight(RobotValues.DRIVE_TIME);
        }
		
        else if (autoSwitch1.get() && !autoSwitch2.get() && autoSwitch3.get()){
        	autonomousCommand = new LowBarTurn();
        }
        
        SmartDashboard.putBoolean("Is DriveStraight executing?", false);
		SmartDashboard.putBoolean("Is JoystickDrive executing?",false);
		SmartDashboard.putBoolean("Is SetAimAngle executing?",false);
		SmartDashboard.putBoolean("Is SetShooterServo executing?",false);
        
		SmartDashboard.putBoolean("Is SetShooterSpeed executing?",false);
		SmartDashboard.putBoolean("Is Shoot executing?", false);
		
		
//		SmartDashboard.putBoolean("Has SetAimAngle.SetAimAngle() run?", false);
//		SmartDashboard.putBoolean("Has SetAimAngle.initialize() run", false);
//		SmartDashboard.putBoolean("Has SetAimAngle.execute() run?", false);
//		SmartDashboard.putBoolean("Has SetAimAngle.isFinished() run?", false);
//		SmartDashboard.putBoolean("Has SetAimAngle.interrupted() run?", false);
//		SmartDashboard.putBoolean("Has SetAimAngle.end() run?", false);
//		
		
		CameraServer server = CameraServer.getInstance();
		server.startAutomaticCapture("cam1");
		
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
    	
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
