/*
 * Name: TestCommandGroup
 * Author: Rana Raaiq Rauf, Robert Ciborowski, Shaiza Hashmi
 * Date: 08/02/2018
 * Description: A group of autonomous commands for testing auto commands.
 */

package org.usfirst.frc.team854.robot.auto;

import org.usfirst.frc.team854.robot.constants.RobotStructureConstants;
import org.usfirst.frc.team854.robot.subsystems.ChassisSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;

public class TestCommandGroup extends CommandGroup {
	public TestCommandGroup(double distance, ChassisSubsystem chassisSubsystem) {
		addSequential(new LinearMotionCommand(distance, chassisSubsystem));
		//Scheduler.getInstance().add(this);
	}
}
