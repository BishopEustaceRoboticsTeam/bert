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
		public static final int LEFT_SHOOTER_MOTOR_PORT = 5;
		public static final int RIGHT_SHOOTER_MOTOR_PORT = 4;
		public static final int SHOOTER_AIMER_MOTOR_PORT = 9;
		public static final int SHOOTER_SERVO_PORT = 7;
		public static final int LIFTER_MOTOR = 6;
		public static final int MANIPULATOR_MOTOR = 8;
		
		//Joystick USB ports
		public static final int LEFT_JOYSTICK_USB_PORT = 0;
		public static final int RIGHT_JOYSTICK_USB_PORT = 1;
		
		//Axes
		public static final int JOYSTICK_X_AXIS = 0;
		public static final int JOYSTICK_Y_AXIS = 1;
		public static final int JOYSTICK_Z_AXIS = 2;
		public static final int JOYSTICK_TWIST_AXIS = 3;
		
		//Joystick buttons:
		
		//Other
		public static final double SERVO_SHOOT_POS = 1;
		public static final double SERVO_DEFAULT_POS = 0;
		public static final int DIGITAL_INPUT_CHANNEL_U = 0;
		public static final int DIGITAL_INPUT_CHANNEL_D = 1;
		
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
