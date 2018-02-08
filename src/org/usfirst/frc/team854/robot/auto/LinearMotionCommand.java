/*
 * Name: AngularMotion
 * Author: Rana Raaiq Rauf, Robert Ciborowski, Shaiza Hashmi
 * Date: 08/02/2018
 * Description: A command for moving along a line.
 */

package org.usfirst.frc.team854.robot.auto;

import org.usfirst.frc.team854.robot.PID.DistancePID;
import org.usfirst.frc.team854.robot.constants.RobotTuningConstants;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class LinearMotionCommand extends Command{

	PIDSubsystem PID;
	private double distanceLeft;
	
	public LinearMotionCommand(double distancePerPulse, double distance) {
		// This initialises and starts the motion.
		PID = new DistancePID(RobotTuningConstants.DISTANCE_PROPORTIONAL, RobotTuningConstants.DISTANCE_INTEGRAL,
				RobotTuningConstants.DISTANCE_DERIVATIVE, RobotTuningConstants.DISTANCE_FEED_FORWARD, distance, distancePerPulse);
		PID.enable();
	}
	
	public void execute() {
		
	}
	
	public void end() {
		PID.free();
	} 
	
	@Override
	protected boolean isFinished() {
		return PID.onTarget();
	}
	
	
}
