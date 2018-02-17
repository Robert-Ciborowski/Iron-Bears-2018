/*
 * Name: AngularMotion
 * Author: Rana Raaiq Rauf, Robert Ciborowski, Shaiza Hashmi
 * Date: 08/02/2018
 * Description: A command for moving along a line.
 */

package org.usfirst.frc.team854.robot.command;

import org.usfirst.frc.team854.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LinearMotionCommand extends Command {
	private double distance;
	
	public LinearMotionCommand(double distance) {
		requires(Robot.chassisSubsystem);

		// This initialises and starts the motion.
		this.distance = distance;
	}
	
	@Override
	public void initialize() {
		Robot.chassisSubsystem.setAutonomousTarget(0, distance);
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.chassisSubsystem.isAutonomousOnTarget();
	}
}
