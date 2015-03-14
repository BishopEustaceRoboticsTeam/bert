package robotCode;

import edu.wpi.first.wpilibj.Joystick;

public class F310 {

    private Joystick f310;
    private double deadZone = 0.00;
    
    //array to keep track of the previous state of each button
    private boolean[] previousButtonStates = new boolean[7];

    public F310(int joystickPort) {
        f310 = new Joystick(joystickPort);
    }

    private double checkDeadZone(double val) {
        if (Math.abs(val) <= deadZone) {
            return 0;

        } else {
            return val;

        }
    }

    public boolean getXButton() {
        return isButtonJustPressed(3);
    }

    public boolean getAButton() {
        return isButtonJustPressed(1);
    }

    public boolean getBButton() {
        return isButtonJustPressed(2);
    }

    public boolean getYButton() {
        return isButtonJustPressed(4);
    }

    public boolean getLBButton() {
        return isButtonJustPressed(5);
    }

    public boolean getRBButton() {
        return isButtonJustPressed(6);
    }

    public double getLTAxis() {
        // 0-1
        return f310.getRawAxis(2);
    }

    public double getRTAxis() {
        // 0-1
        return f310.getRawAxis(3);
    }

    public boolean getBackButton() {
        return f310.getRawButton(7);

    }

    public boolean getStartButton() {
        return f310.getRawButton(8);

    }

    public boolean getL3Button() {
        return f310.getRawButton(9);

    }

    public boolean getR3Button() {
        return f310.getRawButton(10);

    }

    // Mapping has been reversed to match a Cartesian frame
    public double getLeftStickX() {
        return checkDeadZone(f310.getRawAxis(0));
    }

    public double getLeftStickY() {
        return checkDeadZone(f310.getRawAxis(1));
    }

    public double getRightStickX() {
        return checkDeadZone(f310.getRawAxis(4));
    }

    public double getRightStickY() {
        return checkDeadZone(f310.getRawAxis(5));
    }

    public double getDpad() {
        return f310.getPOV();
    }

    private boolean isButtonJustPressed(int button) {
		if (f310.getRawButton(button)) {
			//if the button is not still being pressed then return true
    		if (!previousButtonStates[button]) {
    			previousButtonStates[button] = true;
    			return true;
    			
    		//otherwise return false because the button state didn't change
    		} else {
    			previousButtonStates[button] = true;
    			return false;
    		}
			
    	//the button is not pressed so set the state to false
    	} else {
			previousButtonStates[button] = false;
			return false;
    	}
    }
    
}