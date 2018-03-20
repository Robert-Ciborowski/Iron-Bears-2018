/**
 * Name: IntakeFeelersCommand
 * Authors: Julian Dominguez-Schatz, Robert Ciborowski
 * Date: 17/02/2018
 * Description: A command for opening and closing the feelers.
 */

package org.usfirst.frc.team854.robot.command;

import org.usfirst.frc.team854.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeFeelersCommand extends Command {

	public IntakeFeelersCommand() {
		requires(Robot.intakeSubsystem);
	}

	@Override
	protected void initialize() {
		Robot.intakeSubsystem.setPneumaticsExtended(!Robot.intakeSubsystem.getPneumaticsExtended());
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
	}
}