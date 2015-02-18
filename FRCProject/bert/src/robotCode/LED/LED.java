package robotCode.LED;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


//A simple class to set the LED mode of the arduino
public class LED {

	private static final Port PORT = Port.kMXP; //the arduino is using the mxp port
	private static final int ARDUINO_ADDRESS = 4; //address of the arduino
	
	
	private static final int WRITE_ADDRESS = 0x10; //the write address of the arduino hex 10 is dec 16
	
	private static I2C arduino = new I2C(PORT, ARDUINO_ADDRESS);
	
	public LED(){
		
	}
	
	//The i2c.write() command will send two bytes of information the first is the address
	//the second is the data that you want to send. this means the arduino should expect two bytes every time
	//it receives data.
	public static void setLEDs(LEDModes mode){

			switch(mode){
				case BLUE:
					arduino.write(WRITE_ADDRESS, 0);
					break;
				case YELLOW:
					arduino.write(WRITE_ADDRESS, 1);
					break;
				case GREEN:
					arduino.write(WRITE_ADDRESS, 2);
					break;
				case PURPLE:
					arduino.write(WRITE_ADDRESS, 3);
					break;
				case RED:
					arduino.write(WRITE_ADDRESS, 4);
					break;
				//when the robot is done auto put the arduino in rainbow mode
				//this will start the light show
				case RAINBOW:
					arduino.write(WRITE_ADDRESS, 5);
					break;
				//in the event of an error with the auto mode display the error lights
				case ERROR:
					arduino.write(WRITE_ADDRESS, 101); //ascii for 'e'
					break;
			}
	}	
		

}
