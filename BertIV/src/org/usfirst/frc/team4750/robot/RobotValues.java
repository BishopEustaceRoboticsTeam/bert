package org.usfirst.frc.team4750.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotValues {
	
	    //Motor Controller PWM ports
		public static final int FRONT_RIGHT_MOTOR = 2;
		public static final int FRONT_LEFT_MOTOR = 0;
		public static final int BACK_RIGHT_MOTOR = 1;
		public static final int BACK_LEFT_MOTOR = 3;
		public static final int LEFT_ROLLER_PORT = 4;
		public static final int RIGHT_ROLLER_PORT = 5;
		
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
		
		public static final int TOTE_SENSOR_PORT = 9;
		
		//Digital sensor values:
		public static final boolean REVERSE_LEFT_ENCODER_DIRECTION = true;
		public static final boolean REVERSE_RIGHT_ENCODER_DIRECTION = false;
		
		
	
}
