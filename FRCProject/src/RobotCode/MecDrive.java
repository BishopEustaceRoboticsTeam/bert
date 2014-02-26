package RobotCode;

import java.lang.Math;  // For min and max function.
import edu.wpi.first.wpilibj.Victor; // For writing to motor controllers.
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 *
 * @author bert5
 */
public class MecDrive {

    // Constant params:
    static final double kTICSFACTOR = 10.0;

    // Local classes:
    F310 rc_; // Instance of the remote controller.
    Victor motor_fl_, motor_fr_, motor_bl_, motor_br_; // Motors, duh.
    //Encoder fl_encoder_, fr_encoder_, bl_encoder_, br_encoder_;
    PIDController fl_controller_, fr_controller_, bl_controller_, br_controller_;
    RobotDrive drive;
    StateEstimator state_;

    private final double Kp = 0.3;
    private final double Ki = 0.0;
    private final double Kd = 0.0;

    // Constructor.
    MecDrive(F310 rc, StateEstimator state, int fl_port, int fr_port,
            int bl_port, int br_port) {
        rc_ = rc;

        // Initialize the motors.
        motor_fl_ = new Victor(fl_port);
        motor_fr_ = new Victor(fr_port);
        motor_bl_ = new Victor(bl_port);
        motor_br_ = new Victor(br_port);
        drive = new RobotDrive(motor_fl_, motor_bl_, motor_fr_, motor_br_);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        state_ = state;
        //fl_encoder_ = new Encoder(1, 2, true, EncodingType.k4X);
        //fr_encoder_ = new Encoder(3, 4, false, EncodingType.k4X);
        //bl_encoder_ = new Encoder(5, 6, true, EncodingType.k4X);
        //br_encoder_ = new Encoder(7, 8, false, EncodingType.k4X);

        //fl_encoder_.setPIDSourceParameter(PIDSource.PIDSourceParameter.kRate);
        //fr_encoder_.setPIDSourceParameter(PIDSource.PIDSourceParameter.kRate);
        //bl_encoder_.setPIDSourceParameter(PIDSource.PIDSourceParameter.kRate);
        //br_encoder_.setPIDSourceParameter(PIDSource.PIDSourceParameter.kRate);
        //fl_controller_ = new PIDController(Kp, Ki, Kd, fl_encoder_, motor_fl_);
        //fr_controller_ = new PIDController(Kp, Ki, Kd, fr_encoder_, motor_fr_);
        //bl_controller_ = new PIDController(Kp, Ki, Kd, bl_encoder_, motor_bl_);
        //br_controller_ = new PIDController(Kp, Ki, Kd, br_encoder_, motor_br_);
    }

    /* This function needs to get called at 50Hz to update the control
     to the wheels.
     */
    public void encoderStart() {

//        fl_encoder_.start();
//        fr_encoder_.start();
//        bl_encoder_.start();
//        br_encoder_.start();
//        
//        fl_controller_.enable();
//        fr_controller_.enable();
//        bl_controller_.enable();
//        br_controller_.enable();
    }

    void update() {

        // Step (1) - Get all the inputs from the joystick. All values
//        //            should be between -1.0 and 1.0.
//        double leftX = rc_.getLeftStickX();
//        double leftY = rc_.getLeftStickY();
//        double rightX = rc_.getRightStickX(); //for turning with right stick
//        double rightY = rc_.getRightStickY();
//       (-1*rc_.getRightStickX())
        
        
        if (state_.getDriveMode()) {
            //this is the normal drive state (pickerupper in front)
            drive.mecanumDrive_Cartesian((-1 * rc_.getRightStickX()), (-1 * rc_.getLeftStickY()), (-1 * rc_.getLeftStickX()), 0.0);
        } else if (!state_.getDriveMode()) {
            //this is the reversed drive code (shooter in front)
            drive.mecanumDrive_Cartesian((rc_.getRightStickX()), (rc_.getLeftStickY()), (-1*rc_.getLeftStickX()), 0.0);
        }

//        System.out.println("LeftStickx =  " + rc_.getLeftStickX());
//        System.out.println("LeftSticky = " + rc_.getLeftStickY());
//        System.out.println("RightStickx =  " + rc_.getRightStickX());
//        
        // Step (2) - Convert input into desired 2D vector velocity.
        //  TODO: assume max is 10m/s
//        double fl_input = Math.min(1.0, Math.max(-1.0, (leftX + leftY - rightX)));
//        double fr_input = Math.min(1.0, Math.max(-1.0, (-leftX + leftY + rightX)));
//        double bl_input = Math.min(1.0, Math.max(-1.0, (-leftX + leftY - rightX)));
//        double br_input = Math.min(1.0, Math.max(-1.0, (leftX + leftY + rightX)));
//        
        // Convert to ticks per second
//        double fl_desired_mps = fl_input * kTICSFACTOR *-1;
//        double fr_desired_mps = fr_input * kTICSFACTOR;
//        double bl_desired_mps = bl_input * kTICSFACTOR *-1;
//        double br_desired_mps = br_input * kTICSFACTOR;
//        
        //this should set the correct
        //fl_controller_.setSetpoint(fl_desired_mps);
        //fr_controller_.setSetpoint(fr_desired_mps);
        //bl_controller_.setSetpoint(bl_desired_mps);
        //br_controller_.setSetpoint(br_desired_mps);
        // Step (3) - Get actual value of each encoder velocity.
        // TODO nick
//        double fl_RPS = fl_encoder_.getRate() / 250; //the encoders are 250 count so if you divide by 250 you will get the revolutions per second
//        double fr_RPS = fr_encoder_.getRate() / 250;
//        double bl_RPS = bl_encoder_.getRate() / 250;
//        double br_RPS = br_encoder_.getRate() / 250;
        // Step (4) - Now that we have actual velocity and desired velocity,
        //            write PID for each of the wheels individually.
        // TODO nick       
        // Step (5) - Write controls to each of the wheel.
        //            Scale back to MPS.  We could just leave mps out of the
        //            whole picture, but it is here for clarity.
//        double fl_output = fl_desired_mps / kTICSFACTOR;
//        double fr_output = fr_desired_mps / kTICSFACTOR;
//        double bl_output = bl_desired_mps / kTICSFACTOR;
//        double br_output = br_desired_mps / kTICSFACTOR;
//        
        //motor_fl_.set(fl_input);
        //motor_fr_.set(fr_input);
        //motor_bl_.set(bl_input);
        //motor_br_.set(br_input);
    }
    
    //the input is a value from -1 - 1  
    public void driveWithShooterInFront(double speed){
        drive.mecanumDrive_Cartesian(0, (-1 * speed), 0, 0);
        
    }
    
    //the input is a value from -1 - 1  
    public void driveWithPickerUpperInFront(double speed){
        drive.mecanumDrive_Cartesian(0, speed, 0, 0);
        
    }

    public void encoderStop() {
//        fl_encoder_.stop();
//        fr_encoder_.stop();
//        bl_encoder_.stop();
//        br_encoder_.stop();

    }

    public void encoderReset() {
//        fl_encoder_.reset();
//        fr_encoder_.reset();
//        bl_encoder_.reset();
//        br_encoder_.reset();
//        
    }
    

}
