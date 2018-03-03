/*
 * Name: TestCommandGroup
 * Author: Rana Raaiq Rauf, Robert Ciborowski, Shaiza Hashmi
 * Date: 08/02/2018
 * Description: A group of autonomous commands for testing auto commands.
 */

package org.usfirst.frc.team854.robot.auto;

import org.usfirst.frc.team854.robot.command.AngularMotionCommand;
import org.usfirst.frc.team854.robot.command.LinearMotionCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;

public class TestCommandGroup extends AutoCommandGroup {
	public TestCommandGroup() {
		addSequential(new LinearMotionCommand(5));
		addSequential(new AngularMotionCommand(Math.PI / 2));
		addSequential(new LinearMotionCommand(5));
		addSequential(new AngularMotionCommand(Math.PI / 2));
		addSequential(new LinearMotionCommand(5));
		addSequential(new AngularMotionCommand(Math.PI / 2));
		addSequential(new LinearMotionCommand(5));
		addSequential(new AngularMotionCommand(Math.PI / 2));
	}

	@Override
	public void init(String fieldState, AutoTarget target) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void execute() {
		// System.out.println("GARBAGE!");
	}
}
