/*
 * Class: RobotStructureConstants
 * Author: Waseef Nayeem, Robert Ciborowski and Julian Dominguez-Schatz
 * Date: 06/01/2018
 * Description: Contains constants related to the robot's physical structure.
 */

package org.usfirst.frc.team854.robot.constants;

public interface RobotStructureConstants {
	// The following variables use inches.
	public static final double DISTANCE_BETWEEN_WHEELS = 19.25;
	public static final int WHEEL_RADIUS = 2;
	double ENCODER_COUNTS_PER_INCH = 141.7761453778885;
	
	// These are encoder max rates, in counts/second
	double ENCODER_MAX_RATE_LEFT = 20000;
	double ENCODER_MAX_RATE_RIGHT = 20000;
}
