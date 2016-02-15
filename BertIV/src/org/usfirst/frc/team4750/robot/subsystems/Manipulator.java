package org.usfirst.frc.team4750.robot.subsystems;

import org.usfirst.frc.team4750.robot.RobotValues;
import org.usfirst.frc.team4750.robot.commands.Manipulate;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Manipulator extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Victor manipulatorMotor = new Victor(RobotValues.MANIPULATOR_MOTOR_PORT);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new Manipulate());
    }
    
    public void manipulatorUp(){
    	manipulatorMotor.set(RobotValues.DEFAULT_MANIPULATOR_MOTOR_SPEED);
    }
    
    public void manipulatorDown(){
    	manipulatorMotor.set(-RobotValues.DEFAULT_MANIPULATOR_MOTOR_SPEED);
    }
    
    
}

