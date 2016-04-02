
package org.usfirst.frc.team4750.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	//Victor beltMotor = new Victor(2);
	//VictorSP anotherMotor = new VictorSP(9);
	//Victor leftMotor = new Victor(2);
	//Victor rightMotor = new Victor(1);
	Joystick leftStick = new Joystick(0);
	
	Victor aimingMotor = new Victor(2);
	
	DigitalInput verticalPosIRSensor = new DigitalInput(6);
	DigitalInput highGoalPosIRSensor = new DigitalInput(5);
	
	Joystick shootStick = new Joystick(0);
	
	JoystickButton aimerDown = new JoystickButton(shootStick, 4);
	JoystickButton aimerUp = new JoystickButton(shootStick, 5);
	
	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	currentPos = ShooterPos.HIGH_GOAL;
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	SmartDashboard.putString("currentPos:", currentPos.toString());
//    	SmartDashboard.putString("targetPos:", targetPos.toString());
    	if(aimerUp.get()){
    		SmartDashboard.putString("Is button pressed?", "AIMER UP BUTTON HAS BEEN PRESSED!!");
    		
    		SetAimAngle(true);
    		SmartDashboard.putBoolean("Is Robot.SetAimAngle running?", false);
    		
    		SmartDashboard.putString("currentPos:", currentPos.toString());
        	SmartDashboard.putString("targetPos:", targetPos.toString());
    		
    		initialize();
    		SmartDashboard.putBoolean("Is Robot.initialize() running?",false);
    		
    		SmartDashboard.putString("currentPos:", currentPos.toString());
        	SmartDashboard.putString("targetPos:", targetPos.toString());
    		
    		while(!isFinished()){
    			execute();
    			
    			SmartDashboard.putString("currentPos:", currentPos.toString());
    	    	SmartDashboard.putString("targetPos:", targetPos.toString());
    		}
    		
    		SmartDashboard.putString("currentPos:", currentPos.toString());
        	SmartDashboard.putString("targetPos:", targetPos.toString());
    		
    		end();
    		
    		SmartDashboard.putString("currentPos:", currentPos.toString());
        	SmartDashboard.putString("targetPos:", targetPos.toString());
    	} else if(aimerDown.get()){
    		SmartDashboard.putString("Is button pressed?", "AIMER DOWN BUTTON HAS BEEN PRESSED!!");
    		
    		SetAimAngle(false);
    		SmartDashboard.putBoolean("Is Robot.SetAimAngle running?", false);
    		
    		initialize();
    		SmartDashboard.putBoolean("Is Robot.initialize() running?",false);
    		
    		while(!isFinished()){
    			SmartDashboard.putBoolean("Is isFinished loop running?", true);
    			execute();
    		}
    		SmartDashboard.putBoolean("Is isFinished loop running?", false);
    		end();
    	} else{
    		SmartDashboard.putString("Is button pressed?", "NO BUTTON IS BEING PRESSED!!");
    	}
    	
    }	
