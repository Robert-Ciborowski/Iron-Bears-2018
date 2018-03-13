/*
 * Name: AngularMotion
 * Author: Rana Raaiq Rauf, Robert Ciborowski, Shaiza Hashmi
 * Date: 08/02/2018
 * Description: A command for rotating.
 */

package org.usfirst.frc.team854.robot.command;

import org.usfirst.frc.team854.robot.Robot;
import org.usfirst.frc.team854.robot.subsystems.TurningMode;

import edu.wpi.first.wpilibj.command.Command;

public class AngularMotionCommand extends Command {
	public double angle;
	
	public AngularMotionCommand(double angle) {
		super("AngularMotionCommand");
		requires(Robot.chassisSubsystem);
		this.angle = angle;
	}
	
	@Override
	public void execute() {
//		Robot.chassisSubsystem.setAutonomousTarget(angle - Robot.chassisSubsystem.getGyroAngle(), 0);
//		System.out.println("New: " + (angle - Robot.chassisSubsystem.getGyroAngle()));
	}

	@Override
	public void initialize() {
		Robot.chassisSubsystem.setTurningMode(TurningMode.ABSOLUTE);
		Robot.chassisSubsystem.setAutonomousTarget(angle, 0);
		Robot.chassisSubsystem.useGyroPIDOnly();
	}
	
	@Override
	protected void end() {
		// Robot.chassisSubsystem.disableAllComponents();
		Robot.chassisSubsystem.disableDistancePIDOnly();
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.chassisSubsystem.isAngleOnTarget();
	}
}