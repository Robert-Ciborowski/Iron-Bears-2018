/*
 * Class: RobotTuningConstants
 * Author: Waseef Nayeem, Robert Ciborowski and Julian Dominguez-Schatz
 * Date: 06/01/2018
 * Description: Contains constants related to PID tuning constants.
 */

package org.usfirst.frc.team854.robot.constants;

public interface RobotTuningConstants {
	// These are for the real robot:
	public static final double DRIVE_PROPORTIONAL = 2;// 7.0, 3.02, 2.5, 9
	public static final double DRIVE_INTEGRAL = 0.0021;// 0.3, 0.18072289, 0, 0.36
	public static final double DRIVE_DERIVATIVE = 15;// 35, 0.12048192, 0, 0.24
	public static final double DRIVE_FEED_FORWARD = 0; // 1.0
	public static final double TURN_POST_SCALE = 8.0;

	// This is for the test robot:
//	public static final double DRIVE_PROPORTIONAL = 0.1;//7.0;//, 3.02, 2.5, 9
//	public static final double DRIVE_INTEGRAL = 0.00105;//0.3;//, 0.18072289, 0, 0.36
//	public static final double DRIVE_DERIVATIVE = 1.05;//35;//, 0.12048192, 0, 0.24
//	public static final double DRIVE_FEED_FORWARD = 0;//1.0;
//	public static final double TURN_POST_SCALE = 1.0;

	// These are for the real robot:
	public static final double DISTANCE_PROPORTIONAL = 2.0;
	public static final double DISTANCE_INTEGRAL = 1;
	public static final double DISTANCE_DERIVATIVE = 0;

	// These are for the test robot:
//	public static final double DISTANCE_PROPORTIONAL = 1;
//	public static final double DISTANCE_INTEGRAL = 0.01;
//	public static final double DISTANCE_DERIVATIVE = 3;

	public static final double DISTANCE_FEED_FORWARD = 0.0;

	public static final double ARM_UP_PROPORTIONAL = 0.01; // 3.02, 2.5, 9
	public static final double ARM_UP_INTEGRAL = 4.0E-4; // 0.18072289, 0, 0.36
	public static final double ARM_UP_DERIVATIVE = 2.0E-4; // 0.12048192, 0, 0.24
	public static final double ARM_DOWN_PROPORTIONAL = 0.005; // 3.02, 2.5, 9
	public static final double ARM_DOWN_INTEGRAL = 4.0E-4; // 0.18072289, 0, 0.36
	public static final double ARM_DOWN_DERIVATIVE = 2.0E-4; // 0.12048192, 0, 0.24
	public static final double ARM_FEED_FORWARD = 1.0;
}