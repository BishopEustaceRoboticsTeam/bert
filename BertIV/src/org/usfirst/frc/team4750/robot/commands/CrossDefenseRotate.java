package org.usfirst.frc.team4750.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossDefenseRotate extends CommandGroup {
    
    public  CrossDefenseRotate(double speed, double crossTime, double rotateTime) {
    	addSequential(new Drive(speed, speed, crossTime));
    	addSequential(new Drive(speed, -speed, rotateTime));	//addSequential(new Drive(0, -speed, rotateTime));
    	
        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
