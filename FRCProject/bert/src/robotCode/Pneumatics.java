package robotCode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Timer;


public class Pneumatics  {
	private Solenoid lifter;
	private Solenoid locker;
	
	private Compressor compressor;
	private boolean taskCompleted = true;
	private boolean stateCompleted = true;
	private States.Pneumatics currentPneumaticState = States.Pneumatics.READY;
	private States.Stack currentStackState = States.Stack.LOWER;
	private States.Place currentPlaceState = States.Place.LOWER;
	private DigitalInput closedReadSwitch = new DigitalInput(RobotValues.CLOSED_READ_SWITCH_PORT);
	private DigitalInput openReadSwitch = new DigitalInput(RobotValues.OPEN_READ_SWITCH_PORT);
	
	public Pneumatics(){
		lifter = new Solenoid(RobotValues.LIFTER_SOLENOID_PORT);
		locker = new Solenoid(RobotValues.LOCK_SOLENOID_PORT);
		compressor = new Compressor();
	}
	
	//this is the update method
	public void update(){
		switch(currentPneumaticState){
			case READY:
				break;
			case UNLOCK:
				unlock();
				break;
			case LOCK:
				lock();
				break;
			case LIFTER_UP:
				lifterUp();
				break;
			case LIFTER_DOWN:
				lifterDown();
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
	
	
	//method to engage the locking mech
	public void lock(){
		locker.set(true);
		completed();
	}
	
	//method to unlock the locking mech
	public void unlock(){
		locker.set(false);
		completed();
	}
	
	//method to raise the lifter
	public void lift(){
		lifter.set(true);
	}
	
	//method to lower the lifter
	public void lower(){
		lifter.set(false);
	}
	
	//method that gets the current state of lock. true = locked false = unlocked
	public boolean getLock(){
		return locker.get();
	}
	
	//method to get the state of the lifter (note this returns true if the solenoid is on and false for off)
	//it does not tell you if the lifter fully up or full down
	public boolean getLifter(){
	    return lifter.get();
	}
	
	public void startLifterUp(){
		if(Done()){
			currentPneumaticState = States.Pneumatics.LIFTER_UP;
			notCompleted();
		}
	}	
	
	private void lifterUp(){
		lifter.set(false);
		if(closedReadSwitch.get()){
			completed();
		}
	}
		
	
	//method that will use the above methods to lift a tote and lock it and put the lifter down
	private void stackOld(){
		if(getLock()){
			unlock();
		}
		
		//lift up
		lift();
		
		//lock when lifter is all the way up
		Timer.delay(1); //replace with when the sensor says the lifter has reached its position
		
		//lock
		lock();
		Timer.delay(.25);//add a short delay
		
		//put the lift back down
		lower();
	}
	
	public void startLifterDown(){
		if(Done()){
			currentPneumaticState = States.Pneumatics.LIFTER_DOWN;
			notCompleted();
		}
	}
	
	private void lifterDown(){
		lifter.set(true);
		if(openReadSwitch.get()){
			completed();
		}
	}
}