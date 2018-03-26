/*
 * Name: TestCommandGroup
 * Author: Rana Raaiq Rauf, Robert Ciborowski, Shaiza Hashmi
 * Date: 08/02/2018
 * Description: A group of autonomous commands for testing auto commands.
 */

package org.usfirst.frc.team854.robot.auto;

import org.usfirst.frc.team854.robot.Robot;
import org.usfirst.frc.team854.robot.command.AngularMotionCommand;
import org.usfirst.frc.team854.robot.command.ArmLevelCommand;
import org.usfirst.frc.team854.robot.command.LinearTimedMotionCommand;
import org.usfirst.frc.team854.robot.command.IntakeSpitCommand;
import org.usfirst.frc.team854.robot.command.LinearMotionCommand;
import org.usfirst.frc.team854.robot.subsystems.RobotArmLevel;

import edu.wpi.first.wpilibj.DriverStation;

public class SimpleAutoCommandGroup extends AutoCommandGroup {
	public SimpleAutoCommandGroup() {
	}
	
	@Override
	public void execute() {
		System.out.println("GARBAGE!");
	}

	@Override
	public void init() {
		Robot.intakeSubsystem.setPneumaticsExtended(false);
		
//		System.out.println("INITED");
//		if (DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'L') {
//			addParallel(new ArmLevelCommand(RobotArmLevel.SWITCH));
//		}
		
		addSequential(new LinearTimedMotionCommand(3000));
		
//		if (DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'L') {
////			addSequential(new AngularMotionCommand(-Math.PI / 2));
////			addSequential(new LinearTimedMotionCommand(200));
//			addSequential(new IntakeSpitCommand());
//			System.out.println("SPIT!!!!!!!!!!!!!");
//		}
	}
}
