package robotCode;

public class Actuators {
	
	private States.Actuator currentActuatorState = States.Actuator.READY;
	private States.Stack currentStackState = States.Stack.LOWER;
	private States.Place currentPlaceState = States.Place.LOWER;
	private boolean stateCompleted = true;
	private Pneumatics pneu;
	
	public Actuators(Pneumatics _pneu){
		pneu = _pneu;
	}
	
	private void completed(){
		stateCompleted = true;
		currentActuatorState = States.Actuator.READY;
	}
	
	private void notCompleted(){
		stateCompleted = false;
	}
	
	public boolean Done(){
		return stateCompleted;
	}
	
	
	public void update(){
		switch(currentActuatorState){
			case READY:
				break;
			case STACK:
				stack();
				break;
			case PLACE:
				place();
				break;
			}	
		}
		
	public void startStack(){
		if(Done()){
			currentActuatorState = States.Actuator.STACK;
			notCompleted();
		}
	}
	
	private void stack(){
		if(pneu.Done()){
			switch(currentStackState){
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
			currentActuatorState = States.Actuator.PLACE;
			notCompleted();
		}
	}
	
	private void place(){
		if(pneu.Done()){
			switch(currentPlaceState){
				case LIFT:
					pneu.startLifterUp();
					break;
				case UNLOCK:
					pneu.startUnlock();
					break;
				case LOWER:
					pneu.startLifterDown();
					break;
				case END:
					completed();
					break;
			}
		}
	}
}

