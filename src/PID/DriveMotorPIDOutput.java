/*
 * Class: DriveMotorPIDOutput
 * Author: Robert Ciborowski
 * Date: 10/01/2018
 * Description: A class which is used by a Motor PID Controller to drive motors. The class is given an offset
 *              value by the PID Controller which helps to make sure that the robot travels in the right direction.
 */

package PID;

import org.usfirst.frc.team854.robot.constants.UserInterfaceConstants;
import org.usfirst.frc.team854.robot.constants.RobotInterfaceConstants;
import org.usfirst.frc.team854.robot.constants.RobotStructureConstants;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveMotorPIDOutput implements PIDOutput {
	private Spark leftMotor = new Spark(RobotInterfaceConstants.PORT_MOTOR_LEFT);
	private Spark rightMotor = new Spark(RobotInterfaceConstants.PORT_MOTOR_RIGHT);
	private Spark leftMiniCIM = new Spark(RobotInterfaceConstants.PORT_MINICIM_LEFT);
	private Spark rightMiniCIM = new Spark(RobotInterfaceConstants.PORT_MINICIM_RIGHT);
	
	// These are the base speeds to use.
	private double targetSpeed = 0;
	private double outputAngle;
	
	public DriveMotorPIDOutput() {
		leftMotor.setInverted(UserInterfaceConstants.leftMotorInverted);
    	rightMotor.setInverted(UserInterfaceConstants.rightMotorInverted);
    	leftMiniCIM.setInverted(UserInterfaceConstants.leftMiniCIMInverted);
    	rightMiniCIM.setInverted(UserInterfaceConstants.rightMiniCIMInverted);
	}
	
	@Override
	public void pidWrite(double outputAngle) {
		// convert from 1/s to in/s
		targetSpeed *= UserInterfaceConstants.ENCODER_MAX_RATE_LEFT / UserInterfaceConstants.ENCODER_COUNTS_PER_INCH;
		
		// convert to speed of the wheel in rotations/s
		double leftSpeed = targetSpeed / (RobotStructureConstants.WHEEL_RADIUS);
		double rightSpeed = leftSpeed + (outputAngle * RobotStructureConstants.DISTANCE_BETWEEN_WHEELS / RobotStructureConstants.WHEEL_RADIUS);

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
