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

public class Lifter extends Subsystem{
	Victor lifterMotor = new Victor(RobotValues.LIFTER_MOTOR);
	
	DigitalInput top = new DigitalInput(RobotValues.DIGITAL_INPUT_CHANNEL_U);
	DigitalInput bottom = new DigitalInput(RobotValues.DIGITAL_INPUT_CHANNEL_D);
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}

	public void setLifterMotorSpeed(double speed){
		lifterMotor.set(speed);
	}

	public boolean isLifterUp(){
		return (top.get());
	}

	public boolean isLifterDown(){
		return (bottom.get());
	}

}
