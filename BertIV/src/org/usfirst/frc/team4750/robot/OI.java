package org.usfirst.frc.team4750.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

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



	Button right2 = new JoystickButton(rightStick, 2);
    Button right6 = new JoystickButton(rightStick, 6);
	
	Button leftTrigger = new JoystickButton(leftStick, 1);
	Button rightTrigger = new JoystickButton(rightStick, 1);
	
	public OI(){
		rightTrigger.whileHeld(new SetShooterServo(true));
		right2.whileHeld(new SetShooterSpeed());
	}
	
	public double getRightTwist(){
		return rightStick.getRawAxis(RobotValues.JOYSTICK_TWIST_AXIS);
	}
	
	public double getLeftTwist(){
		return leftStick.getRawAxis(RobotValues.JOYSTICK_TWIST_AXIS);
	}
	
	public double getRightY(){
		return rightStick.getRawAxis(RobotValues.JOYSTICK_Y_AXIS);
	}
	
	public double getLeftY(){
		return leftStick.getRawAxis(RobotValues.JOYSTICK_Y_AXIS);
	}
	
	public double getRightX(){
		return rightStick.getRawAxis(RobotValues.JOYSTICK_X_AXIS);
	}
	
	public double getLeftX(){
		return leftStick.getRawAxis(RobotValues.JOYSTICK_X_AXIS);
	}
	
	public Joystick getLeftStick(){
		return leftStick;
	}
	
	public Joystick getRightStick(){
		return rightStick;
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
