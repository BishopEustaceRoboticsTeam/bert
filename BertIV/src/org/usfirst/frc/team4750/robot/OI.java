package org.usfirst.frc.team4750.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team4750.robot.commands.Lift;
import org.usfirst.frc.team4750.robot.commands.Manipulate;
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

	Button shooterSpeedButton = new JoystickButton(rightStick, 2);
	Button liftButton = new JoystickButton(rightStick, 2);
	Button unliftButton = new JoystickButton(leftStick, 2);
	Button raiseLiftButton= new JoystickButton(rightStick, 7);
    
	Button manipulateButton = new JoystickButton(rightStick, rightStick.getPOV());
	Button unmanipulateButton = new JoystickButton(rightStick, rightStick.getPOV());
	
	Button leftTrigger = new JoystickButton(leftStick, 1);
	Button rightTrigger = new JoystickButton(rightStick, 1);
	
	
	public OI(){
		rightTrigger.whileHeld(new SetShooterServo(true));
		liftButton.whenPressed(new Lift(true));
		unliftButton.whenPressed(new Lift(false));
		manipulateButton.whileHeld(new Manipulate(true));
		unmanipulateButton.whileHeld(new Manipulate(false));
		shooterSpeedButton.whileHeld(new SetShooterSpeed());
	}
	
	public double getRightTwist(){
		return rightStick.getRawAxis(RobotValues.JOYSTICK_TWIST_AXIS);
	}
	
	public double getLeftTwist(){
		return leftStick.getRawAxis(RobotValues.JOYSTICK_TWIST_AXIS);
	}
	
	public double getYAxis(){
		return rightStick.getRawAxis(RobotValues.JOYSTICK_Y_AXIS);
	}
	
	public Joystick getLeftStick(){
		return leftStick;
	}
	
	public Joystick getRightStick(){
		return rightStick;
	}
	
	public double getRightStickPOV(){
		return rightStick.getPOV();
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
