/*
 * Class: GyroPIDInput
 * Author: Robert Ciborowski, Julian Dominguez-Schatz
 * Date: 10/01/2018
 * Description: A class which uses a gyro to give a PID controller the robot rotation.
 *              Note that we may eventually use gyroscope rotation with our PID Controller instead.
 */

package org.usfirst.frc.team854.robot.PID;

import org.usfirst.frc.team854.robot.Robot;
import org.usfirst.frc.team854.robot.constants.RobotInterfaceConstants;
import org.usfirst.frc.team854.robot.constants.UserInterfaceConstants;
import org.usfirst.frc.team854.robot.hardware.InterfaceType;
import org.usfirst.frc.team854.robot.subsystems.TurningMode;

import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GyroPIDInput implements PIDSource {	
	private double targetAngle = 0;
	private double currentAngleForRelativePID = 0;
	private long timeOfLastPIDGet = 0;

	private TurningMode turningMode = UserInterfaceConstants.INITIAL_TURNING_MODE;
	
	// private final AnalogGyro gyro = Robot.devices.getDevice(InterfaceType.ANALOG, RobotInterfaceConstants.PORT_GYRO);
	private final ADIS16448_IMU gyro = Robot.devices.getDevice(InterfaceType.MXP, RobotInterfaceConstants.PORT_GYRO);
	
	public GyroPIDInput() {
	}

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
		if (turningMode == TurningMode.ABSOLUTE) {
			// This was done using the old gyro.
			double gyroAngle = -Math.toRadians(gyro.getAngle());
			
			// This gets the angle via the new gyro.
			// double gyroAngle = Math.toRadians(gyro.getAngleX());
			
			// This takes the difference between the actual heading and the target angle. If travelling at the right
			// angle, this should be 0. That's how absolute turning works.
			double transformedTargetAngle = targetAngle - gyroAngle;
			// System.out.println("Target angle: " + targetAngle);
			// System.out.println(targetAngle + " is the target angle, " + gyroAngle + " is the gyro angle!");
			// return descaleValue(transformedTargetAngle, -Math.PI, Math.PI);
			return transformedTargetAngle;
		} else if (turningMode == TurningMode.RELATIVE) {
			long currentTime = System.currentTimeMillis();
			currentAngleForRelativePID += targetAngle * (currentTime - timeOfLastPIDGet) / 1000;
			// System.out.println("Actual target: " + targetAngle);
			timeOfLastPIDGet = currentTime;
			
			double gyroAngle = -Math.toRadians(gyro.getAngle());
			// double gyroAngle = Math.toRadians(gyro.getAngleX());
			double transformedTargetAngle = currentAngleForRelativePID - gyroAngle;
			//System.out.println("Joystick-Stored Angle: " + currentAngleForRelativePID);
			//System.out.println("Angle provided by joy: " + transformedTargetAngle + ", Gyro Angle: " + gyroAngle);
			// System.out.println("Target Angle for PID: " + currentAngleForRelativePID + ", Target Angle: " + targetAngle);
			// transformedTargetAngle = descaleValue(transformedTargetAngle, -Math.PI, Math.PI);
			// System.out.println("Current angle for relative (input): " + transformedTargetAngle + " " + targetAngle);
			// System.out.println("Trans: " + transformedTargetAngle);
			// System.out.println("Trans: " + transformedTargetAngle);
			SmartDashboard.putNumber("Target Angle: ", currentAngleForRelativePID);
			return transformedTargetAngle;
		} else {
			throw new IllegalStateException("Stop screwing with the robot.");
		}
	}
	
	/**
	 * Scales a value down into a range. For example, if <code>value</code> is an angle
	 * in radians, and <code>minValue</code> and <code>maxValue</code> are the angles
	 * <code>-PI</code> and <code>PI</code>, then the angle will be scaled down into
	 * an angle between -PI and PI. Note that this is different than <code>clamp</code>,
	 * which will return <code>maxValue</code> if <code>value</code> is greater than
	 * <code>maxValue</code> and <code>minValue</code> if <code>value</code> is less than
	 * <code>minValue</code>.
	 * @param value the value to descale
	 * @param minValue the minimum value on the scale
	 * @param maxValue the maximum value on the scale
	 * @return the descaled value
	 */
	private double descaleValue(double value, double minValue, double maxValue) {
		if (minValue <= value && value <= maxValue) {
			return value;
		}
		
		double valueRange = maxValue - minValue;
		if (value < minValue) {
			return value + valueRange * Math.ceil(Math.abs((minValue - value) / valueRange));
		}
		return value - valueRange * Math.ceil(Math.abs((maxValue - value) / valueRange));
	}
	
	public void setTurningMode(TurningMode turningMode) {
		this.turningMode = turningMode;
	}
	
	public void updateDashboard() {
		// This is for the old gyro.
		// SmartDashboard.putNumber("Gyro-X (radians)", -gyro.getAngle() * (Math.PI / 180.0));
		
		// This is for the new gyro.
		SmartDashboard.putNumber("Gyro-X (radians)", gyro.getAngleX() * (Math.PI / 180.0));
		SmartDashboard.putNumber("angle", currentAngleForRelativePID);
	}

	public void init() {
		// This was all made for the old gyro, not the new one.
//		gyro.initGyro();
//		gyro.setSensitivity(UserInterfaceConstants.GYRO_SENSITIVITY);
//		gyro.calibrate();
	}

	public void reset() {
		gyro.reset();
		targetAngle = 0;
		currentAngleForRelativePID = 0;
	}

	public void setTargetAngle(double targetAngle) {
		this.targetAngle = targetAngle;
		// System.out.println("Target angle is set to: " + targetAngle);
	}
	
	public double getTargetAngle() {
		return targetAngle;
	}

	public double getGyroAngle() {
		// Test robot:
		// return gyro.getAngle();
		// Actual robot:
		return gyro.getAngleX();
	}
}
