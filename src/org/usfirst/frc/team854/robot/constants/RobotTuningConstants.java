/*
 * Class: RobotTuningConstants
 * Author: Waseef Nayeem, Robert Ciborowski and Julian Dominguez-Schatz
 * Date: 06/01/2018
 * Description: Contains constants related to PID tuning constants.
 */

package org.usfirst.frc.team854.robot.constants;

public interface RobotTuningConstants {
	public static final double DRIVE_PROPORTIONAL = 7.0; // 3.02, 2.5, 9
	public static final double DRIVE_INTEGRAL = 0.3; // 0.18072289, 0, 0.36
	public static final double DRIVE_DERIVATIVE = 35; // 0.12048192, 0, 0.24
	public static final double DRIVE_FEED_FORWARD = 1.0;
	
	public static final double DISTANCE_PROPORTIONAL = 0.5;
	public static final double DISTANCE_INTEGRAL = 0;
	public static final double DISTANCE_DERIVATIVE = 0;

	public static final double DISTANCE_FEED_FORWARD = 0.0;


	public static final double ARM_PROPORTIONAL = 7.0; // 3.02, 2.5, 9
	public static final double ARM_INTEGRAL = 0.3; // 0.18072289, 0, 0.36
	public static final double ARM_DERIVATIVE = 35; // 0.12048192, 0, 0.24
	public static final double ARM_FEED_FORWARD = 1.0;

	
	public static final double TURN_POST_SCALE = 4.0;
}
