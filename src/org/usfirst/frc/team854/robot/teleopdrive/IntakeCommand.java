/**
 * Name: JoystickCommand
 * Authors: Robert Ciborowski
 * Date: 20/01/2018
 * Description: Interfaces the joystick hardware input in a software environment
 */

package org.usfirst.frc.team854.robot.teleopdrive;

import org.usfirst.frc.team854.robot.Robot;
import org.usfirst.frc.team854.robot.subsystems.Direction1D;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeCommand extends Command {

	public IntakeCommand() {
		requires(Robot.intakeSubsystem);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
    	if (Robot.intakeSubsystem.isFull()) {
    		Robot.intakeSubsystem.setOuterIntakeDirection(Direction1D.REVERSE);
    	}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		
	}
}