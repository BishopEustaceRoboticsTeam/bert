package robotCode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Pneumatics  {
	//create all the solenoids that are used on the robot
	private Solenoid lifterPiston;
	private Solenoid locker;
	private Solenoid rollerPiston;
	
	//the compressor object
	private Compressor compressor;

	//Create all the state variables 
	private States.Pneumatics currentPneumaticState = States.Pneumatics.READY;
	private States.Stack currentStackState = States.Stack.LOWER;
	private States.Place currentPlaceState = States.Place.LOWER;
	
	//create the digital inputs for the reed swtiches
	private DigitalInput closedReedSwitch = new DigitalInput(RobotValues.CLOSED_REED_SWITCH_PORT);
	private DigitalInput openReedSwitch = new DigitalInput(RobotValues.OPEN_REED_SWITCH_PORT);
	
	//the variables to keep track if a task or state is completed
	private boolean taskCompleted = true;
	private boolean stateCompleted = true;
	
	//the timer variables
	private Timer timer = new Timer();
	private final double LIFT_UP_TIME = 4;
	private final double LIFT_DOWN_TIME = 1.5;
	private final double LOCK_TIME = .1;
	private final double UNLOCK_TIME = .3;
	private final double ROLLER_TIME = .1;
	
	
	
	public Pneumatics(){
		lifterPiston = new Solenoid(RobotValues.LIFTER_SOLENOID_PORT);
		locker = new Solenoid(RobotValues.LOCK_SOLENOID_PORT);
		compressor = new Compressor();
		rollerPiston = new Solenoid(RobotValues.ROLLER_PISTON_PORT);
		compressor.clearAllPCMStickyFaults();
	}
	
	//this is the update method
	public void update(){
		switch(currentPneumaticState){
			case READY:
				SmartDashboard.putString("Pneumatics:", "Ready");
				break;
			case UNLOCK:
				SmartDashboard.putString("Pneumatics:", "Unlock");
				unlock();
				break;
			case LOCK:
				SmartDashboard.putString("Pneumatics:", "Lock");
				lock();
				break;
			case LIFTER_UP:
				SmartDashboard.putString("Pneumatics:", "Lifter up");
				lifterUp();
				break;
			case LIFTER_DOWN:
				SmartDashboard.putString("Pneumatics:", "Lifter down");
				lifterDown();
				break;
			case ROLLER_IN:
				SmartDashboard.putString("Pneumatics:", "Roller In");
				rollerIn();
				break;
			case ROLLER_OUT:
				SmartDashboard.putString("Pneumatics:", "Roller Out");
				rollerOut();
				break;
				
				
		}
		
	}
	
	private void notCompleted(){
		stateCompleted = false;
	}
	
	private void completed(){ 
		stateCompleted = true;
		currentPneumaticState = States.Pneumatics.READY;
	}
	
	public boolean Done(){
		return stateCompleted;
	}
	
	public void startLock(){
		if(Done()){
			timer.reset();
			timer.start();
			currentPneumaticState = States.Pneumatics.LOCK;
			notCompleted();
		}
	}
	
	//method to engage the locking mech
	private void lock(){
		locker.set(false);
		if(timer.get() >= LOCK_TIME){
			timer.stop();
			completed();
		}
	}
	
	public void startUnlock(){
		if(Done()){
			timer.reset();
			timer.start();
			currentPneumaticState = States.Pneumatics.UNLOCK;
			notCompleted();
		}
	}
	
	

	//method to unlock the locking mech
	private void unlock(){
		locker.set(true);
		if(timer.get() >= UNLOCK_TIME){
			timer.stop();
			completed();
		}
	}
	
	//method to raise the lifter without any checks (override)
	public void lift(){
		lifterPiston.set(true);
		completed();
	}
	
	//method to lower the lifter without any checks (override)
	public void lower(){
		lifterPiston.set(false);
		completed();
	}
	
	//method that gets the current state of lock. true = locked false = unlocked
	public boolean getLock(){
		return locker.get();
	}
	
	//method to get the state of the lifter (note this returns true if the solenoid is on and false for off)
	//it does not tell you if the lifter fully up or full down
	public boolean getLifter(){
	    return lifterPiston.get();
	}
	
	public void startLifterUp(){
		if(Done()){
			timer.reset();
			timer.start();
			currentPneumaticState = States.Pneumatics.LIFTER_UP;
			notCompleted();
		}
	}	
	
	private void lifterUp(){
		lifterPiston.set(true);
		if(!openReedSwitch.get() || timer.get() >= LIFT_UP_TIME){
			SmartDashboard.putString("Piston", "Up");
			timer.stop();
			completed();
		}
	}
		
	public void startLifterDown(){
		if(Done()){
			timer.reset();
			timer.start();
			currentPneumaticState = States.Pneumatics.LIFTER_DOWN;
			notCompleted();
		}
	}
	
	private void lifterDown(){
		lifterPiston.set(false);
		if(!closedReedSwitch.get() || timer.get() >= LIFT_DOWN_TIME){
			SmartDashboard.putString("Piston", "Down");
			timer.stop();
			completed();
		}
	}
	
	public void startRollerIn(){
		if(Done()){
			timer.reset();
			timer.start();
			SmartDashboard.putString("Roller", "start Roller in");
			currentPneumaticState = States.Pneumatics.ROLLER_IN;
			notCompleted();
		}
	}
	
	private void rollerIn(){
		SmartDashboard.putString("Roller", "Roller in");
		rollerPiston.set(true);
		if(timer.get() >= ROLLER_TIME){
			completed();
		}
	}
	
	public void startRollerOut(){
		if(Done()){
			timer.reset();
			timer.start();
			SmartDashboard.putString("Roller", "start Roller out");
			currentPneumaticState = States.Pneumatics.ROLLER_OUT;
			notCompleted();
		}
	}
	private void rollerOut(){
		SmartDashboard.putString("Roller", "start Roller out");
		rollerPiston.set(false);
		if(timer.get() >= ROLLER_TIME){
			completed();
		}
	}
	
	
	//method that will use the above methods to lift a tote and lock it and put the lifter down
	//private void stackOld(){
	//	if(getLock()){
	//		unlock();
	//	}
		
		//lift up
	//	lift();
		
		//lock when lifter is all the way up
	//	Timer.delay(1); //replace with when the sensor says the lifter has reached its position
		
		//lock
	//	lock();
	//	Timer.delay(.25);//add a short delay
		
		//put the lift back down
	//	lower();
	//}
}