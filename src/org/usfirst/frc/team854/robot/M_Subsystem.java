package org.usfirst.frc.team854.robot;


import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class M_Subsystem extends Subsystem {

	public void periodic() {}
	
	public void init() {}
	
	/**
	 * Update the dashboard every cycle.
	 */
	public abstract void updateDashboard();
}
