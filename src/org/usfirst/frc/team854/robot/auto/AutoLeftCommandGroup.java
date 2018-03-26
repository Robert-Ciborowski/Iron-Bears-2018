/*
 * Name: AutoLeftCommandGroup
 * Author: Julian Dominguez-Schatz, Robert Ciborowski
 * Date: 17/02/2018
 * Description: The command that runs when auto starts if the robot starts on the left.
 */

package org.usfirst.frc.team854.robot.auto;

import org.usfirst.frc.team854.robot.command.AngularMotionCommand;
import org.usfirst.frc.team854.robot.command.ArmLevelCommand;
import org.usfirst.frc.team854.robot.command.IntakeSpitCommand;
import org.usfirst.frc.team854.robot.command.LinearMotionCommand;
import org.usfirst.frc.team854.robot.command.LinearTimedMotionCommand;

import static org.usfirst.frc.team854.robot.constants.RobotCommandConstants.*;

import org.usfirst.frc.team854.robot.Robot;
import org.usfirst.frc.team854.robot.subsystems.RobotArmLevel;

public class AutoLeftCommandGroup extends AutoCommandGroup {
	public AutoLeftCommandGroup() {
	}

	public void init() {
		FieldTarget target = config.getFieldTarget();
		Position1D position = config.getPositionForTarget(target);
		
		Robot.intakeSubsystem.setPneumaticsExtended(false);
		
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
				System.out.println("hi");
				break;
		}
	}

	private void loadNone() {
		addSequential(new LinearMotionCommand(NONE_DISTANCE_FORWARD));
	}

	// If these really are errors, make sure to update AutoRightCommand too.
	private void loadSwitch(Position1D position) {
		addParallel(new ArmLevelCommand(RobotArmLevel.SWITCH));
		if (position == Position1D.LEFT) {
			addSequential(new LinearMotionCommand(DISTANCE_TO_SWITCH_EDGE + (SWITCH_WIDTH / 2) - (ROBOT_LENGTH / 2)));
			addSequential(new AngularMotionCommand(-(Math.PI / 2)));
			// We should probably move forward a bit again to make sure that we're touching the switch!
			addSequential(new LinearTimedMotionCommand(500));
		} else if (config.shouldOverextend()) {
			addSequential(new LinearMotionCommand(DISTANCE_TO_ALLEY));
			addSequential(new AngularMotionCommand(-(Math.PI / 2)));
			addSequential(new LinearMotionCommand(DISTANCE_DOWN_ALLEY / 2 + 16));
			addSequential(new AngularMotionCommand(-3 * (Math.PI / 4)));
			// addSequential(new LinearMotionCommand(16.854));
		} else {
			loadNone();
			return;
		}
		
		addSequential(new IntakeSpitCommand());
		
		if (config.shouldRunTwoCubeAuto()) {
			if (position == Position1D.LEFT) {

			} else if (config.shouldOverextend()) {

			} else {
				return;
			}
		}
	}

	private void loadScale(Position1D position) {
		addParallel(new ArmLevelCommand(RobotArmLevel.SCALE));
		if (position == Position1D.LEFT) {
			// Distance to scale seems high. Should it not be 139 + 56 + 130 - 38 - 32.5?
			addSequential(new LinearMotionCommand(DISTANCE_TO_SCALE));
			addSequential(new AngularMotionCommand(3 * Math.PI / 4));
		} else if (config.shouldOverextend()) {
			addSequential(new LinearMotionCommand(DISTANCE_TO_ALLEY));
			addSequential(new AngularMotionCommand(-(Math.PI / 2)));
			addSequential(new LinearMotionCommand(DISTANCE_DOWN_ALLEY));
			addSequential(new AngularMotionCommand(0));
			addSequential(new LinearMotionCommand(DISTANCE_TO_SCALE - DISTANCE_TO_ALLEY));
			addSequential(new AngularMotionCommand(-3 * (Math.PI / 4)));
		} else {
			loadNone();
			return;
		}
		addSequential(new IntakeSpitCommand());
		
		if (config.shouldRunTwoCubeAuto()) {
			if (position == Position1D.LEFT) {

			} else if (config.shouldOverextend()) {

			} else {
				return;
			}
		}
	}
}
