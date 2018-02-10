/*
 * Name: AngularMotion
 * Author: Rana Raaiq Rauf, Robert Ciborowski, Shaiza Hashmi
 * Date: 08/02/2018
 * Description: A command for moving along a line.
 */

package org.usfirst.frc.team854.robot.auto;

import org.usfirst.frc.team854.robot.PID.DistancePID;
import org.usfirst.frc.team854.robot.constants.RobotTuningConstants;
import org.usfirst.frc.team854.robot.subsystems.ChassisSubsystem;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class LinearMotionCommand extends Command {
	private ChassisSubsystem chassisSubsystem;
	private double distanceLeft;
	
	public LinearMotionCommand(double distance, ChassisSubsystem chassisSubsystem) {
		// This initialises and starts the motion.
		this.chassisSubsystem = chassisSubsystem;
		chassisSubsystem.setAutonomousTarget(0, distance);
		System.out.println("Constructed the Linear Command.");
	}
	
	public void execute() {
		
	}
	
	@Override
	public void end() {
		chassisSubsystem.endAutonomousCommand();
	} 
	
	@Override
	protected boolean isFinished() {
		return chassisSubsystem.isAutonomousOnTarget();
	}
}