/*    	//double speed = SmartDashboard.getNumber("Motor speed",0);
    	double speed = leftStick.getRawAxis(0);
    	SmartDashboard.putNumber("Motor speed", speed);
    	
    	//leftMotor.set(-speed);
    	//rightMotor.set(speed);
    	beltMotor.set(speed);
*/
    
    public enum ShooterPos{
    	VERTICAL, HIGH_GOAL/*, LOW_GOAL, PICK_UP*/;
    }
    
    public boolean getIRSensor(ShooterPos shooterPos){
		boolean isSensorActivated = false;
		
		switch(shooterPos){
			case VERTICAL:
				isSensorActivated = !verticalPosIRSensor.get();
				break;
			case HIGH_GOAL:
				isSensorActivated = !highGoalPosIRSensor.get();
				break;
//			case LOW_GOAL:
//				isSensorActivated = lowGoalPosIRSensor.get();
//				break;
//			case PICK_UP:
//				isSensorActivated = pickUpPosIRSensor.get();
//				break;
		}
		
		return isSensorActivated;
	}
    
    boolean direction;
    ShooterPos currentPos;
    ShooterPos targetPos;
    
    
    public void SetAimAngle(boolean direction){
    	SmartDashboard.putBoolean("Is Robot.SetAimAngle running?", true);
    	
    	this.direction = direction;
    	
    	switch(currentPos){
    		case VERTICAL:
    			if(this.direction){
    				//This shouldn't change anything; targetPos should already be currentPos. This 
    				//statement is mostly for readability and to make it easier to extend this switching 
    				//mechanism to other states.
    				targetPos = ShooterPos.VERTICAL; 
    			}
    			else {
    				targetPos=ShooterPos.HIGH_GOAL;
    			}  //if direction is true, then targetPos will remain unchanged; i.e., it will be VERTICAL.
    			break;
    		case HIGH_GOAL:
    			if(this.direction){
    				targetPos = ShooterPos.VERTICAL;
    			} else {
    				targetPos = ShooterPos.HIGH_GOAL;
    			}
    			break;
//	case LOW_GOAL:
//		if(this.direction){
//			targetPos = ShooterPos.HIGH_GOAL;
//		} else{
//			targetPos = ShooterPos.PICK_UP;
//		}
//		break;
//	case PICK_UP:
//		if(this.direction){
//			targetPos = ShooterPos.LOW_GOAL;
//		} else {
//			//This shouldn't change anything; targetPos should already be currentPos. This 
//			//statement is mostly for readability and to make it easier to extend this switching 
//			//mechanism to other states.
//			targetPos = ShooterPos.PICK_UP;
//		}
//		break;
    	}

    }
    
	protected void initialize() {
		// TODO Auto-generated method stub
		SmartDashboard.putBoolean("Is Robot.initialize() running?",true);
		

		if (targetPos != currentPos) {
			if(direction){
				setShooterAimerMotorSpeed(0.35);
			} else{
				setShooterAimerMotorSpeed(-0.2);
			}
		}
//			switch (targetPos) {
//				case HIGH_GOAL:
//					Robot.shooter.setShooterAimerMotorSpeed(0.5);
//					break;
//				case LOW_GOAL:
//					switch(currentPos){
//						case HIGH_GOAL:
//							Robot.shooter.setShooterAimerMotorSpeed(-0.5);
//							break;
//						case PICK_UP:
//							Robot.shooter.setShooterAimerMotorSpeed(0.5);
//							break;
//					}
//					break;
//				case PICK_UP:
//					Robot.shooter.setShooterAimerMotorSpeed(-0.5);
//					break;
//			}

	}

	public void setShooterAimerMotorSpeed(double speed){
		aimingMotor.set(-speed);
	}
	
	
	protected void execute() {
		// TODO Auto-generated method stub
		//SmartDashboard.putBoolean("Has SetAimAngle.execute run?", true);
		//Robot.shooter.setShooterAimerMotorSpeed(Robot.oi.getShooterY());
	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		SmartDashboard.putBoolean("Has SetAimAngle.isFinished() run?", true);
		return getIRSensor(targetPos);
	}

	protected void end() {
		// TODO Auto-generated method stub
		SmartDashboard.putBoolean("Has SetAimAngle.end() run?", true);
		
		//SmartDashboard.putBoolean("Is SetAimAngle executing?", false);
		//SmartDashboard.putBoolean("Is SetAimAngle.execute() running?", false);
		//SmartDashboard.putBoolean("Is SetAimAngle.isFinished() running?", false);
		
		currentPos = targetPos;
		setShooterAimerMotorSpeed(0);
	}

	protected void interrupted() {
		// TODO Auto-generated method stub
		SmartDashboard.putBoolean("Has SetAimAngle.interrupted() run?", true);
		
		//SmartDashboard.putBoolean("Is SetAimAngle executing?", false);
		//SmartDashboard.putBoolean("Is SetAimAngle.execute() running?", false);
		//SmartDashboard.putBoolean("Is SetAimAngle.isFinished() running?", false);
		
		end();	
	}

	
	
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
