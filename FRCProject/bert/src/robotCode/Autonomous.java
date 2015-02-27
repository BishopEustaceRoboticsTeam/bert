package robotCode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robotCode.LED.*;
//class to 
public class Autonomous {
	private final double DISTANCE_TO_AUTO_ZONE = 2.75;  //2.7178 is the exact value
	private final double DISTANCE_FROM_WALL_TO_AUTO_ZONE = 4.1402;
	private final double DISTANCE_TO_TOTE = 0.2; 
	private final double DISTANCE_TO_BIN = 0.25;

	private Drive drive;
	private Lifter lifter;
	private Pneumatics pneu;
	private Rollers roller;
	
	
	private final boolean left = true;
	
	private States.Autonomous currentAutonomousState = States.Autonomous.READY;
	private States.AutoTote currentAutoToteState = States.AutoTote.DRIVE_TO_TOTE; 
	private States.AutoBin currentAutoBinState = States.AutoBin.DRIVE_TO_BIN;
	
	DigitalInput autoSwitch1 = new DigitalInput(RobotValues.AUTO_SWITCH_PORT_1);
	DigitalInput autoSwitch2 = new DigitalInput(RobotValues.AUTO_SWITCH_PORT_2);
	DigitalInput autoSwitch3 = new DigitalInput(RobotValues.AUTO_SWITCH_PORT_3);
	
	Boolean currentActuatorState;
	Boolean stateCompleted;
	
	LED leds = new LED();
	
	public Autonomous(Drive _drive, Lifter _lifter, Pneumatics _pneumatics){
		drive = _drive;
		lifter = _lifter;
		pneu = _pneumatics;
	}
	
	public void update(){
		switch(currentAutonomousState){
		case READY:
			setAutoMode();
			break;
		case DRIVE_TO_ZONE:
			driveToZone();
			break;
		case TOTE_TO_ZONE:
			toteToZone();
			break;
		case BIN_TO_ZONE:
			binToZone();
			break;
		case TO_BE_DETERMINED:
			driveToZone();
			break;
		case DO_NOTHING:
			currentAutonomousState = States.Autonomous.END;
			break;
		case END:
			SmartDashboard.putString("Auto", "Done");
			//auto is over it's time for the light show!
			LED.setLEDs(LEDModes.RAINBOW);
			break;
		}
		
		
	}
	
	private void completed(){
		currentAutonomousState = States.Autonomous.END;
	}
	
	private void notCompleted(){
		stateCompleted = false;
	}
	
	public boolean Done(){
		return stateCompleted;
	}
	
	
	//tote to zoneC
	public void toteToZone(){
		
		if(drive.Done() && lifter.Done()){
			switch(currentAutoToteState){
				case DRIVE_TO_TOTE:
					//drive to the tote
					drive.startDistanceDrive(DISTANCE_TO_TOTE);
					//set the state to the next state
					currentAutoToteState = States.AutoTote.INTAKE;
					break;
				case INTAKE:
					roller.startRollerIntake();
					currentAutoToteState = States.AutoTote.LIFT;
				case LIFT:
					roller.startRollerIntake();
					//start the lift
					lifter.startStack();
					//set the next state
					currentAutoToteState = States.AutoTote.TURN_90;
					break;
				case TURN_90:
					//start the 90 degree turn
					drive.startRightAngleTurn(!left);
					//change the state to the next state
					currentAutoToteState = States.AutoTote.DRIVE_TO_AUTO_ZONE;
					break;
				case DRIVE_TO_AUTO_ZONE:
					//start the drive to the auto zone
					drive.startDistanceDrive(DISTANCE_TO_AUTO_ZONE);
					//change the state to the next state
					currentAutoToteState = States.AutoTote.DROP_STACK;
					break;
				case DROP_STACK:
					//start the drop stack
					lifter.startPlace();
					//change to the next state
					currentAutoToteState = States.AutoTote.BACKUP;
					break;
				case BACKUP:
					//start the distance drive backwards
					drive.startBDistanceDrive(1);
					//change to end because we're done
					currentAutoToteState = States.AutoTote.END;
					break;
				case END:
					//were done this mode of autonomous!
					SmartDashboard.putString("Auto", "Finished AutoTote");
					completed();
					break;
			}
		}
	}
	
	//only for debugging
	public void resetStates(){
		currentAutoToteState = States.AutoTote.DRIVE_TO_TOTE;
		currentAutoBinState = States.AutoBin.DRIVE_TO_BIN;
		
	}
	
