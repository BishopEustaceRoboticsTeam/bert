
package robotemulator;

import RobotCode.*;
import java.util.Scanner;


/**
 *
 * @author aubrey
 */
public class RobotEmulator {
    static int input;
    static boolean InIsValid;
    static RobotMain robot = new RobotMain();
    static int i = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        do {
            System.out.println("(1) Autonomous \n(2) Teleop");
            
            Scanner scan = new Scanner(System.in);
            input = scan.nextInt();
            
            if (input == 1 || input == 2) {
                InIsValid = true;
            }
        } while (!InIsValid);
        
        robot.robotInit();
        
        if (input == 1) {
            while (true) {
                robot.autonomousContinuous();
                i++;
                if (i == 150) {
                    i = 0;
                    robot.autonomousPeriodic();
                } 
            }
                } else if (input == 2) {
                    while (true) {
                        robot.teleopContinuous();
                        i++;
                        if (i == 150) {
                            i = 0;
                            robot.teleopPeriodic();
                        } 
                    }
                }
    }
}
