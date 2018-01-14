/*
 * Class: DriveToDistanceTimeOutNoDistance
 * Author: Christopher Lansdale, Waseef Nayeem and Robert Ciborowski
 * Date: A long time ago (2016)
 * Description: A command for driving a certain distance.
 */

package auto;

import org.usfirst.frc.team854.robot.Robot;

/**
 * Drives to a specified distance using encoder counts.
 */
public class DriveToDistanceTimeOutNoDistance extends AutoGoStraightCommand {
	// This class needs to be re-implemented or deleted!
	
	/**
	 * The distance to drive to.
	 */
	private double distanceSetpoint;

	private double speedSetpoint;

	/**
	 * The constructor for a new DriveToDistance command.
	 * 
	 * @param speed
	 *            The speed at which to drive.
	 * @param angle
	 *            The angle to drive at (in degrees).
	 * @param distance
	 *            The distance to drive to.
	 */
	public DriveToDistanceTimeOutNoDistance(double speed, double angle, double distance, double timeOut) {
		super(angle);
		this.setTimeout(timeOut);
		this.speedSetpoint = speed;
		this.distanceSetpoint = distance;
		System.out.println("DriveToDistance called constructor");
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.chassisSubsystem.reset();
		super.initialize();

		// Chad.
		if (distanceSetpoint < 0) {
			setSpeed(speedSetpoint, Direction.BACKWARD);
		} else {
			setSpeed(speedSetpoint, Direction.FORWARD);
		}
		System.out.println("Initialized");
	}
	
	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		System.out.println("Execute started");
		super.execute();
		System.out.println("Execute ended");
	}

	/**
	 * Gets the distance set point.
	 * 
	 * @return the distance set point.
	 */
	public double getDistance() {
		return distanceSetpoint;
	}

	// Called once after isFinished returns true
	@Override
	protected boolean isFinished() {
		return this.isTimedOut();
	}
}
