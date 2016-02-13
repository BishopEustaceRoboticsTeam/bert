package org.usfirst.frc.team4750.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Shoot extends CommandGroup {

	public Shoot(double angle, double shooterSpeed, boolean servoPosition) {
		// TODO Auto-generated constructor stub
		addSequential(new SetAimAngle());
		addSequential(new SetShooterSpeed());
		addSequential(new SetShooterServo(servoPosition));
	}

}	
