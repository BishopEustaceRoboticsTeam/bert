/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotCode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
//import com.sun.squawk.util.

/**
 *
 * @author user1
 */
public class F310 {

    final double time_between_buttons = 0.2;
    Joystick f130;
    double dead_Zone_ = 0.05;
    Timer timer_;

    F310(int joystickPort) {
        f130 = new Joystick(joystickPort);
        timer_ = new Timer();
        timer_.start();
    }

    private double checkDeadZone(double val) {
        if (Math.abs(val) <= dead_Zone_) {
            return 0;

        } else {
            return val;

        }
    }

    public boolean getXButton() {
        return isTimeOK(f130.getRawButton(3));
    }

    public boolean getAButton() {
        return isTimeOK(f130.getRawButton(1));
    }

    public boolean getBButton() {
        return isTimeOK(f130.getRawButton(2));
    }

    public boolean getYButton() {
        
        return isTimeOK(f130.getRawButton(4));
        
    }

    public boolean getLBButton() {
        
        return isTimeOK(f130.getRawButton(5));
        
    }

    public boolean getRBButton() {
        
        return isTimeOK(f130.getRawButton(6));
        
        
    }

    public double getLTAxis() {
        //0-1
        return f130.getRawAxis(3);

    }

    public double getRTAxis() {
        //-1-0
        return f130.getRawAxis(3);

    }

    public boolean getBackButton() {
        return f130.getRawButton(7);

    }

    public boolean getStartButton() {
        return f130.getRawButton(8);

    }

    public boolean getL3Button() {
        return f130.getRawButton(9);

    }

    public boolean getR3Button() {
        return f130.getRawButton(10);

    }

    // Mapping has been reversed to match a cartesian frame.
    public double getLeftStickX() {
//        return f130.getRawAxis(1) *-1;
        return checkDeadZone(f130.getRawAxis(1));

    }

    public double getLeftStickY() {
        return checkDeadZone(f130.getRawAxis(2));

    }

    public double getRightStickX() {
        return checkDeadZone(f130.getRawAxis(4));

    }

    public double getRightStickY() {
        return checkDeadZone(f130.getRawAxis(5));

    }

    public double getDpadX() {
        return f130.getRawAxis(6);

    }

    public boolean isTimeOK(boolean button) {
        if (button && 
            (timer_.get() >= time_between_buttons)) {
            timer_.reset();
            return true;
        }
        return false;
    }

   // public double getDpadY(){
    //     return f130.getRawAxis();
}
