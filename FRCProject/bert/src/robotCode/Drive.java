package robotCode;
//This class will handle moving the robot

//imports
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;

public class Drive {
	//global class vars go here
	final int WHEEL_RADIUS = 3; //in inches
	final double pi = 3.1459;
	F310 remote;
	Talon frMotor, flMotor, brMotor, blMotor; 
	RobotIO robotIO;
	 
	RobotDrive driver;
	public Drive(F310 _rc, RobotIO _IO){
		//constructor
		frMotor = new Talon(RobotValues.FRONT_RIGHT_MOTOR);
		flMotor = new Talon(RobotValues.FRONT_LEFT_MOTOR);
		brMotor = new Talon(RobotValues.BACK_RIGHT_MOTOR);
		blMotor = new Talon(RobotValues.BACK_LEFT_MOTOR);
		remote = _rc;
		robotIO = _IO;
		driver = new RobotDrive(flMotor, blMotor, frMotor, brMotor);
		driver.setSafetyEnabled(false);
	}
	//controlleDrive method that needs to be called in teleop periodic
	//it will drive the robot using the f310 remote
		public void controllerDrive(){
     		driver.arcadeDrive(-remote.getLeftStickY(), -remote.getLeftStickX());
			
		}
		public void setsafety(boolean enable){
			driver.setSafetyEnabled(enable);
		}

	
	//straight(double speed) method that will drive the robot straight using encoders
	//it will take an input of a double between -1 - 1. 1: full forward  -1: full back
	
	//rightAngleTurn(var leftOrRight) //this will use the encoders to make
	//a perfect right angle turn //need to decide what var type to use. enum? int? boolean
	public void rightAngleTurn(boolean left){
		
	}
	//turn(int degree) //degree is 0-360 that will turn the robot using the encoders
	//to the specified angle from the start position. counter clockwise! (90 is to the left)
	
}
