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
		System.out.println("Constructed.");
	}
	
	@Override
	public void execute() {
		// System.out.println("Angular.");
//		Robot.chassisSubsystem.setAutonomousTarget(angle - Robot.chassisSubsystem.getGyroAngle(), 0);
//		System.out.println("New: " + (angle - Robot.chassisSubsystem.getGyroAngle()));
	}

	@Override
	public void initialize() {
		Robot.chassisSubsystem.setTurningMode(TurningMode.ABSOLUTE);
		Robot.chassisSubsystem.setAutonomousTarget(angle, 0);
		Robot.chassisSubsystem.enableAllPIDs();
		System.out.println("Inited.");
	}
	
	@Override
	protected void end() {
		Robot.chassisSubsystem.disableAllComponents();
	}
	
	@Override
	protected boolean isFinished() {
		if (Robot.chassisSubsystem.isAngleOnTarget()) {
			System.out.println("ANGLE IS ON TARGET!");
		}
		return Robot.chassisSubsystem.isAngleOnTarget();
	}
}