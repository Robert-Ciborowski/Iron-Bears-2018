/*
 * Class: PeriodicSubsystem
 * Author: Christopher Lansdale and Robert Ciborowski
 * Date: A long time ago (2016)
 * Description: An abstract class for a subsystem which updates itself
 *              and the dashboard periodically.
 */

package org.usfirst.frc.team854.robot;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class PeriodicSubsystem extends Subsystem {

	public void periodic() {}
	
	public void init() {}
	
	/** Updates the FRC dashboard every cycle.*/
	public abstract void updateDashboard();
}
