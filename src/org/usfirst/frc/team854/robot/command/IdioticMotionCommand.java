/*
 * Name: AngularMotion
 * Author: Rana Raaiq Rauf, Robert Ciborowski, Shaiza Hashmi
 * Date: 08/02/2018
 * Description: A command for moving along a line.
 */

package org.usfirst.frc.team854.robot.command;

import org.usfirst.frc.team854.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class IdioticMotionCommand extends Command {
	private double angle, time;
	
	public IdioticMotionCommand(double time) {
		requires(Robot.chassisSubsystem);

		this.time = time;
	}
	
	double cTime = 0, sTime = 0;
	
	@Override
	public void initialize() {
		System.out.println("Lineari.");
		// Robot.chassisSubsystem.resetTargetAngle();
		angle = Robot.chassisSubsystem.getTargetAngle();
		Robot.chassisSubsystem.setAutonomousTarget(angle, 999999);
		Robot.chassisSubsystem.enableAllPIDs();
		sTime = System.currentTimeMillis();
	}
	
	@Override
	protected boolean isFinished() {
//		return Robot.chassisSubsystem.isAutonomousOnTarget();
		return cTime > time;
	}
	
	@Override
	protected void execute() {
		cTime = System.currentTimeMillis() - sTime;
	}
	
	@Override
	protected void end() {
		Robot.chassisSubsystem.disableAllComponents();
	}
}
