/** 
 * Class: Motion
 * Author: Rana, Lucas, Cole, Robert Ciborowski, Danny Xu
 * Date: 03/02/2018
 * Description: A class for moving the robot in a linear motion.
 */

package org.usfirst.frc.team854.robot.auto;

public class LinearMotionCommand extends Motion {

	private double distanceRemaining;
	
	public LinearMotionCommand(double distance) {
		super(distance, 0);
	}

	
	private void setSpeed() {
		
	}
	
	@Override
	protected void execute() {
		
	}	
	
	@Override
	protected boolean isFinished() {
		return false;
	}
}
