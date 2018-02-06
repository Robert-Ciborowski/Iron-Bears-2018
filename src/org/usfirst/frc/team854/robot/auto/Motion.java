/** 
 * Class: Motion
 * Author: Rana, Lucas, Cole, Robert Ciborowski, Danny Xu
 * Date: 03/02/2018
 * Description: A base class for moving the drive train in autonomous mode.
 */

package org.usfirst.frc.team854.robot.auto;

import org.usfirst.frc.team854.robot.constants.RobotInterfaceConstants;
import org.usfirst.frc.team854.robot.hardware.Sensors;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

public class Motion extends Command implements RobotInterfaceConstants {
	private double distance, speed, angle, distanceRemaining;
	
	private Encoder leftEncoder = Sensors.leftEncoder, rightEncoder = Sensors.rightEncoder;
	private AnalogGyro gyro = Sensors.gyro;

	public Motion(double distance, double angle) {
		this.distance = distance;
		this.angle = angle;
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
}
