/*
 * Name: AutoMiddleCommandGroup
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
import static org.usfirst.frc.team854.robot.constants.RobotCommandConstants.*;

public class AutoMiddleCommandGroup extends AutoCommandGroup {
	public AutoMiddleCommandGroup() {
	}
	
	@Override
	public void init() {
		if (!config.shouldOverextend()) {
			loadNone();
			return;
		}

		FieldTarget target = config.getFieldTarget();
		Position1D position = config.getPositionForTarget(target);
		switch (target) {
			case LOCAL_SWITCH:
				loadSwitch(position);
				break;
			case SCALE:
				// loadScale(fieldState.charAt(1) == 'L');
				loadNone();
				break;
			case NONE:
				loadNone();
				break;
			default:
				break;
		}
	}

	private void loadNone() {
		addSequential(new LinearMotionCommand(DISTANCE_TO_FRONT_CUBE_PILE / 2));
		addSequential(new AngularMotionCommand(Math.PI / 2)); // + for left/- for right
		// Switch width (56) or switch length (154.5)?
		addSequential(new LinearMotionCommand(53 + (SWITCH_LENGTH / 2)));
		addSequential(new AngularMotionCommand(0));
		addSequential(new LinearMotionCommand(NONE_DISTANCE_FORWARD - (DISTANCE_TO_FRONT_CUBE_PILE / 2)));
	}

	private void loadSwitch(Position1D position) {
		addParallel(new ArmLevelCommand(RobotArmLevel.SWITCH));
		if (position == Position1D.LEFT) {
			addSequential(new LinearMotionCommand(DISTANCE_TO_FRONT_CUBE_PILE / 2));
			addSequential(new AngularMotionCommand(Math.PI / 4));
			addSequential(new LinearMotionCommand(61.5)); // pythagoras
		} else {
			addSequential(new LinearMotionCommand(DISTANCE_TO_FRONT_CUBE_PILE / 2));
			addSequential(new AngularMotionCommand(-Math.PI / 4));
			addSequential(new LinearMotionCommand(61.5)); // pythagoras
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
