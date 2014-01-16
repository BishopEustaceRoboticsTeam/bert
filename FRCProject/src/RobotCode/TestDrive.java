package RobotCode;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;



/**
 *
 * @author Robotics
 */
public class TestDrive {
    
    RobotDrive drive_;
    
    public TestDrive(RobotDrive drive) {
        // Use requires() here to declare subsystem dependencies
        drive_ = drive;
        
       // eg. requires(chassis);
    System.out.println("Test drive is awesome");
    
    }
  
    
    void driveTenMeters() {
        
        drive_.drive(0.5, 0.0);

    }

 
}


