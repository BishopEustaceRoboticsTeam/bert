package RobotCode;

import java.lang.Math;  // For min and max function.
import edu.wpi.first.wpilibj.Victor; // For writing to motor controllers.

/**
 *
 * @author bert5
 */
public class MecDrive {
 
    // Constant params:
    static final double kVELOCITYFACTOR = 10.0;

    // Local classes:
    F310 rc_; // Instance of the remote controller.
    Victor motor_fl_, motor_fr_, motor_bl_, motor_br_; // Motors, duh.    
    
    // Constructor.
    MecDrive(F310 rc, int fl_port, int fr_port, 
                      int bl_port, int br_port) {
        rc_ = rc;        

        // Initialize the motors.
        motor_fl_ = new Victor(fl_port);
        motor_fr_ = new Victor(fr_port);
        motor_bl_ = new Victor(bl_port);
        motor_br_ = new Victor(br_port);
 
    }
    
    
    /* This function needs to get called at 50Hz to update the control
        to the wheels.
    */
    void update() {
        
        // Step (1) - Get all the inputs from the joystick. All values
        //            should be between -1.0 and 1.0.
        double leftX = rc_.getLeftStickX() *-1;
        double leftY = rc_.getLeftStickY();
        
        // Step (2) - Convert input into desired 2D vector velocity.
        //  TODO: assume max is 10m/s
        double fl_input = Math.min(1.0, Math.max(-1.0, (leftX + leftY)));
        double fr_input = Math.min(1.0, Math.max(-1.0, (-leftX + leftY)));
        double bl_input = Math.min(1.0, Math.max(-1.0, (-leftX + leftY)));
        double br_input = Math.min(1.0, Math.max(-1.0, (leftX + leftY)));
        
        // Convert to velocity.
        double fl_desired_mps = fl_input * kVELOCITYFACTOR *-1;
        double fr_desired_mps = fr_input * kVELOCITYFACTOR;
        double bl_desired_mps = bl_input * kVELOCITYFACTOR *-1;
        double br_desired_mps = br_input * kVELOCITYFACTOR;
        
        
        // Step (3) - Get actual value of each encoder velocity.
        // TODO nick
        
        
        
        // Step (4) - Now that we have actual velocity and desired velocity,
        //            write PID for each of the wheels individually.
        // TODO nick
        
        
        // Step (5) - Write controls to each of the wheel.
        //            Scale back to MPS.  We could just leave mps out of the
        //            whole picture, but it is here for clarity.
        double fl_output = fl_desired_mps / kVELOCITYFACTOR;
        double fr_output = fr_desired_mps / kVELOCITYFACTOR;
        double bl_output = bl_desired_mps / kVELOCITYFACTOR;
        double br_output = br_desired_mps / kVELOCITYFACTOR;
                        
        motor_fl_.set(fl_output);
        motor_fr_.set(fr_output);
        motor_bl_.set(bl_output);
        motor_br_.set(br_output);
                
    }
    
    
}
