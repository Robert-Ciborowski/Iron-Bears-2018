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
		// System.out.println("Gyro OA: " + outputAngle);

		double targetAngularSpeed = targetSpeed / RobotStructureConstants.WHEEL_RADIUS;
		// Convert from 1/s to in/s.
		targetAngularSpeed *= (RobotStructureConstants.MOTOR_MAX_RATE / RobotStructureConstants.ENCODER_COUNTS_PER_INCH);

		double angleOffset = (outputAngle * RobotStructureConstants.DISTANCE_BETWEEN_WHEELS
				/ RobotStructureConstants.WHEEL_RADIUS) / 2;

		// Because we're bad and because our algorithm is sketchy, we have to multiply by some random constant.
		angleOffset *= RobotTuningConstants.TURN_POST_SCALE;

		// This converts the previous speed to speed of the wheel in rotations/second.
		double leftSpeed = targetAngularSpeed - angleOffset;
		double rightSpeed = targetAngularSpeed + angleOffset;

//		System.out.println("TAS: " + targetAngularSpeed);
		
		// someRandomConstant is actually supposed to be the values commented out below, according to our algorithm.
		// However, it doesn't seem to be (due to the values of RobotStrucureConstants potentially being wrong). So
		// instead, we can find some random constant experimentally and use it!
		double someRandomConstant = 0.01798360938;

//		leftSpeed *= RobotStructureConstants.WHEEL_RADIUS * (RobotStructureConstants.ENCODER_COUNTS_PER_INCH / RobotStructureConstants.MOTOR_MAX_RATE);
//		rightSpeed *= RobotStructureConstants.WHEEL_RADIUS * (RobotStructureConstants.ENCODER_COUNTS_PER_INCH / RobotStructureConstants.MOTOR_MAX_RATE);
		
		leftSpeed *= someRandomConstant;
		rightSpeed *= someRandomConstant;

		leftSpeed = Math.max(-1, Math.min(1, leftSpeed));
		rightSpeed = Math.max(-1, Math.min(1, rightSpeed));

//		System.out.println("Target Speed: " + targetSpeed);
//		System.out.println("Target Angular Speed: " + targetAngularSpeed);
//		System.out.println("Angle Offset: " + angleOffset);
		System.out.println("Left Gyro PID Output: " + leftSpeed);
//		System.out.println("Right: " + rightSpeed);
//		System.out.println("--------------------");
		// System.out.println("Left speed: " + leftSpeed);
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
