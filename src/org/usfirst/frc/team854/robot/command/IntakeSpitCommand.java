/**
 * Name: IntakeSpitCommand
 * Authors: Julian Dominguez-Schatz, Robert Ciborowski
 * Date: 17/02/2018
 * Description: A command for spitting out a cube.
 */

package org.usfirst.frc.team854.robot.command;

import org.usfirst.frc.team854.robot.Robot;
import org.usfirst.frc.team854.robot.constants.RobotCommandConstants;
import org.usfirst.frc.team854.robot.utils.Direction1D;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeSpitCommand extends Command {

	private long startTime;

	public IntakeSpitCommand() {
		requires(Robot.intakeSubsystem);
	}

	@Override
	protected void initialize() {
		startTime = System.currentTimeMillis();

		Robot.intakeSubsystem.setInnerIntakeDirection(Direction1D.REVERSE);
		Robot.intakeSubsystem.setOuterIntakeDirection(Direction1D.REVERSE);
	}

	@Override
	protected boolean isFinished() {
		return (System.currentTimeMillis() - startTime) >= RobotCommandConstants.INTAKE_SPIT_DURATION_MS;
	}

	@Override
	protected void end() {
		Robot.intakeSubsystem.setInnerIntakeDirection(Direction1D.OFF);
		Robot.intakeSubsystem.setOuterIntakeDirection(Direction1D.REVERSE);
	}
}