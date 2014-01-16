/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MainCode;

import edu.wpi.first.wpilibj.Servo;


/**
 *
 * @author Robotics
 */
public class ServoPractice {
    
    Servo myservo_;
    
    
    // constructor
    public ServoPractice(int channel_number) {
     
        myservo_ = new Servo(channel_number);
    }
    
    public double getServoValue() {
        return myservo_.get();
    }
    
    public double getServoAngle() {
        return myservo_.getAngle();
    }
        
}
