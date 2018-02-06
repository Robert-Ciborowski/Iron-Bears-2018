/*
 * Class: DriveMotorPIDOutput
 * Author: Robert Ciborowski
 * Date: 10/01/2018
 * Description: A class which is used by a Motor PID Controller to drive motors. The class is given an offset
 *              value by the PID Controller which helps to make sure that the robot is oriented in the right direction.
 */
package PID;

import org.usfirst.frc.team854.robot.constants.UserInterfaceConstants;
import org.usfirst.frc.team854.robot.constants.RobotInterfaceConstants;
import org.usfirst.frc.team854.robot.constants.RobotStructureConstants;
import org.usfirst.frc.team854.robot.constants.RobotTuningConstants;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import hardware.Motors;

public class DriveMotorPIDOutput implements PIDOutput {
	private Spark leftMotor = Motors.leftMotor;
	private Spark rightMotor = Motors.rightMotor;
	private Spark leftMiniCIM = Motors.leftMiniCIM;
	private Spark rightMiniCIM = Motors.rightMiniCIM;

	// These are the base speeds to use.
	private double targetSpeed;
	private double outputAngle;

	public DriveMotorPIDOutput() {
		leftMotor.setInverted(UserInterfaceConstants.leftMotorInverted);
		rightMotor.setInverted(UserInterfaceConstants.rightMotorInverted);
		leftMiniCIM.setInverted(UserInterfaceConstants.leftMiniCIMInverted);
		rightMiniCIM.setInverted(UserInterfaceConstants.rightMiniCIMInverted);
	}

	@Override
	public void pidWrite(double outputAngle) {
		// System.out.println("Output angle: " + outputAngle);
		// convert from 1/s to in/s
		targetSpeed *= UserInterfaceConstants.ENCODER_MAX_RATE_LEFT / UserInterfaceConstants.ENCODER_COUNTS_PER_INCH;

		double targetAngularSpeed = targetSpeed / RobotStructureConstants.WHEEL_RADIUS;
		double angleOffset = (outputAngle * RobotStructureConstants.DISTANCE_BETWEEN_WHEELS
				/ RobotStructureConstants.WHEEL_RADIUS) / 2;

		// TEMPORARY
		angleOffset *= RobotTuningConstants.TURN_POST_SCALE;

		// System.out.println("Angle offset: " + angleOffset);

		// convert to speed of the wheel in rotations/s
		double leftSpeed = targetAngularSpeed + angleOffset;
		double rightSpeed = targetAngularSpeed - angleOffset;

		leftSpeed /= (UserInterfaceConstants.ENCODER_MAX_RATE_LEFT / UserInterfaceConstants.ENCODER_COUNTS_PER_INCH);
		rightSpeed /= (UserInterfaceConstants.ENCODER_MAX_RATE_RIGHT / UserInterfaceConstants.ENCODER_COUNTS_PER_INCH);

		leftMotor.pidWrite(leftSpeed);
		leftMiniCIM.pidWrite(-leftSpeed);
		rightMotor.pidWrite(rightSpeed);
		leftMiniCIM.pidWrite(-rightSpeed);

		this.outputAngle = outputAngle;
	}

	public void updateDashboard() {
		SmartDashboard.putData("Left Motor", leftMotor);
		SmartDashboard.putData("Right Motor", rightMotor);
		SmartDashboard.putNumber("PID Angular Correction", outputAngle);
	}

	public void setTargetSpeed(double targetSpeed) {
		this.targetSpeed = targetSpeed;
	}
}
