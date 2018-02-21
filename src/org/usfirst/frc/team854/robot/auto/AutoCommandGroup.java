package org.usfirst.frc.team854.robot.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public abstract class AutoCommandGroup extends CommandGroup {
	
	public abstract void init(String fieldState, AutoTarget target);

}
