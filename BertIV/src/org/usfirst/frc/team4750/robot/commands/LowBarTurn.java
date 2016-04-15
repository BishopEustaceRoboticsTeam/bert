package org.usfirst.frc.team4750.robot.commands;

import org.usfirst.frc.team4750.robot.RobotValues;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowBarTurn extends CommandGroup {
    
    public  LowBarTurn() {
        // Add Commands here:
        addSequential(new LowerShooter());
        addSequential(new DriveStraight(RobotValues.LOW_DRIVE_TIME));
        addSequential(new Turn(RobotValues.TURN_TIME, -1));
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
