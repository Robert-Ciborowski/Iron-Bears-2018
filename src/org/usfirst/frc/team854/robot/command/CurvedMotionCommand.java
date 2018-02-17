package org.usfirst.frc.team854.robot.command;

import org.usfirst.frc.team854.robot.Robot;

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
		Robot.chassisSubsystem.setAutonomousTarget(angle, distance);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
