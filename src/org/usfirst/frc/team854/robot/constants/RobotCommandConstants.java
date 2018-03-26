/*
 * Class: RobotCommandConstants
 * Author: Julian Dominguez-Schatz
 * Date: 17/02/2018
 * Description: An interface which stores constants related to commands.
 */

package org.usfirst.frc.team854.robot.constants;

public interface RobotCommandConstants {
	public static long INTAKE_SPIT_DURATION_MS = 1000;
	public static long INTAKE_FULL_SWITCH_DELAY_MS = 350;
	public static double INTAKE_MOTOR_SPEED = 0.7;
	public static double CLIMBER_MOTOR_LOWER_SPEED = 1;
	public static double CLIMBER_MOTOR_RAISE_SPEED = 1;
	public static final double CUBE_ADJUST_SPEED = 0.7;

	public static double ROBOT_LENGTH = 32.5;
	public static double ROBOT_WIDTH = 27.5;

	public static double DISTANCE_TO_FRONT_CUBE_PILE = 97;
	public static double DISTANCE_TO_SWITCH_EDGE = 139;
	public static double SWITCH_WIDTH = 56;
	public static double SWITCH_LENGTH = 154.5;
	public static double DISTANCE_TO_SCALE = 271;
	public static double DISTANCE_TO_ALLEY = 233;
	public static double DISTANCE_DOWN_ALLEY = 260.5;
	public static double NONE_DISTANCE_FORWARD = 85 + 1.5 * ROBOT_LENGTH;
}
