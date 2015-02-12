package robotCode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Autonomous {
	private final double DISTANCE_TO_AUTO_ZONE = 2.75;  //2.7178 is the exact value
	private final double DISTANCE_FROM_WALL_TO_AUTO_ZONE = 4.1402;
	private final double DISTANCE_TO_TOTE = 0.2; 
	private final double DISTANCE_TO_BIN = 0.25;

	private Drive drive;
	private Pneumatics lifter;
	
	private final boolean left = true;
	
	private States.Autonomous currentAutonomousState = States.Autonomous.READY;
	private States.AutoTote currentAutoToteState = States.AutoTote.DRIVE_TO_TOTE; 
	private States.AutoBin currentAutoBinState = States.AutoBin.DRIVE_TO_BIN;
	
	DigitalInput autoSwitch1 = new DigitalInput(RobotValues.AUTO_SWITCH_PORT_1);
	DigitalInput autoSwitch2 = new DigitalInput(RobotValues.AUTO_SWITCH_PORT_2);
	DigitalInput autoSwitch3 = new DigitalInput(RobotValues.AUTO_SWITCH_PORT_3);
	
	Boolean currentActuatorState;
	Boolean stateCompleted;
	
	public Autonomous(Drive _drive, Pneumatics _pneumatics){
		drive = _drive;
		lifter = _pneumatics;
		
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
					currentAutoToteState = States.AutoTote.LIFT;
					break;
				case LIFT:
					//start the lift
					lifter.startLifterUp();
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
					lifter.startLifterDown();
					//change to the next state
					currentAutoToteState = States.AutoTote.BACKUP;
					break;
				case BACKUP:
					//start the distance drive backwards
					drive.startDistanceDrive(-1);
					//change to end because we're done
					currentAutoToteState = States.AutoTote.END;
					break;
				case END:
					//were done this mode of autonomous!
					SmartDashboard.putString("Auto", "Finished AutoTote");
					break;
			}
		}
	}
	
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
		if(drive.Done() && lifter.Done()){
			switch(currentAutoBinState){
				case DRIVE_TO_BIN:
					//drive to the bin
					drive.startDistanceDrive(DISTANCE_TO_BIN);
					//set the state to the next state
					currentAutoBinState = States.AutoBin.LIFT_BIN;
					break;
				case LIFT_BIN:
					//start the lift
					lifter.lift();
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
					lifter.lower();
					//change to the next state
					currentAutoBinState = States.AutoBin.BACKUP;
					break;
				case BACKUP:
					//start the distance drive backwards
					drive.startDistanceDrive(-1);
					//change to end because we're done
					currentAutoBinState = States.AutoBin.END;
					break;
				case END:
					//were done this mode of autonomous!
					SmartDashboard.putString("Auto", "Finished AutoBin");
					break;
			}
	
		}
		
	}
	//to zone
	public void driveToZone(){
		drive.startDistanceDrive(DISTANCE_TO_AUTO_ZONE);
		SmartDashboard.putString("Auto", "Finished DriveToZone");
	}
	
	//method that will display the auto mode in the smartdashboad 
	//it will also display the mode using the the leds
	public int displayAutoMode(){
		int switchPos = getAutoSwitchPos(autoSwitch1.get(),autoSwitch2.get(),autoSwitch3.get());
		SmartDashboard.putNumber("The auto switch Pos", switchPos);
		
		
		//led code:
		
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
			
			case 0:
				currentAutonomousState = States.Autonomous.DRIVE_TO_ZONE;
				SmartDashboard.putString("Auto Error", "Switch failed.");
			break;
		}
	}
	
	private int getAutoSwitchPos(boolean one, boolean two, boolean three){
		/*pos|bit
		 * 1 |110
		 * 2 |100
		 * 3 |101
		 * 4 |001
		 * 5 |011
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
			return 0;
		}
		
		
	}
	
	
	
	
	
	

}
