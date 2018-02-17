/**
 * Name: IntakeSwallowCommand
 * Authors: Julian Dominguez-Schatz, Robert Ciborowski
 * Date: 17/02/2018
 * Description: A command for swallowing a cube.
 */

package org.usfirst.frc.team854.robot.command;

import org.usfirst.frc.team854.robot.Robot;
import org.usfirst.frc.team854.robot.utils.Direction1D;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeSwallowCommand extends Command {

	public IntakeSwallowCommand() {
		requires(Robot.intakeSubsystem);
	}

	@Override
	protected void initialize() {
		if (!Robot.armSubsystem.isInHomePosition()) {
			return;
		}

		Robot.intakeSubsystem.setPneumaticsExtended(false);
		Robot.intakeSubsystem.setInnerIntakeDirection(Direction1D.FORWARD);
		Robot.intakeSubsystem.setOuterIntakeDirection(Direction1D.FORWARD);
	}

	@Override
	protected boolean isFinished() {
		return Robot.intakeSubsystem.isFull();
	}

	@Override
	protected void end() {
		Robot.intakeSubsystem.setOuterIntakeDirection(Direction1D.REVERSE);
	}
}