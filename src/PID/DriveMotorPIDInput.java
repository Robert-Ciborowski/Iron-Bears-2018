/*
 * Class: DriveMotorPIDInput
 * Author: Robert Ciborowski
 * Date: 10/01/2018
 * Description: A class which uses driving encoders to give a PID controller the difference in motor speeds.
 *              Note that we may eventually use gyroscope rotation with our PID Controller instead.
 */

package PID;

import org.usfirst.frc.team854.robot.RobotInterfaceConstants;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveMotorPIDInput implements PIDSource {
	private M_Gyro gyro = new M_Gyro(RobotInterfaceConstants.gyroPort);

	@Override
	// Do nothing with this method as it is not used.
	public void setPIDSourceType(PIDSourceType pidSource) {
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		// This takes the difference in rates. If traveling perfectly straight, this should be zero.
		//return rightEncoder.getRate() / RobotInterfaceConstants.rightEncoderMaxRate - leftEncoder.getRate() / RobotInterfaceConstants.leftEncoderMaxRate;
		return Math.toRadians(gyro.getAngle());
	}
	
	public void updateDashboard() {
		SmartDashboard.putNumber("Gyro angle", gyro.getAngle());
		SmartDashboard.putData("Gyro", gyro);
	}

	public void init() {
    	gyro.initGyro();
    	gyro.setSensitivity(RobotInterfaceConstants.gyroGain);
    	gyro.calibrate();
	}

	public void reset() {
		gyro.reset();
	}
}
