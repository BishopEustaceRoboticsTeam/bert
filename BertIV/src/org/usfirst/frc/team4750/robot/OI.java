package org.usfirst.frc.team4750.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team4750.robot.commands.LowerShooter;
import org.usfirst.frc.team4750.robot.commands.RaiseShooter;
//import org.usfirst.frc.team4750.robot.commands.Manipulate;
import org.usfirst.frc.team4750.robot.commands.SetShooterServo;
import org.usfirst.frc.team4750.robot.commands.SetShooterSpeed;

/**w
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

	Joystick leftStick = new Joystick(RobotValues.LEFT_JOYSTICK_USB_PORT);
	Joystick rightStick = new Joystick(RobotValues.RIGHT_JOYSTICK_USB_PORT);
	Joystick shootStick = new Joystick(RobotValues.SHOOTER_JOYSTICK_USB_PORT);

	Button servoTrigger = new JoystickButton(shootStick, 1);
	Button shooterButton = new JoystickButton(shootStick, 2);
	Button intakeButton = new JoystickButton(shootStick, 3);
	Button shooterLowerButton = new JoystickButton(shootStick, 4);
	Button shooterRaiseButton = new JoystickButton(shootStick, 5);
	
	public OI(){
		servoTrigger.whileHeld(new SetShooterServo());
		shooterLowerButton.whileHeld(new LowerShooter());
		shooterRaiseButton.whileHeld(new RaiseShooter());
		
		//An input of "true" causes the motors to spin in one direction (for shooting) and "false" causes them to spin in the opposite direction (for intaking). 
		shooterButton.whileHeld(new SetShooterSpeed(true));
		intakeButton.whileHeld(new SetShooterSpeed(false));
	}
		
	public double getRightYAxis(){
		return rightStick.getRawAxis(RobotValues.JOYSTICK_Y_AXIS);
	}
	
	public double getLeftYAxis(){
		return leftStick.getRawAxis(RobotValues.JOYSTICK_Y_AXIS);
	}
	
	public double getRightXAxis(){
		return rightStick.getRawAxis(RobotValues.JOYSTICK_X_AXIS);
	}
	
	public double getLeftXAxis(){
		return leftStick.getRawAxis(RobotValues.JOYSTICK_X_AXIS);
	}
	
	public Joystick getLeftStick(){
		return leftStick;
	}
	
	public Joystick getRightStick(){
		return rightStick;
	}
		
	public double getShooterY(){
		return shootStick.getRawAxis(RobotValues.JOYSTICK_Y_AXIS);
	}
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}
