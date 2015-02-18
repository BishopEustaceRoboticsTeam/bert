package robotCode;


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
		currentLifterState = States.LifterStates.STACK;
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
					currentStackState = States.Stack.LIFT;
					break;
				case LIFT:
					pneu.startLifterUp();
					currentStackState = States.Stack.LOWER;
					break;
				case LOWER:
					pneu.startLifterDown();
					currentStackState = States.Stack.END;
					break;
				case END:
					completed();
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
					break;
				case LIFT:
					pneu.startLifterUp();
					currentPlaceState = States.Place.UNLOCK;
					break;
				case UNLOCK:
					pneu.startUnlock();
					currentPlaceState = States.Place.LOWER;
					break;
				case LOWER:
					pneu.startLifterDown();
					currentPlaceState = States.Place.END;
					break;
				case END:
					completed();
					break;
			}
		}
	}
}

