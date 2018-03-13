/**
 * Name: ChassisSubsystem
 * Authors: Robert Ciborowski, Julian-Dominguez-Schatz, Waseef Nayeem,
 *          Shaiza Hashma, Rana Rauf, Danny Xu
 * Date: 20/01/2018
 * Description: The subsystem of our robot that represents the drive chassis.
 */

package org.usfirst.frc.team854.robot.subsystems;

import org.usfirst.frc.team854.robot.CustomSubsystem;
import org.usfirst.frc.team854.robot.Robot;
import org.usfirst.frc.team854.robot.RobotMode;
import org.usfirst.frc.team854.robot.PID.DistancePIDInput;
import org.usfirst.frc.team854.robot.PID.DistancePIDOutput;
import org.usfirst.frc.team854.robot.PID.GyroPIDInput;
import org.usfirst.frc.team854.robot.PID.GyroPIDOutput;
import org.usfirst.frc.team854.robot.constants.RobotInterfaceConstants;
import org.usfirst.frc.team854.robot.constants.RobotTuningConstants;
import org.usfirst.frc.team854.robot.constants.UserInterfaceConstants;
import org.usfirst.frc.team854.robot.hardware.InterfaceType;
import org.usfirst.frc.team854.robot.operatorinterface.OperatorInterface;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ChassisSubsystem extends CustomSubsystem {
	// These are our PID inputs, outputs and controllers.
	private GyroPIDInput gyroPIDInput = new GyroPIDInput();
	private GyroPIDOutput gyroPIDOutput = new GyroPIDOutput();
	private PIDController gyroPIDController = new PIDController(RobotTuningConstants.DRIVE_PROPORTIONAL,
			RobotTuningConstants.DRIVE_INTEGRAL, RobotTuningConstants.DRIVE_DERIVATIVE,
			RobotTuningConstants.DRIVE_FEED_FORWARD, gyroPIDInput, gyroPIDOutput);
	private DistancePIDInput distancePIDInput = new DistancePIDInput(0);
	private DistancePIDOutput distancePIDOutput = new DistancePIDOutput();
	private PIDController distancePIDController = new PIDController(RobotTuningConstants.DISTANCE_PROPORTIONAL,
			RobotTuningConstants.DISTANCE_INTEGRAL, RobotTuningConstants.DISTANCE_DERIVATIVE,
			RobotTuningConstants.DISTANCE_FEED_FORWARD, distancePIDInput, distancePIDOutput);

	// These are the drive motors.
	private Spark leftMotor = Robot.devices.getDevice(InterfaceType.PWM, RobotInterfaceConstants.PORT_MOTOR_DRIVE_LEFT);
	private Spark rightMotor = Robot.devices.getDevice(InterfaceType.PWM, RobotInterfaceConstants.PORT_MOTOR_DRIVE_RIGHT);
//	private Spark leftMiniCIMMotor = Robot.devices.getDevice(InterfaceType.PWM,
//			RobotInterfaceConstants.PORT_MOTOR_MINICIM_LEFT);
//	private Spark rightMiniCIMMotor = Robot.devices.getDevice(InterfaceType.PWM,
//			RobotInterfaceConstants.PORT_MOTOR_MINICIM_RIGHT);

	public ChassisSubsystem() {
		leftMotor.setInverted(UserInterfaceConstants.MOTOR_LEFT_INVERT);
		rightMotor.setInverted(UserInterfaceConstants.MOTOR_RIGHT_INVERT);
//		leftMiniCIMMotor.setInverted(UserInterfaceConstants.MINICIM_LEFT_INVERT);
//		rightMiniCIMMotor.setInverted(UserInterfaceConstants.MINICIM_RIGHT_INVERT);

		gyroPIDController.setInputRange(-Math.PI, Math.PI);
		gyroPIDController.setOutputRange(-Math.PI, Math.PI);
		gyroPIDController.setSetpoint(0);
		gyroPIDController.setAbsoluteTolerance(0.05);
		gyroPIDController.setContinuous(false);
		
		distancePIDController.setInputRange(-100000, 100000);
		distancePIDController.setOutputRange(-1, 1);
		distancePIDController.setSetpoint(1);
		distancePIDController.setAbsoluteTolerance(0.05);
		distancePIDController.setContinuous(false);
		
		currentMode = RobotMode.DISABLED;
	}

	public void init() {
		gyroPIDInput.init();
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		if (enabled) {
			setCurrentMode(currentMode);
		} else {
			gyroPIDController.disable();
			distancePIDController.disable();
//			OperatorInterface.mainJoystickCommand.setEnabled(false);
		}
	}

	@Override
	public void setCurrentMode(RobotMode mode) {
		super.setCurrentMode(mode);
		if (enabled) {
			switch (mode) {
				case TELEOPERATED:
					setGyroTargetMotion(0, 0);
					gyroPIDController.enable();
					distancePIDController.disable();
					OperatorInterface.mainJoystickCommand.setEnabled(true);
					gyroPIDInput.reset();
					break;
				case AUTONOMOUS:
					gyroPIDController.reset();
					distancePIDController.reset();
					gyroPIDController.enable();
					distancePIDController.enable();
					OperatorInterface.mainJoystickCommand.setEnabled(false);
					gyroPIDInput.reset();
					break;
				case DISABLED:
					gyroPIDController.disable();
					distancePIDController.disable();
					OperatorInterface.mainJoystickCommand.setEnabled(false);
					break;
				case TEST:
					break;
				default:
					break;
			}
		}
	}

	public void setAutonomousTarget(double angle, double distance) {
		// If it doesn't drive, this is the likely culprit.
		if (currentMode == RobotMode.AUTONOMOUS) {
			distancePIDInput.setDistance(distance);
			distancePIDController.setSetpoint(1);
			gyroPIDInput.setTargetAngle(angle);
		}
	}

	public boolean isAutonomousOnTarget() {
		return distancePIDController.onTarget() && gyroPIDController.onTarget();
	}

	public void setMotors(double leftMotorValue, double rightMotorValue) {
		if (enabled) {
			leftMotor.pidWrite(leftMotorValue);
			rightMotor.pidWrite(rightMotorValue);
//			System.out.println(leftMotorValue);
//			System.out.println(rightMotorValue);
	//		leftMiniCIMMotor.pidWrite(-leftMotorValue);
	//		rightMiniCIMMotor.pidWrite(-rightMotorValue);
		}
	}

	public void setTurningMode(TurningMode turningMode) {
		gyroPIDInput.setTurningMode(turningMode);
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(OperatorInterface.mainJoystickCommand);
	}

	@Override
	public void updateDashboard() {
		gyroPIDInput.updateDashboard();
		gyroPIDOutput.updateDashboard();
		distancePIDInput.updateDashboard();
		SmartDashboard.putData("Gyro Controller", gyroPIDController);
		SmartDashboard.putData("Distance Controller", distancePIDController);
	}

	public boolean isAngleOnTarget() {
		return gyroPIDController.onTarget();
	}

	public void setGyroTargetMotion(double angle, double speed) {
		gyroPIDInput.setTargetAngle(angle);
		gyroPIDOutput.setTargetSpeed(speed);
	}
}
