/*
 * Name: CurvedMotionCommand
 * Author: Robert Ciborowski
 * Date: 17/02/2018
 * Description: A command for moving the robot along an arc.
 */

package org.usfirst.frc.team854.robot.command;

import org.usfirst.frc.team854.robot.Robot;
import org.usfirst.frc.team854.robot.subsystems.TurningMode;

import edu.wpi.first.wpilibj.command.Command;

public class CurvedMotionCommand extends Command {
	private double angle, distance;
	
	public CurvedMotionCommand(double angle, double distance) {
		requires(Robot.chassisSubsystem);
		this.angle = angle;
		this.distance = distance;
	}
	
	@Override
	public void initialize() {
		Robot.chassisSubsystem.setTurningMode(TurningMode.ABSOLUTE);
		Robot.chassisSubsystem.setAutonomousTarget(angle, distance);
		Robot.chassisSubsystem.enableAllPIDs();
	}
	
	@Override
	protected void end() {
		Robot.chassisSubsystem.disableAllComponents();
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.chassisSubsystem.isAutonomousOnTarget();
	}
}
