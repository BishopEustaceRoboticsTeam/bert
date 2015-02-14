package robotCode.LED;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LED {

	private I2C arduino;
	private final int ARDUINO_ADDRESS = 4;
	private final Port PORT = Port.kOnboard;
	private final int WRITE_ADDRESS = 0x10;
	private final int READ_ADDRESS = 0x20;
	
	public LED(){
		arduino = new I2C(PORT, ARDUINO_ADDRESS);
	}
	
	public void setLEDs(LEDModes mode){
	//	if(!Done()){
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
				case RAINBOW:
					arduino.write(WRITE_ADDRESS, 5);
					break;
			}
		//}
	}	
		
	public boolean Done(){
		//ASCII for t is 116
		byte[] data = new byte[1];
		arduino.read(READ_ADDRESS, 1, data);
		SmartDashboard.putNumber("Data from arduino: ", data[0]);
		if(data[0] == 't'){
			return true;
		}
		else{
			return false;
		}
		
	}
}
