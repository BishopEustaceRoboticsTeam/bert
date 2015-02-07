package robotCode;

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
	
	private States.AutoTote currentAutoToteState = States.AutoTote.DRIVE_TO_TOTE; 
	private States.AutoBin currentAutoBinState = States.AutoBin.DRIVE_TO_BIN;
	
	public Autonomous(Drive _drive, Pneumatics _pneumatics){
		drive = _drive;
		lifter = _pneumatics;
		
	}
	
	//tote to zone
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
					lifter.stack();
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
					lifter.placeStack();
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
	
	
	
	

}