	//bin to zone 
	public void binToZone(){
		//drive.startDistanceDrive(DISTANCE_TO_BIN);
	    //lifter.lift();
	    //drive.startRightAngleTurn(left);
	    //drive.startDistanceDrive(DISTANCE_TO_AUTO_ZONE);
	    //lifter.lower();
		if(drive.Done() && pneu.Done()){
			switch(currentAutoBinState){
				case DRIVE_TO_BIN:
					//drive to the bin
					drive.startDistanceDrive(DISTANCE_TO_BIN);
					//set the state to the next state
					currentAutoBinState = States.AutoBin.INTAKE;
					break;
				case INTAKE:
					roller.startRollerIntake();
					currentAutoBinState = States.AutoBin.LIFT_BIN;
				case LIFT_BIN:
					//start the lift
					pneu.startLifterUp();
					//set the next state
					currentAutoBinState = States.AutoBin.TURN_90;
					break;
				case TURN_90:
					//start the 90 degree turn
					drive.startRightAngleTurn(left);
					//change the state to the next state
					currentAutoBinState = States.AutoBin.DRIVE_TO_AUTO_ZONE;
					break;
				case DRIVE_TO_AUTO_ZONE:
					//start the drive to the auto zone
					drive.startDistanceDrive(DISTANCE_TO_AUTO_ZONE);
					//change the state to the next state
					currentAutoBinState = States.AutoBin.DROP_BIN;
					break;
				case DROP_BIN:
					//start the drop 
					pneu.startLifterDown();
					//change to the next state
					currentAutoBinState = States.AutoBin.BACKUP;
					break;
				case BACKUP:
					//start the distance drive backwards
					drive.startBDistanceDrive(1);
					//change to end because we're done
					currentAutoBinState = States.AutoBin.END;
					break;
				case END:
					//were done this mode of autonomous!
					SmartDashboard.putString("Auto", "Finished AutoBin");
					completed();
					break;
			}
	
		}
		
	}
	//to zone
	public void driveToZone(){
		drive.startDistanceDrive(DISTANCE_TO_AUTO_ZONE);
		SmartDashboard.putString("Auto", "Finished DriveToZone");
		completed();
	}
	
	//method that will display the auto mode in the smartdashboad 
	//it will also display the mode using the the leds
	public int displayAutoMode(){
		int switchPos = getAutoSwitchPos(autoSwitch1.get(),autoSwitch2.get(),autoSwitch3.get());
		SmartDashboard.putNumber("The auto switch Pos", switchPos);
		
		
		//led code:
		switch(switchPos){
			case 1:
				//drive to zone
				leds.setLEDs(LEDModes.BLUE);
				break;
			case 2:
				//tote to zone
				leds.setLEDs(LEDModes.YELLOW);
				break;
			case 3:
				//bin to zone
				leds.setLEDs(LEDModes.GREEN);
				break;
			case 4:
				//TBD
				leds.setLEDs(LEDModes.PURPLE);
				break;
			case 5:
				//do nothing
				leds.setLEDs(LEDModes.RED);
				break;
			case 0:
				//error still going to drive to zone
				leds.setLEDs(LEDModes.ERROR);
				break;
			
				
		
		}
		return switchPos;
	}
	
	//method that will be called at the start of autonomous
	//it will set the currentAutoState based on the pos
	public void setAutoMode(){
		switch(getAutoSwitchPos(autoSwitch1.get(),autoSwitch2.get(),autoSwitch3.get())){
			
			case 1:
				currentAutonomousState = States.Autonomous.DRIVE_TO_ZONE;
				break;
			
			case 2:
				currentAutonomousState = States.Autonomous.TOTE_TO_ZONE;
				break;
			
			case 3:
				currentAutonomousState = States.Autonomous.BIN_TO_ZONE;
				break;
			
			case 4:
				currentAutonomousState = States.Autonomous.TO_BE_DETERMINED;
				break;
			
			case 5:
				currentAutonomousState = States.Autonomous.DO_NOTHING;
				break;
				
			//error with the switch
			case 0:
				currentAutonomousState = States.Autonomous.DRIVE_TO_ZONE;
				SmartDashboard.putString("Auto Error", "Switch failed.");
				break;
		}
	}
	
	private int getAutoSwitchPos(boolean one, boolean two, boolean three){
		/*
		 * pos|bits
		 * 1  |110
		 * 2  |100
		 * 3  |101
		 * 4  |001
		 * 5  |011
		 * 
		 */
		
		if(one && two){
			return 1;
		}
		else if(one && !two && !three){
			return 2;
		}
		else if(one && !two && three){
			return 3;
		}
		else if(!one && !two){
			return 4;
		}
		else if(!one && two){
			return 5;
		}
		else {
			//error case
			return 0;
		}
		
		
	}
	
	
	
	
	
	

}
