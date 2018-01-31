/*
 * Class: RobotTuningConstants
 * Author: Waseef Nayeem, Robert Ciborowski and Julian Dominguez-Schatz
 * Date: 06/01/2018
 * Description: Contains constants related to PID tuning constants.
 */

package org.usfirst.frc.team854.robot.constants;

public interface RobotTuningConstants {
	public static final double DRIVE_PROPORTIONAL = 12.0;
	public static final double DRIVE_INTEGRAL = 0.22727272727;
	public static final double DRIVE_DERIVATIVE = 0.05681818181;
	public static final double DRIVE_FEED_FORWARD = 1.0;
	
	public static final double TURN_POST_SCALE = 4.0;
}
