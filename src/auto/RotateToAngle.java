package auto;

import org.usfirst.frc.team854.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RotateToAngle extends Command {

	private double targetAngle;
	private double leftSpeed;
	private double rightSpeed;
	private double initSpeed = AutoVariables.rotateToAngleSpeed;

	public RotateToAngle(double targetAngle, double time) {
		requires(Robot.chassisSubsystem);
		this.targetAngle = targetAngle;
		this.setTimeout(time);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// Which way am I going to turn? Need to be able to get angle from
		// drive

		double angleDifference = Robot.chassisSubsystem.getAngleDifference(this.targetAngle);

		rightSpeed = (angleDifference > 0) ? initSpeed : -initSpeed;
		leftSpeed = -rightSpeed;

		Robot.chassisSubsystem.setMotorSpeed(leftSpeed, rightSpeed);
		System.out.println("Wait finished");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// Reduce the speed as you are getting close to the target.

		double angleDifference = Robot.chassisSubsystem.getAngleDifference(this.targetAngle);

		double speed = initSpeed;
		
		if (Math.abs(angleDifference) < 20) {
			speed *= .5;
		} else if (Math.abs(angleDifference) < 10) {
			speed *= .3;
		} else if (Math.abs(angleDifference) < 5) {
			speed *= .3;
		}

		rightSpeed = (angleDifference > 0) ? speed : -speed;

		leftSpeed = -rightSpeed;

		Robot.chassisSubsystem.setMotorSpeed(leftSpeed, rightSpeed);

		SmartDashboard.putNumber("Angle difference", angleDifference);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
	    System.out.println("rotate finished");
		double angleDifference = Robot.chassisSubsystem.getAngleDifference(this.targetAngle);

		if((-0.03 > Robot.oi.getSpeed())||(Robot.oi.getSpeed() > 0.03)){
			return true;
		}
		if((-0.03 > Robot.oi.getTurn())||(Robot.oi.getTurn() > 0.03)) {
			return true;
		}
		
		if (Math.abs(angleDifference) < 3) {
			return true;
		} else {
			return this.isTimedOut();
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		// stop motors
		Robot.chassisSubsystem.setMotorSpeed(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {

	}
}
