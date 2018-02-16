/**
 * Name: ChassisSubsystem
 * Authors: Robert Ciborowski
 * Date: 20/01/2018
 * Description: Personal implementation of FRC's periodic subsystem, mainly adding some PID features
 */

package org.usfirst.frc.team854.robot.subsystems;

import org.usfirst.frc.team854.robot.CustomSubsystem;
import org.usfirst.frc.team854.robot.Robot;
import org.usfirst.frc.team854.robot.RobotMode;
import org.usfirst.frc.team854.robot.PID.DistancePID;
import org.usfirst.frc.team854.robot.PID.DistancePIDInput;
import org.usfirst.frc.team854.robot.PID.DistancePIDOutput;
import org.usfirst.frc.team854.robot.PID.GyroPIDInput;
import org.usfirst.frc.team854.robot.PID.GyroPIDOutput;
import org.usfirst.frc.team854.robot.constants.RobotInterfaceConstants;
import org.usfirst.frc.team854.robot.constants.RobotStructureConstants;
import org.usfirst.frc.team854.robot.constants.RobotTuningConstants;
import org.usfirst.frc.team854.robot.constants.UserInterfaceConstants;
import org.usfirst.frc.team854.robot.hardware.InterfaceType;
import org.usfirst.frc.team854.robot.teleopdrive.JoystickCommand;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ChassisSubsystem extends CustomSubsystem {
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

	private RobotMode currentMode;

	private Spark leftMotor = Robot.devices.getDevice(InterfaceType.PWM, RobotInterfaceConstants.PORT_MOTOR_LEFT);
	private Spark rightMotor = Robot.devices.getDevice(InterfaceType.PWM, RobotInterfaceConstants.PORT_MOTOR_RIGHT);
	private Spark leftMiniCIMMotor = Robot.devices.getDevice(InterfaceType.PWM,
			RobotInterfaceConstants.PORT_MOTOR_MINICIM_LEFT);
	private Spark rightMiniCIMMotor = Robot.devices.getDevice(InterfaceType.PWM,
			RobotInterfaceConstants.PORT_MOTOR_MINICIM_RIGHT);

	public ChassisSubsystem() {
		leftMotor.setInverted(UserInterfaceConstants.MOTOR_LEFT_INVERT);
		rightMotor.setInverted(UserInterfaceConstants.MOTOR_RIGHT_INVERT);
		leftMiniCIMMotor.setInverted(UserInterfaceConstants.MINICIM_LEFT_INVERT);
		rightMiniCIMMotor.setInverted(UserInterfaceConstants.MINICIM_RIGHT_INVERT);

		gyroPIDController.setInputRange(-Math.PI, Math.PI);
		gyroPIDController.setOutputRange(-Math.PI, Math.PI);
		gyroPIDController.setSetpoint(0);
		distancePIDController.setInputRange(0, 10);
		distancePIDController.setOutputRange(-1, 1);
		distancePIDController.setSetpoint(1);
		distancePIDController.setAbsoluteTolerance(0.05);
		currentMode = RobotMode.DISABLED;
	}

	public void init() {
		gyroPIDInput.init();
		setTeleoperatedTargetMotion(0, 0);
	}

	public void reset() {
		gyroPIDController.reset();
		// autonomousPIDController.setSetpoint(0);
	}

	public void setCurrentMode(RobotMode mode) {
		currentMode = mode;
		switch (mode) {
			case TELEOPERATED:
				gyroPIDController.enable();
				distancePIDController.disable();
				// distancePIDController.enable();
				break;
			case AUTONOMOUS:
				distancePIDController.disable();
				gyroPIDController.enable();
				// distancePIDController.enable();
				break;
		}
	}

	public void setTeleoperatedTargetMotion(double angle, double speed) {
		// If it doesn't drive, this is the likely culprit.
		if (currentMode == RobotMode.TELEOPERATED) {
			if (!gyroPIDController.isEnabled()) {
				gyroPIDController.enable();
			}

			gyroPIDInput.setTargetAngle(angle);
			gyroPIDOutput.setTargetSpeed(speed);
		}
		// System.out.println("Updated joystick turn!: " + angle);
	}

	public void setAutonomousTarget(double angle, double distance) {
		// If it doesn't drive, this is the likely culprit.
		if (currentMode == RobotMode.AUTONOMOUS) {
			distancePIDInput.setDistance(distance);
			distancePIDController.setSetpoint(1);
			System.out.println("A setpoint has been set: " + distance);
		}
		// System.out.println("Updated joystick turn!: " + angle);
	}

	public boolean isAutonomousOnTarget() {
		return distancePIDController.onTarget();
	}

	public void endAutonomousCommand() {
		distancePIDController.setSetpoint(0);
	}

	public void setMotors(double leftMotorValue, double rightMotorValue) {
		leftMotor.pidWrite(leftMotorValue);
		rightMotor.pidWrite(rightMotorValue);
		leftMiniCIMMotor.pidWrite(-leftMotorValue);
		rightMiniCIMMotor.pidWrite(-rightMotorValue);
	}

	public void setTurningMode(TurningMode turningMode) {
		gyroPIDInput.setTurningMode(turningMode);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new JoystickCommand());
	}

	@Override
	public void updateDashboard() {
		gyroPIDInput.updateDashboard();
		gyroPIDOutput.updateDashboard();
		SmartDashboard.putData("Motor Controller", gyroPIDController);
	}

	public boolean isAngleOnTarget() {
		return gyroPIDController.onTarget();
	}
}
