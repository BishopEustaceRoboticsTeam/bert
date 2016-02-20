package org.usfirst.frc.team4750.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.buttons.Button;
import org.usfirst.frc.team4750.robot.OI;
import org.usfirst.frc.team4750.robot.RobotValues;
import edu.wpi.first.wpilibj.DigitalInput;

public class Manipulator extends Subsystem{
	
	//NOT DONE YET!!!!!
	
	Victor manipulatorMotor = new Victor(RobotValues.MANIPULATOR_MOTOR);
	
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}

	public void setManipulatorMotorSpeed(double speed){
		manipulatorMotor.set(speed);
	}

}
