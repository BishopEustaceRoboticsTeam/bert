package org.usfirst.frc.team4750.robot.commands;

import org.usfirst.frc.team4750.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Shoot extends CommandGroup {

	public Shoot() {
		// TODO Auto-generated constructor stub
		requires(Robot.shooter);
		addParallel(new SetAimAngle());
		addParallel(new SetShooterSpeed());
	}
	
}	
