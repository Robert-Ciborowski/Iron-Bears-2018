package org.usfirst.frc.team854.robot.command;

import org.usfirst.frc.team854.robot.Robot;
import org.usfirst.frc.team854.robot.subsystems.RobotArmLevel;

import edu.wpi.first.wpilibj.command.Command;

public class ArmLevelCommand extends Command {
	private RobotArmLevel targetLevel;
	
	public ArmLevelCommand(RobotArmLevel level) {
		targetLevel = level;
	}

	@Override
	public void initialize() {
		Robot.armSubsystem.setArmLevel(targetLevel);
	}
	
	@Override
	protected boolean isFinished() {
		if (Robot.armSubsystem.getTargetLevel() == targetLevel) {
			return Robot.armSubsystem.isArmOnTarget();
		} else {
			return true;
		}
	}

}
