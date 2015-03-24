package robotCode;

public class States {
	
	//private static Drive current_drive_state;
	//private static Lifter current_lock_state;
	//private static Lock current_lifter_state;
	
	//States for the drive class
	public enum Drive {
		RIGHT_ANGLE_TURN , CONTROLLER_DRIVE, FINE_CONTROL, OVERRIDE, DISTANCE_DRIVE, BACKWARD_DISTANCE_DRIVE, NONLINEAR_DRIVE, REVERSE_DRIVE;
	}
	
	//the states for the roller class
	public enum Rollers{
		CONTROLLED, IN, OUT;
	}
    
	//the different auto modes
	public enum Autonomous {
		READY, DRIVE_TO_ZONE, TOTE_TO_ZONE, BIN_TO_ZONE, SHORT_DRIVE_TO_ZONE, DO_NOTHING, END;
	}
	
	//sequence for the autonomous tote mode
	public enum AutoTote {
		DRIVE_TO_TOTE, INTAKE, LIFT, TURN_90, DRIVE_TO_AUTO_ZONE, DROP_STACK, BACKUP, END;
	}
	
	//sequence for the autonomous bin mode
	public enum AutoBin{
		DRIVE_TO_BIN, INTAKE, LIFT_BIN, TURN_90, DRIVE_TO_AUTO_ZONE, DROP_BIN, BACKUP, END;
	}
	
	//the different states for the pneumatics class
	public enum Pneumatics{
		READY, UNLOCK, LOCK, LIFTER_UP, LIFTER_DOWN, ROLLER_IN, ROLLER_OUT;
	}
	
	//the sequence for stacking a tote
	public enum Stack{
		OPEN_ROLLERS, RESET, UNLOCK, LIFT, LOCK, LOWER, END;
	}
	
	//the sequence for placing a stack of totes
	public enum Place{
		OPEN_ROLLERS, LIFT, UNLOCK, LOWER, END;
	}
	
	//the states for the lifter class
	public enum LifterStates{
		READY, STACK, PLACE, RESET;
	}
	
	
	
}
