package robotCode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


//high level class that controls the steps for the lift and place actions
//uses the pneumatics class to do so.
public class Lifter {
	
	//states for this class:
	private States.LifterStates currentLifterState = States.LifterStates.READY;
	
	//steps for stacking a tote or bin:
	private States.Stack currentStackState = States.Stack.LOWER;
	
	//steps for placing a bin
	private States.Place currentPlaceState = States.Place.LOWER;
	
	//var to keep track if the current task is completed or not
	private boolean stateCompleted = true;
	
	//the pneumatics class
	private Pneumatics pneu;
	
	public Lifter(Pneumatics _pneu){
		pneu = _pneu;
	}
	
	private void completed(){
		stateCompleted = true;
		currentLifterState = States.LifterStates.READY;
	}
	
	private void notCompleted(){
		stateCompleted = false;
	}
	
	public boolean Done(){
		return stateCompleted;
	}
	
	
	public void update(){
		switch(currentLifterState){
			case READY:
				break;
			case RESET:
				reset();
				break;
			case STACK:
				stack();
				break;
			case PLACE:
				place();
				break;
			}	
		}
	
	
	public void startReset(){
		currentLifterState = States.LifterStates.RESET;
		notCompleted();
	}
	
	private void reset() {
			pneu.lower();
			completed();
		
	}

	public void startStack(){
		if(Done()){
			currentLifterState = States.LifterStates.STACK;
			currentStackState = States.Stack.OPEN_ROLLERS;
			notCompleted();
		}
	}
	
	private void stack(){
		if(pneu.Done()){
			switch(currentStackState){
				case OPEN_ROLLERS:
					pneu.startRollerOut();
					currentStackState = States.Stack.RESET;
					SmartDashboard.putString("Stack", "Open Rollers");
					break;
				case RESET:
					pneu.startLifterDown();
					currentStackState = States.Stack.LIFT;
					SmartDashboard.putString("Stack", "reset");
					break;
				case LIFT:
					pneu.startLifterUp();
					currentStackState = States.Stack.LOWER;
					SmartDashboard.putString("Stack", "lift");
					break;
				case LOWER:
					pneu.startLifterDown();
					currentStackState = States.Stack.END;
					SmartDashboard.putString("Stack", "Lower");
					break;
				case END:
					completed();
					SmartDashboard.putString("Stack", "end");
					break;
			}
		}
	}		
	
	public void startPlace(){
		if(Done()){
			currentLifterState = States.LifterStates.PLACE;
			currentPlaceState = States.Place.OPEN_ROLLERS;
			notCompleted();
		}
	}
	
	private void place(){
		if(pneu.Done()){
			switch(currentPlaceState){
				case OPEN_ROLLERS:
					pneu.startRollerOut();
					currentPlaceState = States.Place.LIFT;
					SmartDashboard.putString("Place", "OpenRollers");
					break;
				case LIFT:
					pneu.startLifterUp();
					currentPlaceState = States.Place.UNLOCK;
					SmartDashboard.putString("Place", "Lift");
					break;
				case UNLOCK:
					pneu.startUnlock();
					currentPlaceState = States.Place.LOWER;
					SmartDashboard.putString("Place", "Unlock");
					break;
				case LOWER:
					pneu.startLifterDown();
					currentPlaceState = States.Place.END;
					SmartDashboard.putString("Place", "Lower");
					break;
				case END:
					completed();
					SmartDashboard.putString("Place", "End");
					break;
			}
		}
	}
}

