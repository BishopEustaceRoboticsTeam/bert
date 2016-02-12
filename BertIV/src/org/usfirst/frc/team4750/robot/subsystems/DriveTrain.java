
package org.usfirst.frc.team4750.robot.subsystems;

import org.usfirst.frc.team4750.robot.RobotValues;
import org.usfirst.frc.team4750.robot.commands.JoystickDrive;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.RobotDrive;
/**
 *
 */
public class DriveTrain extends Subsystem {
    
	Talon frontRightMotor = new Talon(RobotValues.FRONT_RIGHT_MOTOR);
	Talon backRightMotor = new Talon(RobotValues.BACK_RIGHT_MOTOR);
	Victor frontLeftMotor = new Victor(RobotValues.FRONT_LEFT_MOTOR);
	Victor backLeftMotor = new Victor(RobotValues.BACK_LEFT_MOTOR);
	
	private RobotDrive driver = new RobotDrive(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor);
	
	
	// Put methods for controlling this subsystem
    // here. Call these from Commands.

	public void controllerDrive(Joystick left, Joystick right){
		driver.tankDrive(left, right);
	}
	
	public void stop(){
		driver.drive(0,0);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new JoystickDrive());
    	
    }
}

