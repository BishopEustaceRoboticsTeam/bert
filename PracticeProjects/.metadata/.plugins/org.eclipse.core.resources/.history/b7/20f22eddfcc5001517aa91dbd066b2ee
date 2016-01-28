package robotcode;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;

public class Robot extends IterativeRobot {
	
	private final static int LEFT_ROLLER_PORT = 4;
	private final static int RIGHT_ROLLER_PORT = 5;
	private final static int JOYSTICK_USB_PORT = 0;
	
	Joystick joystick = new Joystick(JOYSTICK_USB_PORT);
	
	Victor leftRoller = new Victor(LEFT_ROLLER_PORT);
	Victor rightRoller = new Victor(RIGHT_ROLLER_PORT);
	
	public void robotInit() {
    
	}
	
    public void autonomousPeriodic() {

    }
    
    public void teleopInit(){
    	
    }
    
    public void teleopPeriodic() {
    	//These might supposed to be negative
        leftRoller.set(joystick.getRawAxis(1));
        rightRoller.set(joystick.getRawAxis(1));
    }
}