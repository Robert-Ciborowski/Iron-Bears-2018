/**
 * Name: M_Gyro
 * Authors: Robert Ciborowski
 * Date: 20/01/2018
 * Description: 
 */

package PID;

import org.usfirst.frc.team854.robot.constants.UserInterfaceConstants;

import edu.wpi.first.wpilibj.AnalogGyro;

public class M_Gyro extends AnalogGyro {
	public M_Gyro(int port) {
		super(port);
	}

	@Override
	public double getAngle() {
		double angle = super.getAngle() % 360;
		if (!UserInterfaceConstants.FLIP_GYRO) {
			angle = 360 - angle;
		}
		return (angle < 0) ? angle + 360 : angle;
	}
	
	public double getAngleDifference(double targetAngle) {
		double currentAngle = getAngle();
		double difference = targetAngle - currentAngle;
		
		if (difference > 180) {
			difference -= 360;
		} else if (difference < -180) {
			difference += 360;
		}
		
		return difference;
	}
}

