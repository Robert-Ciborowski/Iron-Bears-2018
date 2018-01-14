/*
 * Class: Robot Interface Constants
 * Author: Waseef Nayeem, Robert Ciborowski and Julian Dominguez-Schatz
 * Date: 06/01/2018
 * Description: Contains constants related to the robot's IO interface.
 */

package org.usfirst.frc.team854.robot;

public interface RobotInterfaceConstants {

	// These are the PWM ports.
	public static final int climberPDPChannel = 10;
	public static final int leftMotorPort = 1;
	public static final int rightMotorPort = 0;
	public static final int leftMiniCIMPort = 3;
	public static final int rightMiniCIMPort = 2;
	public static final int intakeMotorPort = 4;
	public static final int climberMotorPort = 5;
	public static final int servo1Port = 6;
	public static final int servo2Port = 7;

	public static final int indexerCANMotorPort = 1;
	public static final int shooterCANMotorPort = 2;
	// shooterSparkMotorPort is TEMPORARILY set to 9 until we find a suitable port.
	public static final int shooterVictorMotorPort = 9;

	// These are the DIO ports.
	public static final int leftEncoder1 = 2;
	public static final int leftEncoder2 = 3;
	public static final int rightEncoder1 = 0;
	public static final int rightEncoder2 = 1;
	public static final int shooterMotorEncoder1 = 6;
	public static final int shooterMotorEncoder2 = 7;
	//public static final int switch0 = 8;
	//public static final int switch1 = 9;
	
	// These are the Analog ports.
	public static final int gyroPort = 0;
	public static final boolean gyroIsFlipped = false;

	public static final double gyroGain = -0.00165 * 3.805555 * 1.1041666 * 1.0166666666;

	public static final double encoderCountsPerInch = 141.7761453778885;
	public static final double leftEncoderMaxRate = 20000;
	public static final double rightEncoderMaxRate = 20000;
	// We don't know the max rates of the shooter encoders.
	public static final double shooterEncoderMaxRate = 10000000;

	public static final boolean leftMotorInverted = true;
	public static final boolean rightMotorInverted = false;
	public static final boolean shooterMotorInverted = true;
	// We don't yet know if we need to invert shooterMotor2Inverted.
	public static final boolean shooterMotor2Inverted = true;
	public static final boolean intakeMotorInverted = true;
	public static final boolean climberMotorInverted = true;
	public static final boolean leftMiniCIMInverted = true;
	public static final boolean rightMiniCIMInverted = false;
	public static final boolean indexerMotorInverted = true;

	public static final int joystickPort = 0;

	public static final double joystickCenterSensitivity = 0.003;
	public static final int SpeedAxis = 1;
	public static final int TurnAxis = 0;
	public static final int throttleAxis = 3;

	public static final double shooterSpeed = 0.95;
	public static final double indexerSpeed = 1.0;
	public static final double intakeSpeed = 0.8;
	public static final double climberSpeed = 1.0;
}
