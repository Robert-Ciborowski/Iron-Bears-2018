/*
 * Class: CustomSubsystem
 * Author: Christopher Lansdale and Robert Ciborowski
 * Date: A long time ago (2016)
 * Description: An abstract class for a subsystem which updates itself
 *              and the dashboard periodically.
 */

package org.usfirst.frc.team854.robot;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class CustomSubsystem extends Subsystem {
	
	protected boolean enabled = true;
	protected RobotMode currentMode;
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setCurrentMode(RobotMode mode) {
		this.currentMode = mode;
	}
	
	/** Updates the FRC dashboard every cycle.*/
	public abstract void updateDashboard();
}
