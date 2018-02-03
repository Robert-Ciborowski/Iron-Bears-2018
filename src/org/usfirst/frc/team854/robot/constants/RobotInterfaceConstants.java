/*
 * Class: CompoundFilter
 * Author: Julian Dominguez-Schatz, Robert Ciborowski, Waseef Nayeem
 * Date: 13/01/2018
 * Description: An interface which stores constants related to the inputs and outputs on
 *              the RoboRIO and operator interface.
 */

package org.usfirst.frc.team854.robot.constants;

public interface RobotInterfaceConstants {
	// These are the PWM ports.
	public static final int PORT_MOTOR_LEFT = 1;
	public static final int PORT_MOTOR_RIGHT = 0;
	public static final int PORT_MINICIM_LEFT = 3;
	public static final int PORT_MINICIM_RIGHT = 2;

	// These are the DIO ports.
	public static final int PORT_ENCODER_LEFT_1 = 2;
	public static final int PORT_ENCODER_LEFT_2 = 3;
	public static final int PORT_ENCODER_RIGHT_1 = 0;
	public static final int PORT_ENCODER_RIGHT_2 = 1;
	
	// These are the Analog ports.
	public static final int PORT_GYRO = 0;
}
