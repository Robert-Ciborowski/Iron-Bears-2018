/*
 * Class: RotateFixedCommand
 * Author: Christopher Lansdale, Waseef Nayeem and Robert Ciborowski
 * Date: A long time ago (2016)
 * Description: A command for rotating.
 */

package auto;

import org.usfirst.frc.team854.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RotateFixedCommand extends Command {
	// This class needs to be re-implemented or deleted!
	
	public enum Direction {
		LEFT, RIGHT;
	}

	Direction direction;

	public RotateFixedCommand(Direction direction) {
		this.direction = direction;
	}

	@Override
	protected void end() {
		Robot.chassisSubsystem.setMotorSpeed(0, 0);
	}

	@Override
	protected void execute() {
		/*switch (direction) {
			case LEFT:
				Robot.chassisSubsystem.setMotorSpeed(1, -1);
				break;
			case RIGHT:
				Robot.chassisSubsystem.setMotorSpeed(-1, 1);
				break;
		}*/
	}

	@Override
	protected void initialize() {
		this.setTimeout(0.005);
	}

	@Override
	protected void interrupted() {
	
	}

	@Override
	protected boolean isFinished() {
		return this.isTimedOut();
	}

}
