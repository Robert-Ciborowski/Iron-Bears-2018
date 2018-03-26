/**
 * Name: JoystickCommand
 * Authors: Robert Ciborowski, Julian Dominguez-Schatz
 * Date: 20/01/2018
 * Description: Interfaces the joystick hardware input in a software environment.
 */

package org.usfirst.frc.team854.robot.teleopdrive;

import org.usfirst.frc.team854.robot.Robot;
import org.usfirst.frc.team854.robot.RobotMode;
import org.usfirst.frc.team854.robot.constants.UserInterfaceConstants;
import org.usfirst.frc.team854.robot.subsystems.RobotArmLevel;
import org.usfirst.frc.team854.robot.subsystems.TurningMode;
import org.usfirst.frc.team854.robot.utils.Direction1D;

import edu.wpi.first.wpilibj.command.Command;

public class JoystickCommand extends Command {
	public JoystickCommand() {
		requires(Robot.chassisSubsystem);
		requires(Robot.armSubsystem);
	}

	private boolean driveReverseState = false;
	private boolean reverseButtonHeld = false;
	private Direction1D previousPOVDirection;

	private boolean testState = false;
	private boolean testButtonHeld = false;

	@Override
	protected void initialize() {
		previousPOVDirection = getDirection(Robot.oi.getPOVPosition());
	}

	@Override
	protected void execute() {
		Robot.chassisSubsystem.setTurningMode(TurningMode.RELATIVE);

		double speed = Robot.oi.getSpeed(); // Positive forward
		double turn = Robot.oi.getRawTurn(); // Positive Right

		if (Robot.oi.isTestButtonPressed()) {
			if (!testButtonHeld) {
				testButtonHeld = true;
				if (testState) {
					testState = false;
				} else {
					testState = true;
				}

				// Robot.intakeSubsystem.setInnerIntakeDirection(testState ?
				// Direction1D.FORWARD : Direction1D.OFF);
				// Robot.intakeSubsystem.setOuterIntakeDirection(testState ?
				// Direction1D.FORWARD : Direction1D.OFF);
				// Robot.armSubsystem.setArmLevel(testState ? RobotArmLevel.SWITCH : RobotArmLevel.GROUND);
				// Robot.armSubsystem.setMotor(testState ? 0.5 : 0);
				// Robot.intakeSubsystem.setPneumaticsExtended(testState);
			}
		} else {
			testButtonHeld = false;
		}

		if (Robot.oi.getDriveReverse()) {
			if (!reverseButtonHeld) {
				reverseButtonHeld = true;
				if (driveReverseState) {
					driveReverseState = false;
				} else {
					driveReverseState = true;
				}
			}
		} else {
			reverseButtonHeld = false;
		}

		if (driveReverseState) {
			speed *= -1;
		}

		switch (UserInterfaceConstants.TELEOP_DRIVE_MODE) {
			case GYRO_PID:
				Robot.chassisSubsystem.setGyroTargetMotion(turn, speed);
				break;
			case SIMPLE:
				if (turn > 0) {
					Robot.chassisSubsystem.setMotors(speed - turn, speed);
				} else {
					Robot.chassisSubsystem.setMotors(speed, speed + turn);
				}
				break;
			case SCALED:
				double halfTurn = turn / 2.0;
				double left = speed - halfTurn, right = speed + halfTurn;
				double absLeft = Math.abs(left), absRight = Math.abs(right);
				double scalingFactor = 1.0;
				if (absLeft > 1) {
					scalingFactor = 1.0 / absLeft;
				}
				if (absRight > 1) {
					double potentialScaling = 1.0 / absRight;
					if (potentialScaling < scalingFactor) {
						scalingFactor = potentialScaling;
					}
				}
				Robot.chassisSubsystem.setMotors(left * scalingFactor, right * scalingFactor);
				break; 
		}

		Direction1D newDirection = getDirection(Robot.oi.getPOVPosition());
		if (newDirection != previousPOVDirection) {
			previousPOVDirection = newDirection;
			if (previousPOVDirection == Direction1D.FORWARD) {
				Robot.armSubsystem.increaseTargetLevel();
			} else if (previousPOVDirection == Direction1D.REVERSE) {
				Robot.armSubsystem.decreaseTargetLevel();
			}
		}
	}

	private Direction1D getDirection(int newPos) {
		if (newPos >= 0 && (newPos <= 45 || newPos >= 315)) {
			return Direction1D.FORWARD;
		}

		if (newPos >= 135 && newPos <= 225) {
			return Direction1D.REVERSE;
		}

		return Direction1D.OFF;
	}

	@Override
	protected boolean isFinished() {
		return Robot.currentMode != RobotMode.TELEOPERATED;
	}

	@Override
	protected void end() {

	}
}