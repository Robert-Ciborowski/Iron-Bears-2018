/**
 * Name: IntakeStateCommand
 * Authors: Julian Dominguez-Schatz, Robert Ciborowski
 * Date: 17/03/2018
 * Description: A command for adjusting the intake state.
 */

package org.usfirst.frc.team854.robot.command;

import org.usfirst.frc.team854.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeStateCommand extends Command {

	private double leftSpeed;
	private double rightSpeed;
	private boolean feelerState;

	public IntakeStateCommand(double speed, boolean feelerState) {
		this(speed, speed, feelerState);
	}

	public IntakeStateCommand(double leftSpeed, double rightSpeed, boolean feelerState) {
		requires(Robot.intakeSubsystem);
		this.leftSpeed = leftSpeed;
		this.rightSpeed = rightSpeed;
		this.feelerState = feelerState;
	}

	@Override
	protected void initialize() {
		Robot.intakeSubsystem.setOuterMotors(leftSpeed, rightSpeed);
		Robot.intakeSubsystem.setPneumaticsExtended(feelerState);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
	}
}