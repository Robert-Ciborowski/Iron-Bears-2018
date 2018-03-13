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
		System.out.println("Lineari.");
		// Robot.chassisSubsystem.resetTargetAngle();
		angle = Robot.chassisSubsystem.getTargetAngle();
		Robot.chassisSubsystem.setAutonomousTarget(angle, distance);
		Robot.chassisSubsystem.enableAllPIDs();
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.chassisSubsystem.isAutonomousOnTarget();
	}
	
	@Override
	protected void execute() {
		System.out.println("Linear.");
	}
	
	@Override
	protected void end() {
		System.out.println("lend");
		Robot.chassisSubsystem.disableAllComponents();
	}
}
