/*
 * Name: AngularMotion
 * Author: Rana Raaiq Rauf, Robert Ciborowski, Shaiza Hashmi
 * Date: 08/02/2018
 * Description: A command for rotating.
 */

package org.usfirst.frc.team854.robot.command;

import org.usfirst.frc.team854.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AngularMotionCommand extends Command {
	public double angle;
	
	public AngularMotionCommand(double angle) {
		requires(Robot.chassisSubsystem);
		this.angle = angle;
	}

	@Override
	public void initialize() {
		Robot.chassisSubsystem.setAutonomousTarget(angle, 0);
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.chassisSubsystem.isAngleOnTarget();
	}
}
