package robotCode;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Rollers {

	private Victor left_roller, right_roller;
	private Joystick joy;	
	private RobotDrive roller_controller;
	private boolean stateCompleted = true;
	private Timer timer_ = new Timer();
	private final double ROLLER_IN_TIME = 5;
	private final double ROLLER_OUT_TIME = 5;
	
	private States.Rollers currentRollerState = States.Rollers.CONTROLLED;

	public Rollers(Joystick _joy){
		left_roller = new Victor(RobotValues.LEFT_ROLLER_PORT);
		right_roller = new Victor(RobotValues.RIGHT_ROLLER_PORT);
		roller_controller = new RobotDrive(left_roller, right_roller);
		joy = _joy;
	
	}

	public void update(){
		
		switch(currentRollerState){
			case CONTROLLED:
				SmartDashboard.putString("Roller:", "Controlled");
				rollerControl();
				break;
			case IN:
				SmartDashboard.putBoolean("Roller:", true);
				rollerIntake();
				break;
			case OUT:
				SmartDashboard.putBoolean("Roller:", false);
				rollerEject();
				break;
		}
	
	}

	private void notCompleted(){
		stateCompleted = false;
	}
	
	private void completed(){ 
		stateCompleted = true;
		currentRollerState = States.Rollers.CONTROLLED;
		timer_.stop();
	}
	
	public boolean Done(){
		return stateCompleted;
	}
	
	public void startRollerControl(){
		currentRollerState = States.Rollers.CONTROLLED;
		completed();		
	}
	
	public void rollerControl(){
		roller_controller.arcadeDrive(-joy.getRawAxis(1), joy.getRawAxis(0));
	}
	
	public void startRollerIntake(){
		if(Done()){
			currentRollerState = States.Rollers.IN;
			timer_.reset();
			timer_.start();
			notCompleted();
		}
	}
	
	private void rollerIntake(){
		if(timer_.get() <= ROLLER_IN_TIME){
			left_roller.set(0.75);
			right_roller.set(-0.75);
		}
		
		else{
			completed();
		}
	}
	
	public void startRollerEject(){
		if(Done()){
			currentRollerState = States.Rollers.OUT;
			timer_.reset();
			timer_.start();
			notCompleted();
		}	
	}
	
	private void rollerEject(){
		if(timer_.get() <= ROLLER_OUT_TIME){
			left_roller.set(-0.75);
			right_roller.set(0.75);
		}
		else{
			completed();
		}
	}

}
