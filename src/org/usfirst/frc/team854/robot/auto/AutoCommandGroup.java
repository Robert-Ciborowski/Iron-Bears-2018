package org.usfirst.frc.team854.robot.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public abstract class AutoCommandGroup extends CommandGroup {
	
	protected AutoConfig config;
	
	public final void configure(AutoConfig config) {
		this.config = config;
		init();
	}
	
	protected abstract void init();

}
