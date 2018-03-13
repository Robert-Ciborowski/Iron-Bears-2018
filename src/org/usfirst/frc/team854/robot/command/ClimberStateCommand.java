/**
 * Name: ClimbOn
 * Authors: Julian Dominguez-Schatz, Robert Ciborowski
 * Date: 17/02/2018
 * Description: A command for turning on the climb motor.
 */

package org.usfirst.frc.team854.robot.command;

import org.usfirst.frc.team854.robot.Robot;
import org.usfirst.frc.team854.robot.constants.RobotCommandConstants;
import org.usfirst.frc.team854.robot.utils.Direction1D;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberStateCommand extends Command {
	
	private Direction1D direction;

	public ClimberStateCommand(Direction1D direction) {
		this.direction = direction;
		requires(Robot.intakeSubsystem);
	}

	@Override
	protected void initialize() {
		switch (direction) {
			case FORWARD:
				Robot.climberSubsystem.setMotor(RobotCommandConstants.CLIMBER_MOTOR_RAISE_SPEED);
				break;
			case REVERSE:
				Robot.climberSubsystem.setMotor(-RobotCommandConstants.CLIMBER_MOTOR_LOWER_SPEED);
				break;
			case OFF:
			default:
				Robot.climberSubsystem.setMotor(0);
				break;
		}
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
	}
}