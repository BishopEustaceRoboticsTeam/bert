package robotCode;

public class RobotValues {
	
	//Pneumatics vars
	public static final int LIFTER_SOLENOID_PORT = 0;
	public static final int LOCK_SOLENOID_PORT = 1;
	public static final int ROLLER_PISTON_PORT = 2;
	
	//Motor Controller PWM ports
	public static final int FRONT_RIGHT_MOTOR = 2;
	public static final int FRONT_LEFT_MOTOR = 0;
	public static final int BACK_RIGHT_MOTOR = 1;
	public static final int BACK_LEFT_MOTOR = 3;
	public static final int LEFT_ROLLER_PORT = 4;
	public static final int RIGHT_ROLLER_PORT = 5;
	
	//controller vars
	final static int F310_REMOTE_USB_PORT_ONE = 0;
	final static int JOYSTICK_1_USB_PORT = 1;
	final static int JOYSTICK_2_USB_PORT = 2;

	//Digital sensor ports:
	public static final int LEFT_ENCODER_A = 0;
	public static final int LEFT_ENCODER_B = 1;
	public static final int RIGHT_ENCODER_A = 2;
	public static final int RIGHT_ENCODER_B = 3;
	
	public static final int CLOSED_REED_SWITCH_PORT = 4;
	public static final int OPEN_REED_SWITCH_PORT = 5;
	
	public static final int AUTO_SWITCH_PORT_1 = 6;  //Closest to center
	public static final int AUTO_SWITCH_PORT_2 = 7; 
	public static final int AUTO_SWITCH_PORT_3 = 8;
	
	//Servo ports
	public static final int SERVO_1_PORT = 1;
	public static final int SERVO_2_PORT = 0;
	
	//Digital sensor values:
	public static final boolean REVERSE_LEFT_ENCODER_DIRECTION = true;
	public static final boolean REVERSE_RIGHT_ENCODER_DIRECTION = false;
	
	//Buttons for the second driver
		//the lifter buttons
		public static final int STACK = 1;
		public static final int PLACE = 2;
		public static final int RESET_LIFTER = 4;
		
		//the roller buttons
		public static final int ROLLER_IN_OUT = 3;
		
	
		//the locking buttons
		public static final int TOTE_LOCK_UNLOCK = 5;
		
		//debug buttons
		public static final int LIFTER_UP_BUTTON = 6;
		public static final int LIFTER_DOWN_BUTTON = 7;
		
	
}
