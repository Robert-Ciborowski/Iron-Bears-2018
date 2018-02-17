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
	public static final int PORT_MOTOR_DRIVE_LEFT = 1;
	public static final int PORT_MOTOR_DRIVE_RIGHT = 0;
	public static final int PORT_MOTOR_MINICIM_LEFT = 3;
	public static final int PORT_MOTOR_MINICIM_RIGHT = 2;
	public static final int PORT_MOTOR_ARM = 4;
	public static final int PORT_MOTOR_INTAKE_INNER_LEFT = 5;
	public static final int PORT_MOTOR_INTAKE_INNER_RIGHT = 6;
	public static final int PORT_MOTOR_INTAKE_OUTER_LEFT = 7;
	public static final int PORT_MOTOR_INTAKE_OUTER_RIGHT = 8;

	// These are the DIO ports.
	public static final int PORT_ENCODER_LEFT = 2;
	public static final int PORT_ENCODER_LEFT_2 = 3;
	public static final int PORT_ENCODER_RIGHT = 0;
	public static final int PORT_ENCODER_RIGHT_2 = 1;
	public static final int PORT_ENCODER_ARM = 4;
	public static final int PORT_ENCODER_ARM_2 = 5;
	public static final int PORT_SWITCH_ARM_HOME = 6;
	public static final int PORT_SWITCH_INTAKE_FULL = 7;

	// These are the analog ports.
	public static final int PORT_GYRO = 0;
	
	// These are the CAN ports.
	public static final int PORT_PCM = 0;
	
	// These are the pneumatic control channels.
	public static final int PORT_PNEUMATIC_LEFT = -1;
	public static final int PORT_PNEUMATIC_LEFT_REVERSE = -1;
	public static final int PORT_PNEUMATIC_RIGHT = -1;
	public static final int PORT_PNEUMATIC_RIGHT_REVERSE = -1;
}
