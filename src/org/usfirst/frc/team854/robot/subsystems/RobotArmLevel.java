/**
 * Name: Turning Mode
 * Authors: Julian Dominguez-Schatz, Robert Ciborowski
 * Date: 13/01/2018
 * Description: Defines turning modes that affect how the joystick turns the robot.
 */

package org.usfirst.frc.team854.robot.subsystems;

public enum RobotArmLevel {
	GROUND(0), SWITCH(350), SCALE(1000), CLIMB(1001);
	
	private double setpoint;
	
	private RobotArmLevel(double setpoint) {
		this.setpoint = setpoint;
	}

	public double getSetpoint() {
		return setpoint;
	}
}
