/*
 * Name: AngularMotion
 * Author: Rana Raaiq Rauf, Robert Ciborowski, Shaiza Hashmi
 * Date: 08/02/2018
 * Description: A command for rotating.
 */

package org.usfirst.frc.team854.robot.auto;

import org.usfirst.frc.team854.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AngularMotionCommand extends Command {
	public AngularMotionCommand(double angle) {
		requires(Robot.chassisSubsystem);
		Robot.chassisSubsystem.setTargetMotion(angle, 0);
	}

	public void execute() {
		
	}
	
	@Override
	protected boolean isFinished() {
		
		return Robot.chassisSubsystem.isAngleOnTarget();
	}
}
