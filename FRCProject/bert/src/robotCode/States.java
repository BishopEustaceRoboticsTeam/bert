package robotCode;

public class States {
	
	private static Drive current_drive_state;
	private static Lifter current_lock_state;
	private static Lock current_lifter_state;
	
	
	public enum Drive {
		RIGHT_ANGLE_TURN , CONTROLLER_DRIVE, OVERRIDE, DISTANCE_DRIVE;
	}
	
	
	public enum Lifter {
		
	}
	
	public enum Lock {
		
	}

	/**
	 * @return the current_drive_state
	 */
	public Drive getCurrent_drive_state() {
		return current_drive_state;
	}

	/**
	 * @param current_drive_state the current_drive_state to set
	 */
	public void setCurrent_drive_state(Drive current_drive_state) {
		this.current_drive_state = current_drive_state;
	}

	/**
	 * @return the current_lock_state
	 */
	public Lifter getCurrent_lock_state() {
		return current_lock_state;
	}

	/**
	 * @param current_lock_state the current_lock_state to set
	 */
	public void setCurrent_lock_state(Lifter current_lock_state) {
		this.current_lock_state = current_lock_state;
	}

	/**
	 * @return the current_lifter_state
	 */
	public Lock getCurrent_lifter_state() {
		return current_lifter_state;
	}

	/**
	 * @param current_lifter_state the current_lifter_state to set
	 */
	public void setCurrent_lifter_state(Lock current_lifter_state) {
		this.current_lifter_state = current_lifter_state;
	}
	
}
