package org.usfirst.frc.team854.robot.hardware;

import org.usfirst.frc.team854.robot.constants.RobotInterfaceConstants;

import edu.wpi.first.wpilibj.Spark;

public class Motors {
	public static Spark leftMotor = new Spark(RobotInterfaceConstants.PORT_MOTOR_LEFT);
	public static Spark rightMotor = new Spark(RobotInterfaceConstants.PORT_MOTOR_RIGHT);
	public static Spark leftIntakeMotor = new Spark(RobotInterfaceConstants.PORT_INTAKE_LEFT);
	public static Spark rightIntakeMotor = new Spark(RobotInterfaceConstants.PORT_INTAKE_RIGHT);
	public static Spark armMotor = new Spark(RobotInterfaceConstants.PORT_MOTOR_ARM);
}
