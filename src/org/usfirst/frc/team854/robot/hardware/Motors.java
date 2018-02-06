package org.usfirst.frc.team854.robot.hardware;

import org.usfirst.frc.team854.robot.constants.RobotInterfaceConstants;

import edu.wpi.first.wpilibj.Spark;

public class Motors {
	public static Spark leftMotor = new Spark(RobotInterfaceConstants.PORT_MOTOR_LEFT);
	public static Spark rightMotor = new Spark(RobotInterfaceConstants.PORT_MOTOR_RIGHT);
	public static Spark leftMiniCIM = new Spark(RobotInterfaceConstants.PORT_MINICIM_LEFT);
	public static Spark rightMiniCIM = new Spark(RobotInterfaceConstants.PORT_MINICIM_RIGHT);
}
