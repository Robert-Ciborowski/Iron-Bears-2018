/*
 * Class: DriveToDistance
 * Author: Christopher Lansdale, Waseef Nayeem and Robert Ciborowski
 * Date: A long time ago (2016)
 * Description: A command for driving a certain distance.
 */

package org.usfirst.frc.team854.robot;


import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class PeriodicSubsystem extends Subsystem {

	public void periodic() {}
	
	public void init() {}
	
	/**
	 * Update the dashboard every cycle.
	 */
	public abstract void updateDashboard();
}
