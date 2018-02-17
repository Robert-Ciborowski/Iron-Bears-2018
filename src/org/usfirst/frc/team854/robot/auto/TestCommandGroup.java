/*
 * Name: TestCommandGroup
 * Author: Rana Raaiq Rauf, Robert Ciborowski, Shaiza Hashmi
 * Date: 08/02/2018
 * Description: A group of autonomous commands for testing auto commands.
 */

package org.usfirst.frc.team854.robot.auto;

import org.usfirst.frc.team854.robot.command.AngularMotionCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestCommandGroup extends CommandGroup {
	public TestCommandGroup(double angle) {
		
		addSequential(new AngularMotionCommand(angle));
		// addSequential(new LinearMotionCommand(distance, chassisSubsystem));
		
		// Scheduler.getInstance().add(this);
	}
}
