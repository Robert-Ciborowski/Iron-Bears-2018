/*
 * Class: DriveMotorPIDOutput
 * Author: Robert Ciborowski
 * Date: 10/01/2018
 * Description: A class which is used by a Motor PID Controller to drive motors. The class is given an offset
 *              value by the PID Controller which helps to make sure that the robot travels in the right direction.
 */

package PID;

import org.usfirst.frc.team854.robot.RobotInterfaceConstants;
import org.usfirst.frc.team854.robot.RobotStructureConstants;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveMotorPIDOutput implements PIDOutput {
	private Spark leftMotor = new Spark(RobotInterfaceConstants.rightMotorPort);
	private Spark rightMotor = new Spark(RobotInterfaceConstants.leftMotorPort);
	private Spark leftMiniCIM = new Spark(RobotInterfaceConstants.leftMiniCIMPort);
	private Spark rightMiniCIM = new Spark(RobotInterfaceConstants.rightMiniCIMPort);
	
	// These are the base speeds to use.
	private double targetSpeed = 0;
	private double outputAngle;
	
	public DriveMotorPIDOutput() {
		leftMotor.setInverted(RobotInterfaceConstants.leftMotorInverted);
    	rightMotor.setInverted(RobotInterfaceConstants.rightMotorInverted);
    	leftMiniCIM.setInverted(RobotInterfaceConstants.leftMiniCIMInverted);
    	rightMiniCIM.setInverted(RobotInterfaceConstants.rightMiniCIMInverted);
	}
	
	@Override
	public void pidWrite(double outputAngle) {
		// There's a potential problem here. Example: what if "rightSpeed + output / 2" goes over the motor's
		// maximum value? The old code, which did its motor control entirely in M_PIDController, had the
		// same issue. To do: look at M_PIDController's solution to this.
		
		double leftSpeed = targetSpeed / RobotStructureConstants.WHEEL_RADIUS;
		double rightSpeed = leftSpeed + (outputAngle * RobotStructureConstants.DISTANCE_BETWEEN_WHEELS / RobotStructureConstants.WHEEL_RADIUS);

		leftSpeed *= RobotInterfaceConstants.encoderCountsPerInch / RobotInterfaceConstants.leftEncoderMaxRate;
		rightSpeed *= RobotInterfaceConstants.encoderCountsPerInch / RobotInterfaceConstants.rightEncoderMaxRate;
		
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
	
	public void setSpeed(double speed) {
		this.targetSpeed = speed;
	}
}
