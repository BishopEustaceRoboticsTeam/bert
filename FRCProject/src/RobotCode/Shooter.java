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
    
    boolean shootering_ = false;
    
    final int RIGHT_MOTOR_UP = 1;
    final int LEFT_MOTOR_UP = -1;
    final int RIGHT_MOTOR_DOWN = -1;
    final int LEFT_MOTOR_DOWN = 1;
    
    //
    Shooter(int _left_motor_port, int right_motor_port, StateEstimator state) {
        left_motor = new Victor(_left_motor_port); // This should be a pwm port
        right_motor = new Victor(right_motor_port);
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
    
    public void shoot(){
        timer_.start();
        shootering_ = true;
    }

    //this needs to be called in a loop 
    //The purpose of this is to not tie up the robot while shooting
    public void update() {
        if (!shootering_){
            return;
            
        }
        if (timer_.get() <= k_SHOOTER_TIME) {
            right_motor.set(RIGHT_MOTOR_UP);
            left_motor.set(LEFT_MOTOR_UP);
        } 
        else if (!state_.getBumpSwitchState()) 
        {
            right_motor.set(RIGHT_MOTOR_DOWN);
            left_motor.set(LEFT_MOTOR_DOWN);
            //reset_shooter_ = true;
        }
        else {
            right_motor.set(0);
            left_motor.set(0);
            timer_.stop();
            timer_.reset();
            shootering_ = false;
        }
        
    }
    //this method will only shoot if the dist from the disired shooting pos is <= 0
    public void safeShooter(double distFromDisired) {
        
        
        
        
    }
    
    
        
   

}
