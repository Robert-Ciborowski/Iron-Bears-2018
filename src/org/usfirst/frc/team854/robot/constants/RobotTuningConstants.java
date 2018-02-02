/*
 * Class: RobotTuningConstants
 * Author: Waseef Nayeem, Robert Ciborowski and Julian Dominguez-Schatz
 * Date: 06/01/2018
 * Description: Contains constants related to PID tuning constants.
 */

package org.usfirst.frc.team854.robot.constants;

public interface RobotTuningConstants {
	public static final double DRIVE_PROPORTIONAL = 3.02;
	public static final double DRIVE_INTEGRAL = 0.18072289;
	public static final double DRIVE_DERIVATIVE = 0.12048192;//0.04518072;
	public static final double DRIVE_FEED_FORWARD = 1.0;
	
	public static final double TURN_POST_SCALE = 4.0;
}
