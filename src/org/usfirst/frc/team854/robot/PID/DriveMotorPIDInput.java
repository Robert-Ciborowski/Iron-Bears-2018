/*
 * Class: DriveMotorPIDInput
 * Author: Robert Ciborowski, Julian Dominguez-Schatz
 * Date: 10/01/2018
 * Description: A class which uses a gyro to give a PID controller the robot rotation.
 *              Note that we may eventually use gyroscope rotation with our PID Controller instead.
 */

package org.usfirst.frc.team854.robot.PID;

import org.usfirst.frc.team854.robot.constants.UserInterfaceConstants;
import org.usfirst.frc.team854.robot.hardware.Sensors;
import org.usfirst.frc.team854.robot.subsystems.TurningMode;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveMotorPIDInput implements PIDSource {	
	private double targetAngle = 0;
	private double currentAngleForRelativePID = 0;
	private long timeOfLastPIDGet = 0;

	private TurningMode turningMode = UserInterfaceConstants.INITIAL_TURNING_MODE;

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
			// This takes the difference between the actual heading and the target angle. If travelling at the right
			// angle, this should be 0.
			double gyroAngle = Math.toRadians(Sensors.gyro.getAngle());
			double transformedTargetAngle = targetAngle - gyroAngle;
			return descaleValue(transformedTargetAngle, -Math.PI, Math.PI);
		} else if (turningMode == TurningMode.RELATIVE) {
			long currentTime = System.currentTimeMillis();
			currentAngleForRelativePID -= targetAngle * (currentTime - timeOfLastPIDGet) / 1000;
			timeOfLastPIDGet = currentTime;
			
			double gyroAngle = Math.toRadians(Sensors.gyro.getAngle());
			double transformedTargetAngle = currentAngleForRelativePID - gyroAngle;
			//System.out.println("Joystick-Stored Angle: " + currentAngleForRelativePID);
			//System.out.println("Angle provided by joy: " + transformedTargetAngle + ", Gyro Angle: " + gyroAngle);
			// System.out.println("Target Angle for PID: " + currentAngleForRelativePID + ", Target Angle: " + targetAngle);
			// transformedTargetAngle = descaleValue(transformedTargetAngle, -Math.PI, Math.PI);
			// System.out.println("Current angle for relative (input): " + transformedTargetAngle + " " + targetAngle);
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
		SmartDashboard.putNumber("Gyro angle", Sensors.gyro.getAngle());
		SmartDashboard.putNumber("Gyro offset", Sensors.gyro.getOffset());
		SmartDashboard.putData("Gyro", Sensors.gyro);
		SmartDashboard.putNumber("angle", currentAngleForRelativePID);
	}

	public void init() {		
		Sensors.gyro.initGyro();
		Sensors.gyro.setSensitivity(UserInterfaceConstants.GYRO_SENSITIVITY);
		Sensors.gyro.calibrate();
	}

	public void reset() {
		Sensors.gyro.reset();
	}

	public void setTargetAngle(double targetAngle) {
		this.targetAngle = targetAngle;
	}
}
