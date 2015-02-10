package robotCode;

public class States {
	
	//private static Drive current_drive_state;
	//private static Lifter current_lock_state;
	//private static Lock current_lifter_state;
	
	
	public enum Drive {
		RIGHT_ANGLE_TURN , CONTROLLER_DRIVE, OVERRIDE, DISTANCE_DRIVE;
	}
	
	
	public enum Rollers{
		CONTROLLED, IN, OUT;
	}
    
	public enum AutoTote {
		DRIVE_TO_TOTE, LIFT, TURN_90, DRIVE_TO_AUTO_ZONE, DROP_STACK, BACKUP, END;
	}
	
	public enum AutoBin{
		DRIVE_TO_BIN, LIFT_BIN, TURN_90, DRIVE_TO_AUTO_ZONE, DROP_BIN, BACKUP, END;
	}
}
