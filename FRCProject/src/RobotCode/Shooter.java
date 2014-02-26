/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotCode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author the reno
 */
public class Shooter {

    Timer timer_ = new Timer();

    Victor right_motor, left_motor;
    
    StateEstimator state_;

    final double k_SHOOTER_TIME = 0.42; //in seconds

    Shooter(Victor left, Victor right, StateEstimator state) {
        left_motor = left; // This should be a pwm port
        right_motor = right;
        state_ = state;
    }

    //this is the overided shooting
    public void sudoShoot() {
        right_motor.set(1);
        left_motor.set(-1);
        Timer.delay(k_SHOOTER_TIME);
        right_motor.set(0);
        left_motor.set(0);
    }

    //this needs to be called in a loop 
    //The purpose of this is to not tie up the robot while shooting
    public void timerShoot() {
        timer_.start();
        if (timer_.get() <= k_SHOOTER_TIME) {
            right_motor.set(1);
            left_motor.set(-1);
        } else {
            right_motor.set(0);
            left_motor.set(0);

        }
        timer_.stop();
        timer_.reset();
    }

    public void safeShooter() {
        
        
        
        
    }

}
