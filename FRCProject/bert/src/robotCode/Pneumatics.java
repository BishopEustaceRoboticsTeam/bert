package robotCode;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Timer;

public class Pneumatics  {
	Solenoid lifter;
	Solenoid locker;
	
	Compressor compressor;
	
	public Pneumatics(int lifterPort, int lockPort){
		lifter = new Solenoid(lifterPort);
		locker = new Solenoid(lockPort);
		compressor = new Compressor();
	}
	//method to engage the locking mech
	public void lock(){
		locker.set(true);
	}
	
	//method to unlock the locking mech
	public void unlock(){
		locker.set(false);
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
	
	//method that will use the above methods to lift a tote and lock it and put the lifter down
	public void stack(){
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
}