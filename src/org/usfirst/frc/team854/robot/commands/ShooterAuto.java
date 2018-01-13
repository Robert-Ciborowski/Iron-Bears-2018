package org.usfirst.frc.team854.robot.commands;

import org.usfirst.frc.team854.robot.Robot;
import org.usfirst.frc.team854.robot.RobotInterfaceConstants;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterAuto extends Command {

    public ShooterAuto() {
    	requires(Robot.shooterSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooterSubsystem.setShooterSpeed(RobotInterfaceConstants.shooterSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooterSubsystem.shooterOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
