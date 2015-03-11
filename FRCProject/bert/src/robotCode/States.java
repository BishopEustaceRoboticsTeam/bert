package robotCode;

public class States {
	
	//private static Drive current_drive_state;
	//private static Lifter current_lock_state;
	//private static Lock current_lifter_state;
	
	
	public enum Drive {
		RIGHT_ANGLE_TURN , CONTROLLER_DRIVE, FINE_CONTROL, OVERRIDE, DISTANCE_DRIVE, BACKWARD_DISTANCE_DRIVE;
	}
	
	public enum Rollers{
		CONTROLLED, IN, OUT;
	}
    
	public enum Autonomous {
		READY, DRIVE_TO_ZONE, TOTE_TO_ZONE, BIN_TO_ZONE, TO_BE_DETERMINED, DO_NOTHING, END;
	}
	
	public enum AutoTote {
		DRIVE_TO_TOTE, INTAKE, LIFT, TURN_90, DRIVE_TO_AUTO_ZONE, DROP_STACK, BACKUP, END;
	}
	
	public enum AutoBin{
		DRIVE_TO_BIN, INTAKE, LIFT_BIN, TURN_90, DRIVE_TO_AUTO_ZONE, DROP_BIN, BACKUP, END;
	}
	
	public enum Pneumatics{
		READY, UNLOCK, LOCK, LIFTER_UP, LIFTER_DOWN, ROLLER_IN, ROLLER_OUT;
	}
	
	public enum Stack{
		LOCK, OPEN_ROLLERS, RESET, LIFT, LOWER, END;
	}
	
	public enum Place{
		OPEN_ROLLERS, LIFT, UNLOCK, LOWER, END;
	}
	
	public enum LifterStates{
		READY, STACK, PLACE, RESET;
	}
	
	
	
}
