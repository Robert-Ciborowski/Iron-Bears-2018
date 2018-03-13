/*
 * Name: TestCommandGroup
 * Author: Rana Raaiq Rauf, Robert Ciborowski, Shaiza Hashmi
 * Date: 08/02/2018
 * Description: A group of autonomous commands for testing auto commands.
 */

package org.usfirst.frc.team854.robot.auto;

import org.usfirst.frc.team854.robot.command.AngularMotionCommand;
import org.usfirst.frc.team854.robot.command.CurvedMotionCommand;
import org.usfirst.frc.team854.robot.command.LinearMotionCommand;

public class TestCommandGroup extends AutoCommandGroup {
	public TestCommandGroup() {
//		addSequential(new LinearMotionCommand(5));
//		addSequential(new AngularMotionCommand(Math.PI / 2));
//		addSequential(new LinearMotionCommand(5));
//		addSequential(new AngularMotionCommand(Math.PI));
//		addSequential(new LinearMotionCommand(5));
//		addSequential(new AngularMotionCommand(Math.PI * 3 / 2));
//		addSequential(new LinearMotionCommand(5));
//		addSequential(new AngularMotionCommand(Math.PI * 2));
		
//		addSequential(new LinearMotionCommand(5));
//		addSequential(new LinearMotionCommand(-5));
//		addSequential(new LinearMotionCommand(5));
//		addSequential(new AngularMotionCommand(Math.PI / 2));
		
		// addSequential(new CurvedMotionCommand(Math.PI, 5));
		
//		addSequential(new AngularMotionCommand(Math.PI / 2));
//		addSequential(new AngularMotionCommand(0));
		
//		addSequential(new LinearMotionCommand(5));
//		addSequential(new AngularMotionCommand(Math.PI / 2));
		// addSequential(new CurvedMotionCommand(Math.PI / 2, 15));
		addSequential(new AngularMotionCommand(-Math.PI));
		// addSequential(new AngularMotionCommand(Math.PI));
	}

	@Override
	public void init(String fieldState, AutoTarget target) {
	}
	
	@Override
	public void execute() {
		// System.out.println("GARBAGE!");
	}
}
