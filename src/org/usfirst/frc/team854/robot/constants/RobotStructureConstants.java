/*
 * Class: RobotStructureConstants
 * Author: Waseef Nayeem, Robert Ciborowski and Julian Dominguez-Schatz
 * Date: 06/01/2018
 * Description: Contains constants related to the robot's physical structure.
 */

package org.usfirst.frc.team854.robot.constants;

public interface RobotStructureConstants {
	// These are encoder max rates, in counts/second (with no load, add or subtract 10%)
	public static final double MOTOR_MAX_RATE = 12744.0;

	// The following variables use inches.
	public static final double DISTANCE_BETWEEN_WHEELS = 23.75;// 19.25;
	public static final int WHEEL_RADIUS = 3;// 2;
	public static final int ENCODER_COUNTS_PER_REVOLUTION = 1440;
	public static final double ENCODER_COUNTS_PER_INCH = ENCODER_COUNTS_PER_REVOLUTION / (WHEEL_RADIUS * Math.PI * 2);
}
