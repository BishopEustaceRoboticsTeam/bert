
package robotCode;

import robotCode.LED.LED;
import robotCode.LED.LEDModes;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.smartdashboard.*;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	static I2C arduino = new I2C(Port.kOnboard, 4);
	LED led = new LED();
	int regAddr = 0;
	int data = 1;
	
	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	
    	
    	//arduino.broadcast(regAddr, data);
    	//SmartDashboard.putBoolean("Is device There false is good", arduino.addressOnly());
    	SmartDashboard.putNumber("regAddr: ",regAddr);
    	SmartDashboard.putNumber("data: ",data);
    }

    /**
     * This funLEDModes mode = LEDModes.BLUE;
    	led.setLEDs(mode);ction is called periodically during autonomous
     */
    
    public void autonomousInit(){
    	
    }
    
    public void autonomousPeriodic() {
    	//SmartDashboard.putBoolean("LED Done",led.Done());
    	LEDModes mode = LEDModes.BLUE;
    	led.setLEDs(mode);
    }

    public void teleopInit(){
    	
    	
    	
    }
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	//arduino.addressOnly();
    	regAddr = (int) SmartDashboard.getNumber("regAddr: ");
        data = (int) SmartDashboard.getNumber("data: ");
       
        	//arduino.broadcast(regAddr, data);
        	//arduino.write(regAddr, data);
        	arduino.write(0x10, data);
        	//arduino.w
 
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
