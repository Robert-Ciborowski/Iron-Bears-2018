package org.usfirst.frc.team854.robot.hardware;

import org.usfirst.frc.team854.robot.constants.RobotInterfaceConstants;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;

public class Sensors {
	public static final AnalogGyro gyro = new AnalogGyro(RobotInterfaceConstants.PORT_GYRO);
	public static final Encoder leftEncoder = new Encoder(RobotInterfaceConstants.PORT_ENCODER_LEFT_1, RobotInterfaceConstants.PORT_ENCODER_LEFT_2),
			rightEncoder = new Encoder(RobotInterfaceConstants.PORT_ENCODER_RIGHT_1, RobotInterfaceConstants.PORT_ENCODER_RIGHT_2);
}
