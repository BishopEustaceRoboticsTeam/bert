/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RobotCode;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author user1
 */
public class F310 {
    Joystick f130;
    F310(int joystickPort){
        f130 = new Joystick(joystickPort);
        
    }
    public boolean getXButton(){
        return f130.getRawButton(1);
    }
    public boolean getAButton(){
        return f130.getRawButton(2);
        
    }
    public boolean getBButton(){
        return f130.getRawButton(3);
        
    }
    public boolean getYButton(){
        return f130.getRawButton(4);
        
    }
    public boolean getLBButton(){
        return f130.getRawButton(5);
        
    }
    public boolean getRBButton(){
        return f130.getRawButton(6);
        
    }
    public boolean getLTButton(){
        return f130.getRawButton(7);
        
    }
    public boolean getRTButton(){
        return f130.getRawButton(8);
        
    }
    public boolean getBackButton(){
        return f130.getRawButton(9);
        
    }
    public boolean getStartButton(){
        return f130.getRawButton(10);
        
    }
    public boolean getL3Button(){
        return f130.getRawButton(11);
        
    }
    public boolean getR3Button(){
        return f130.getRawButton(12);
        
    }
    public double getLeftStickX(){
        return f130.getRawAxis(1);
        
    }
    public double getLeftStickY(){
        return f130.getRawAxis(2);
        
    }
    public double getRightStickX(){
        return f130.getRawAxis(3);
        
    }
    public double getRightStickY(){
        return f130.getRawAxis(4);
        
    }
    public double getDpadX(){
        return f130.getRawAxis(5);
        
    }
    public double getDpadY(){
        return f130.getRawAxis(6);
        
    }
    
    
}
