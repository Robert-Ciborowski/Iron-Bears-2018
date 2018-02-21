/*
 * Name: AutoLeftCommandGroup
 * Author: Julian Dominguez-Schatz, Robert Ciborowski
 * Date: 20/02/2018
 * Description: The command that runs when auto starts if the robot starts at the middle.
 */

package org.usfirst.frc.team854.robot.auto;

import org.usfirst.frc.team854.robot.command.AngularMotionCommand;
import org.usfirst.frc.team854.robot.command.ArmLevelCommand;
import org.usfirst.frc.team854.robot.command.IntakeSpitCommand;
import org.usfirst.frc.team854.robot.command.LinearMotionCommand;
import org.usfirst.frc.team854.robot.subsystems.RobotArmLevel;

public class AutoMiddleCommandGroup extends AutoCommandGroup {
	public AutoMiddleCommandGroup() {
	}
	
	@Override
	public void init(String fieldState, AutoTarget target) {
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
			addSequential(new LinearMotionCommand(15));
			addSequential(new AngularMotionCommand(Math.PI / 2));
			addSequential(new LinearMotionCommand(5));
			addSequential(new AngularMotionCommand(-(Math.PI / 2)));
			addSequential(new LinearMotionCommand(15));
		} else {
			addSequential(new LinearMotionCommand(15));
			addSequential(new AngularMotionCommand(-(Math.PI / 2)));
			addSequential(new LinearMotionCommand(5));
			addSequential(new AngularMotionCommand(Math.PI / 2));
			addSequential(new LinearMotionCommand(15));
		}
		addSequential(new IntakeSpitCommand());
	}

	private void loadScale(boolean targetOnLeft) {
		addParallel(new ArmLevelCommand(RobotArmLevel.SCALE));
		if (targetOnLeft) {
			addSequential(new LinearMotionCommand(15));
			addSequential(new AngularMotionCommand(Math.PI / 2));
			addSequential(new LinearMotionCommand(15));
			addSequential(new AngularMotionCommand(-(Math.PI / 2)));
			addSequential(new LinearMotionCommand(30));
		} else {
			addSequential(new LinearMotionCommand(15));
			addSequential(new AngularMotionCommand(-(Math.PI / 2)));
			addSequential(new LinearMotionCommand(15));
			addSequential(new AngularMotionCommand(Math.PI / 2));
			addSequential(new LinearMotionCommand(30));
		}
		addSequential(new IntakeSpitCommand());
	}
}
