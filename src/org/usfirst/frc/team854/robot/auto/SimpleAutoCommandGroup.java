/*
 * Name: TestCommandGroup
 * Author: Rana Raaiq Rauf, Robert Ciborowski, Shaiza Hashmi
 * Date: 08/02/2018
 * Description: A group of autonomous commands for testing auto commands.
 */

package org.usfirst.frc.team854.robot.auto;

import org.usfirst.frc.team854.robot.command.AngularMotionCommand;
import org.usfirst.frc.team854.robot.command.ArmLevelCommand;
import org.usfirst.frc.team854.robot.command.IdioticMotionCommand;
import org.usfirst.frc.team854.robot.command.IntakeSpitCommand;
import org.usfirst.frc.team854.robot.subsystems.RobotArmLevel;

import edu.wpi.first.wpilibj.DriverStation;

public class SimpleAutoCommandGroup extends AutoCommandGroup {
	public SimpleAutoCommandGroup() {
	}
	
	@Override
	public void execute() {
		// System.out.println("GARBAGE!");
	}

	@Override
	public void init() {
		if (config.getPositionForTarget(FieldTarget.LOCAL_SWITCH) == Position1D.LEFT) {
			addParallel(new ArmLevelCommand(RobotArmLevel.SWITCH));
		}
		
		addSequential(new IdioticMotionCommand(1250));
		
		if (DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'L') {
			addSequential(new AngularMotionCommand(-Math.PI / 2));
			addSequential(new IdioticMotionCommand(200));
			addSequential(new IntakeSpitCommand());
		}
	}
}
