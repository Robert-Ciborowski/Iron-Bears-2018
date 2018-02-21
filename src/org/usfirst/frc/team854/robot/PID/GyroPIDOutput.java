/*
 * Class: GyroPIDOutput
 * Author: Robert Ciborowski, Julian Dominguez-Schatz
 * Date: 10/01/2018
 * Description: A class which is used by a gyro PID controller to drive motors.
 */
package org.usfirst.frc.team854.robot.PID;

import org.usfirst.frc.team854.robot.Robot;
import org.usfirst.frc.team854.robot.constants.RobotStructureConstants;
import org.usfirst.frc.team854.robot.constants.RobotTuningConstants;
import org.usfirst.frc.team854.robot.constants.UserInterfaceConstants;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GyroPIDOutput implements PIDOutput {
	// These are the base values to use.
	private double targetSpeed;

	public GyroPIDOutput() {
	}

	@Override
	public void pidWrite(double outputAngle) {
		// Convert from 1/s to in/s.
		targetSpeed *= RobotStructureConstants.ENCODER_MAX_RATE_LEFT / RobotStructureConstants.ENCODER_COUNTS_PER_INCH;
		
		double targetAngularSpeed = targetSpeed / RobotStructureConstants.WHEEL_RADIUS;
		double angleOffset = (outputAngle * RobotStructureConstants.DISTANCE_BETWEEN_WHEELS
				/ RobotStructureConstants.WHEEL_RADIUS) / 2;

		// Because we're bad and because our algorithm is sketchy, we have to multiply by some random constant.
		angleOffset *= RobotTuningConstants.TURN_POST_SCALE;

		// This converts the previous speed to speed of the wheel in rotations/s.
		double leftSpeed = targetAngularSpeed - angleOffset;
		double rightSpeed = targetAngularSpeed + angleOffset;

		leftSpeed /= (RobotStructureConstants.ENCODER_MAX_RATE_LEFT / RobotStructureConstants.ENCODER_COUNTS_PER_INCH);
		rightSpeed /= (UserInterfaceConstants.ENCODER_MAX_RATE_RIGHT / RobotStructureConstants.ENCODER_COUNTS_PER_INCH);
		
//		leftSpeed = Math.max(0, Math.min(0.1, leftSpeed));
//		rightSpeed = Math.max(0, Math.min(0.1, rightSpeed));
		
		Robot.chassisSubsystem.setMotors(leftSpeed, rightSpeed);
//		System.out.println("Motors: " + leftSpeed + " " + rightSpeed);
		SmartDashboard.putNumber("Output Angle: ", outputAngle);
	}

	public void updateDashboard() {
	}

	public void setTargetSpeed(double targetSpeed) {
		this.targetSpeed = targetSpeed;
	}
}
