package RobotCode;

import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Jaguar;

/**
 *
 * @author Robotics
 */
public class LiftControl {

    Jaguar actuator_left = new Jaguar(4);
    Jaguar actuator_right = new Jaguar(5);
    Joystick rightStick = new Joystick(1);
    //RobotDrive drive = new RobotDrive(5);
    //RobotDrive drive = new RobotDrive(6);

    LiftControl(/*int actuator_left, int actuator_right*/) {

        if (rightStick.getRawButton(6)) {
            System.out.println("You press button one. Hopefully...");
            actuator_left.set(0.25);
        } else if (rightStick.getRawButton(7)) {
            System.out.println("You press button two. Hopefully...");
            actuator_left.set(-0.25);
        } else {
            actuator_left.set(0.0);
        }
        if (rightStick.getRawButton(6)) {
            System.out.println("You press button one. Hopefully...");
            actuator_right.set(0.25);
        } else if (rightStick.getRawButton(7)) {
            System.out.println("You press button two. Hopefully...");
            actuator_right.set(-0.25);
        } else {
            actuator_right.set(0.0);
        }

    }
}
