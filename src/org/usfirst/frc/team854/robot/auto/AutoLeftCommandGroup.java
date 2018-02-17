/*
 * Name: AutoLeftCommandGroup
 * Author: Julian Dominguez-Schatz
 * Date: 17/02/2018
 * Description: The command that runs when auto starts if the robot starts on the left.
 */

package org.usfirst.frc.team854.robot.auto;

import org.usfirst.frc.team854.robot.command.AngularMotionCommand;
import org.usfirst.frc.team854.robot.command.ArmLevelCommand;
import org.usfirst.frc.team854.robot.command.IntakeSpitCommand;
import org.usfirst.frc.team854.robot.command.LinearMotionCommand;
import org.usfirst.frc.team854.robot.subsystems.RobotArmLevel;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLeftCommandGroup extends CommandGroup {
	public AutoLeftCommandGroup(String fieldState, AutoTarget target) {
		if (fieldState.length() < 3) {
			throw new IllegalStateException("You aren't in auto.");
		}

		switch (target) {
			case SWITCH:
				loadSwitch(fieldState.charAt(0) == 'L');
				break;
			case SCALE:
				loadScale(fieldState.charAt(1) == 'L');
				break;
			case NONE:
				loadNone();
				break;
			default:
				break;
		}
	}

	private void loadNone() {
		addSequential(new LinearMotionCommand(20));
	}

	private void loadSwitch(boolean targetOnLeft) {
		addParallel(new ArmLevelCommand(RobotArmLevel.SWITCH));
		if (targetOnLeft) {
			addSequential(new LinearMotionCommand(30));
			addSequential(new AngularMotionCommand(-(Math.PI / 2)));
		} else {
			addSequential(new LinearMotionCommand(20));
			addSequential(new AngularMotionCommand(-(Math.PI / 2)));
			addSequential(new LinearMotionCommand(50));
			addSequential(new AngularMotionCommand(Math.PI / 2));
			addSequential(new LinearMotionCommand(10));
		}
		addSequential(new IntakeSpitCommand());
	}

	private void loadScale(boolean targetOnLeft) {
		addParallel(new ArmLevelCommand(RobotArmLevel.SCALE));
		if (targetOnLeft) {
			addSequential(new LinearMotionCommand(50));
		} else {
			addSequential(new LinearMotionCommand(40));
			addSequential(new AngularMotionCommand(-(Math.PI / 4)));
			addSequential(new LinearMotionCommand(5));
		}
		addSequential(new IntakeSpitCommand());
	}
}
