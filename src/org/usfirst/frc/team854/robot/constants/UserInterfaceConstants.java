/*
 * Class: UserInterfaceConstants
 * Authors: Robert Ciborowski, Waseef Nayeem
 * Date: 06/01/2018
 * Description: Contains constants related to the robot's IO interface.
 */

package org.usfirst.frc.team854.robot.constants;

import org.usfirst.frc.team854.robot.subsystems.TurningMode;

public interface UserInterfaceConstants {
	public static final boolean FLIP_GYRO = false;

	// This value is based on the individual gyro and is determined from a data sheet (in volts/degree/second).
	// public static final double GYRO_SENSITIVITY = 0.00700;

	public static final boolean MOTOR_LEFT_INVERT = false;
	public static final boolean MOTOR_RIGHT_INVERT = true;
	public static final boolean MINICIM_LEFT_INVERT = false;
	public static final boolean MINICIM_RIGHT_INVERT = true;

	public static final double JOYSTICK_TURNING_CUTOFF = 0.2;
	public static final double JOYSTICK_TURNING_MAX_RAW = 1.5;
	public static final double JOYSTICK_SPEED_CUTOFF = 0.05;
	public static final double JOYSTICK_TURNING_OFFSET = 0;
	public static final double JOYSTICK_SPEED_OFFSET = 0;
	
	public static final TurningMode INITIAL_TURNING_MODE = TurningMode.RELATIVE;

	// These are the operator interface axes and buttons.
	public static final int PORT_JOYSTICK = 0;
	public static final int AXIS_ID_SPEED = 1;
	public static final int AXIS_ID_TURN = 0;
	public static final int AXIS_ID_MAX_SPEED = 3;
}
