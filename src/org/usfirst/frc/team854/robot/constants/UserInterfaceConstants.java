/*
 * Class: UserInterfaceConstants
 * Author: Waseef Nayeem, Robert Ciborowski and Julian Dominguez-Schatz
 * Date: 06/01/2018
 * Description: Contains constants related to the robot's IO interface.
 */

package org.usfirst.frc.team854.robot.constants;

import subsystems.TurningMode;

public interface UserInterfaceConstants {
	public static final boolean FLIP_GYRO = false;

	// This value is based on the individual gyro and is determined from a data sheet (in volts/degree/second).
	public static final double GYRO_SENSITIVITY = 0.00705;

	public static final double ENCODER_COUNTS_PER_INCH = 141.7761453778885;

	// in counts/second
	public static final double ENCODER_MAX_RATE_LEFT = 20000;
	public static final double ENCODER_MAX_RATE_RIGHT = 20000;

	public static final boolean leftMotorInverted = true;
	public static final boolean rightMotorInverted = false;
	public static final boolean leftMiniCIMInverted = true;
	public static final boolean rightMiniCIMInverted = false;

	public static final double JOYSTICK_INPUT_CUTOFF = 0.05;
	
	public static final TurningMode INITIAL_TURNING_MODE = TurningMode.ABSOLUTE;
}
