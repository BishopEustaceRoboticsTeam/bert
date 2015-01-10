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

    double k_SHOOTER_TIME = 0.399999999997; //in seconds that is alot of 9's!
    double k_PASSING_TIME = 0.10;
    final double k_DROP_PICKERUPPER_TIME = 0.0;
    boolean shootering_ = false;
    boolean passing_ = false;
    double MOTOR_TIME = 0;
    // Motor speeds.  Shoot fast up, slow down on the way back.
    final double RIGHT_MOTOR_UP = -1.0;
    final double LEFT_MOTOR_UP = 1.0;
    final double RIGHT_MOTOR_DOWN = 0.48;
    final double LEFT_MOTOR_DOWN = -0.48;
    final double STOP_TIME = 0.1;

    final int PICKERUPPER_SOLINOID_INDEX = 0;

    Pneumatics p_;

    //
    Shooter(int _left_motor_port, int right_motor_port, StateEstimator state, Pneumatics p) {
        left_motor = new Victor(_left_motor_port); // This should be a pwm port
        right_motor = new Victor(right_motor_port);
        state_ = state;
        p_ = p;
    }
    
    public void setShootingTimer(double time){
        k_SHOOTER_TIME = time;
        
    }
    
    public void setPasserTime(double time){
        k_PASSING_TIME = time;
        
    }
    
    public void pass(){
        MOTOR_TIME = k_PASSING_TIME;
        timer_.start();
        shootering_ = true;
        putPickerupperDown();
    }

    public void debug(double leftMotor, double rightMotor, boolean shoot, double time, boolean retract) {
//        if (shoot) {
//            System.out.println("shooting");
//            right_motor.set(RIGHT_MOTOR_UP);
//            left_motor.set(LEFT_MOTOR_UP);
//            Timer.delay(time);
//            right_motor.set(0);
//            left_motor.set(0);
//
//        } else if (retract) {
//            System.out.println("retractering");
//            System.out.println();
//            while (!state_.getBumpSwitchState()) {
//                right_motor.set(rightMotor);
//                left_motor.set(-1*leftMotor);
//
//                
//            }
//            System.out.println("done retracting");
//            right_motor.set(0);
//            left_motor.set(0);
//        }

        left_motor.set(leftMotor);
        right_motor.set(rightMotor);
    }

    //this is the overided shooting
    public void sudoShoot() {
        right_motor.set(1);
        left_motor.set(-1);
        Timer.delay(k_SHOOTER_TIME);
        right_motor.set(0);
        left_motor.set(0);
    }

    public void shoot() {
        MOTOR_TIME = k_SHOOTER_TIME;
        timer_.start();
        shootering_ = true;
        putPickerupperDown();              
    }
                
    public void putPickerupperDown() {
        //this will put the picker up down on the floor
        p_.setSingleSolenoidNotDefault(PICKERUPPER_SOLINOID_INDEX);
    }

    public void putPickerupperUp() {
        //this will put the picker up down on the floor
        p_.setSingleSolenoidDefault(PICKERUPPER_SOLINOID_INDEX);
    }
    //this needs to be called in a loop 
    //The purpose of this is to not tie up the robot while shooting
    public void update() {
        if (!shootering_) { //shootering?
            return;
        }
        if (timer_.get() <= k_DROP_PICKERUPPER_TIME) {
          //waiting for pickerupper to lower

        } else if (timer_.get() <= MOTOR_TIME + k_DROP_PICKERUPPER_TIME) {
            right_motor.set(RIGHT_MOTOR_UP);
            left_motor.set(LEFT_MOTOR_UP);
            
        } 
        else if (timer_.get() <= MOTOR_TIME + k_DROP_PICKERUPPER_TIME + STOP_TIME){
            //we are waiting?
            right_motor.set(0.0);
            left_motor.set(0.0);            
        }
        else if (!state_.getBumpSwitchState()) {
            right_motor.set(RIGHT_MOTOR_DOWN);
            left_motor.set(LEFT_MOTOR_DOWN);
        } else {
            right_motor.set(0);
            left_motor.set(0);
            timer_.stop();
            timer_.reset();
            shootering_ = false;

        }

    }

    //this method will only shoot if the dist from the disired shooting pos is <= 0
    public void safeShooter(double distFromShootingDistance) {
        //reno stop i can code better than simona i should be software leader
        if (distFromShootingDistance <= 0) {
            //DON'T WANNA BE AN AMERICAN IDIOT NANANANANANANANANANANANANANANANANA
            shoot();

        }

    }

    
    public void lowerShooter() {
        
        right_motor.set(RIGHT_MOTOR_DOWN);
        left_motor.set(LEFT_MOTOR_DOWN);
        while(!state_.getBumpSwitchState()) {
	  // empty loop check
	}
        right_motor.set(0.0);
        left_motor.set(0.0);
    
    }
    
}
