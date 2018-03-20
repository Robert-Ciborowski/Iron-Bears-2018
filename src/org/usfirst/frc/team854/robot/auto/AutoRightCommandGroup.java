/*
 * Name: AutoRightCommandGroup
 * Author: Julian Dominguez-Schatz, Robert Ciborowski
 * Date: 20/02/2018
 * Description: The command that runs when auto starts if the robot starts on the right.
 */

package org.usfirst.frc.team854.robot.auto;

import static org.usfirst.frc.team854.robot.constants.RobotCommandConstants.*;

import org.usfirst.frc.team854.robot.command.AngularMotionCommand;
import org.usfirst.frc.team854.robot.command.ArmLevelCommand;
import org.usfirst.frc.team854.robot.command.IntakeSpitCommand;
import org.usfirst.frc.team854.robot.command.LinearMotionCommand;
import org.usfirst.frc.team854.robot.subsystems.RobotArmLevel;

public class AutoRightCommandGroup extends AutoCommandGroup {
	public AutoRightCommandGroup() {
	}
	
	@Override
	public void init() {
		FieldTarget target = config.getFieldTarget();
		Position1D position = config.getPositionForTarget(target);
		switch (target) {
			case LOCAL_SWITCH:
				loadSwitch(position);
				break;
			case SCALE:
				loadScale(position);
				break;
			case NONE:
				loadNone();
				break;
			default:
				break;
		}
	}

	private void loadNone() {
		addSequential(new LinearMotionCommand(NONE_DISTANCE_FORWARD));
	}

	private void loadSwitch(Position1D position) {
		System.out.println("hisw");
		addParallel(new ArmLevelCommand(RobotArmLevel.SWITCH));
		if (position == Position1D.RIGHT) {
			addSequential(new LinearMotionCommand(DISTANCE_TO_SWITCH_EDGE + (SWITCH_WIDTH / 2) - (ROBOT_LENGTH / 2)));
			addSequential(new AngularMotionCommand((Math.PI / 2)));
		} else if (config.shouldOverextend()) {
			addSequential(new LinearMotionCommand(DISTANCE_TO_ALLEY));
			addSequential(new AngularMotionCommand(Math.PI / 2));
			addSequential(new LinearMotionCommand(DISTANCE_DOWN_ALLEY / 2));
			addSequential(new AngularMotionCommand(3 * (Math.PI / 4)));
			addSequential(new LinearMotionCommand(16.854));
		} else {
			loadNone();
			return;
		}
		addSequential(new IntakeSpitCommand());
	}

	private void loadScale(Position1D position) {
		System.out.println("hisc");
		addParallel(new ArmLevelCommand(RobotArmLevel.SCALE));
		if (position == Position1D.RIGHT) {
			addSequential(new LinearMotionCommand(DISTANCE_TO_SCALE));
			addSequential(new AngularMotionCommand(-3 * Math.PI / 4));
		} else if (config.shouldOverextend()) {
			addSequential(new LinearMotionCommand(DISTANCE_TO_ALLEY));
			addSequential(new AngularMotionCommand(Math.PI / 2));
			addSequential(new LinearMotionCommand(DISTANCE_DOWN_ALLEY));
			addSequential(new AngularMotionCommand(0));
			addSequential(new LinearMotionCommand(DISTANCE_TO_SCALE - DISTANCE_TO_ALLEY));
			addSequential(new AngularMotionCommand(3 * (Math.PI / 4)));
		} else {
			loadNone();
			return;
		}
		addSequential(new IntakeSpitCommand());
	}
}
