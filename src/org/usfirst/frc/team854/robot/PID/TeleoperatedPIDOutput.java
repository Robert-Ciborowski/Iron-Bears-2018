/*
 * Class: DriveMotorPIDOutput
 * Author: Robert Ciborowski
 * Date: 10/01/2018
 * Description: A class which is used by a Motor PID Controller to drive motors. The class is given an offset
 *              value by the PID Controller which helps to make sure that the robot is oriented in the right direction.
 */
package org.usfirst.frc.team854.robot.PID;

import org.usfirst.frc.team854.robot.constants.RobotStructureConstants;
import org.usfirst.frc.team854.robot.constants.RobotTuningConstants;
import org.usfirst.frc.team854.robot.constants.UserInterfaceConstants;
import org.usfirst.frc.team854.robot.hardware.Motors;
import org.usfirst.frc.team854.robot.subsystems.ChassisSubsystem;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleoperatedPIDOutput implements PIDOutput {
	private ChassisSubsystem chassisSubsystem;

	// These are the base speeds to use.
	private double targetSpeed;
	private double outputAngle;

	public TeleoperatedPIDOutput(ChassisSubsystem chassisSubsystem) {
		this.chassisSubsystem = chassisSubsystem;
	}

	@Override
	public void pidWrite(double outputAngle) {
		// System.out.println("Output angle: " + outputAngle);
		// convert from 1/s to in/s
		targetSpeed *= RobotStructureConstants.ENCODER_MAX_RATE_LEFT / RobotStructureConstants.ENCODER_COUNTS_PER_INCH;

		double targetAngularSpeed = targetSpeed / RobotStructureConstants.WHEEL_RADIUS;
		double angleOffset = (outputAngle * RobotStructureConstants.DISTANCE_BETWEEN_WHEELS
				/ RobotStructureConstants.WHEEL_RADIUS) / 2;

		// TEMPORARY
		angleOffset *= RobotTuningConstants.TURN_POST_SCALE;

		// System.out.println("Angle offset: " + angleOffset);

		// convert to speed of the wheel in rotations/s
		double leftSpeed = targetAngularSpeed + angleOffset;
		double rightSpeed = targetAngularSpeed - angleOffset;

		leftSpeed /= (RobotStructureConstants.ENCODER_MAX_RATE_LEFT / RobotStructureConstants.ENCODER_COUNTS_PER_INCH);
		rightSpeed /= (UserInterfaceConstants.ENCODER_MAX_RATE_RIGHT / RobotStructureConstants.ENCODER_COUNTS_PER_INCH);
		
		chassisSubsystem.setMotors(leftSpeed, rightSpeed);

		this.outputAngle = outputAngle;
	}

	public void updateDashboard() {
		SmartDashboard.putData("Left Motor", Motors.leftMotor);
		SmartDashboard.putData("Right Motor", Motors.rightMotor);
		SmartDashboard.putNumber("PID Angular Correction", outputAngle);
	}

	public void setTargetSpeed(double targetSpeed) {
		this.targetSpeed = targetSpeed;
	}
}
