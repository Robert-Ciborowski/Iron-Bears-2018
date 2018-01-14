/*
 * Class: GoStraightCommand
 * Author: Christopher Lansdale, Waseef Nayeem and Robert Ciborowski
 * Date: A long time ago (2016)
 * Description: A command for driving straight.
 */

package auto;

import org.usfirst.frc.team854.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GoStraightCommand extends Command {
	// This class needs to be re-implemented or deleted!
	
	double angleSetpoint;

	public GoStraightCommand(double angle) {
		requires(Robot.chassisSubsystem);
		this.angleSetpoint = angle;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		/* double speed = Robot.oi.getSpeed();
		double leftSpeed;
		double rightSpeed;

		SmartDashboard.putNumber("Angle setpoint", angleSetpoint);
		SmartDashboard.putNumber("Angle difference", -Robot.chassisSubsystem.getAngleDifference(angleSetpoint));
		SmartDashboard.putNumber("AnglePIDOutput", GoStraightPID.getOutput());

		double turn = GoStraightPID.getOutput();

		// Reverse the direction of the turn when going backwards
		if (speed < 0) { turn = -turn; }

		// If the speed is zero, then just pivot in place
		// The speed of the turn is set to 1/4 of the full value for all pivots.
		if (Math.abs(speed) < 0.03) {
			leftSpeed  =  turn * 0.25;
			rightSpeed = -turn * 0.25;
		} else {
			
			// If the speed is more than zero, then slow down one side of the robot
			leftSpeed  = (turn < 0) ? speed * (1 + turn) : speed;
			rightSpeed = (turn < 0) ? speed              : speed * (1 - turn);
		}

		Robot.chassisSubsystem.setMotorSpeed(leftSpeed, rightSpeed);*/
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		double turn = Robot.oi.getTurn();
		if (Math.abs(turn) > 0.03) {
			return true;
		}
		return false;

	}

	// Called once after isFinished returns true
	protected void end() {

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
