/*
 * Class: AutoGoStraightCommand
 * Author: Christopher Lansdale, Waseef Nayeem and Robert Ciborowski
 * Date: A long time ago (2016)
 * Description: A class for the moving straight autonomous command.
 */

package auto;

import org.usfirst.frc.team854.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public abstract class AutoGoStraightCommand extends Command {
	public enum Direction {
		FORWARD, BACKWARD;
	}

	private double angleSetpoint;
	private double speedSetpoint;

	public AutoGoStraightCommand(double angle) {
		requires(Robot.chassisSubsystem);
		this.angleSetpoint = angle;
	}

	public void setSpeed(double speed, Direction direction) {
		System.out.println("Speed set to: " + speed);
		double absoluteSpeed = Math.abs(speed);
		this.speedSetpoint = (direction == Direction.FORWARD) ? absoluteSpeed : -absoluteSpeed;
	}

	// This is called just before this Command runs the first time.
	protected void initialize() {

	}

	// This is called repeatedly when this Command is scheduled to run.
	protected void execute() {
//		double speed = speedSetpoint;
//		double leftSpeed;
//		double rightSpeed;
//		
//		SmartDashboard.putNumber("Angle setpoint",  angleSetpoint);
//		SmartDashboard.putNumber("AnglePIDOutput", GoStraightPID.getOutput());
//		
//		double turn = GoStraightPID.getOutput();
//		
//		if (speed < 0) {
//			turn = -turn;
//		}
//		
//		if (Math.abs(speed) < 0.03) {
//			leftSpeed = turn / 4.0;
//			rightSpeed = -turn / 4.0;
//		} else {
//			leftSpeed = (turn < 0) ? speed * (1 + turn) : speed;
//			rightSpeed = (turn < 0) ? speed : speed * (1 - turn);
//		}
//		
//		Robot.chassisSubsystem.setMotorSpeed(leftSpeed,rightSpeed);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	public void end() {
		Robot.chassisSubsystem.setMotorSpeed(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}

}
