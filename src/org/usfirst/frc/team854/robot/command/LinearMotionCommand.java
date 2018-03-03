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
	private double distance, angle;
	
	public LinearMotionCommand(double distance) {
		requires(Robot.chassisSubsystem);

		// This initialises and starts the motion.
		this.distance = distance;
	}
	
	@Override
	public void initialize() {
		angle = Robot.chassisSubsystem.getTargetAngle();
		Robot.chassisSubsystem.setAutonomousTarget(angle, distance);
		System.out.println(angle + ", " + distance);
		Robot.chassisSubsystem.enableAllPIDs();
	}
	
	@Override
	protected boolean isFinished() {
		if (Robot.chassisSubsystem.isAutonomousOnTarget()) {
			System.out.println("DONE LINEAR!");
		}
		return Robot.chassisSubsystem.isAutonomousOnTarget();
	}
	
	// We were hear. The robot was changing its angle!
	@Override
	protected void execute() {
		// System.out.println("Linear.");
	}
	
	@Override
	protected void end() {
		System.out.println("ENDED LINEAR!");
		Robot.chassisSubsystem.disableAllComponents();
	}
}
