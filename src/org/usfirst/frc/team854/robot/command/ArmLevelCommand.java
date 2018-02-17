/*
 * Name: ArmLevelCommand
 * Author: Robert Ciborowski
 * Date: 17/02/2018
 * Description: A command for rotating the arm.
 */

package org.usfirst.frc.team854.robot.command;

import org.usfirst.frc.team854.robot.Robot;
import org.usfirst.frc.team854.robot.subsystems.RobotArmLevel;
import org.usfirst.frc.team854.robot.utils.Direction1D;

import edu.wpi.first.wpilibj.command.Command;

public class ArmLevelCommand extends Command {
	private RobotArmLevel targetLevel;
	
	public ArmLevelCommand(RobotArmLevel level) {
		requires(Robot.armSubsystem);
		requires(Robot.intakeSubsystem);
		targetLevel = level;
	}

	@Override
	public void initialize() {
		Robot.intakeSubsystem.setOuterIntakeDirection(Direction1D.REVERSE);
		Robot.armSubsystem.setArmLevel(targetLevel);
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.armSubsystem.isArmOnTarget();
	}
}
