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
		// This initialises and starts the motion.
		this.distance = distance;
		System.out.println("Constructed the Linear Command.");
	}
	
	@Override
	public void initialize() {
		Robot.chassisSubsystem.setAutonomousTarget(0, distance);
	}
	
	public void execute() {
		
	}
	
	@Override
	public void end() {
		Robot.chassisSubsystem.endAutonomousCommand();
	} 
	
	@Override
	protected boolean isFinished() {
		return Robot.chassisSubsystem.isAutonomousOnTarget();
	}
}
