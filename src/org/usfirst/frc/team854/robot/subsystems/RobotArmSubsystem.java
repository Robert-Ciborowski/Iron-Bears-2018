/**
 * Name: ChassisSubsystem
 * Authors: Robert Ciborowski
 * Date: 20/01/2018
 * Description: Personal implementation of FRC's periodic subsystem, mainly adding some PID features
 */

package org.usfirst.frc.team854.robot.subsystems;

import org.usfirst.frc.team854.robot.CustomSubsystem;
import org.usfirst.frc.team854.robot.teleopdrive.JoystickCommand;

public class RobotArmSubsystem extends CustomSubsystem {
//	private static DriveMotorPIDInput motorPIDInput = new DriveMotorPIDInput();
//	private DriveMotorPIDOutput motorPIDOutput = new DriveMotorPIDOutput();
//	private PIDController motorPIDController = new PIDController(
//			RobotTuningConstants.DRIVE_PROPORTIONAL,
//			RobotTuningConstants.DRIVE_INTEGRAL,
//			RobotTuningConstants.DRIVE_DERIVATIVE,
//			RobotTuningConstants.DRIVE_FEED_FORWARD,
//			motorPIDInput,
//			motorPIDOutput);
	
    public RobotArmSubsystem() {
//    	motorPIDController.setInputRange(-Math.PI, Math.PI);
//    	motorPIDController.setOutputRange(-Math.PI, Math.PI);
//    	motorPIDController.setSetpoint(0);
//    	
//    	motorPIDInput.init();
    }
    
    // MIGHT NEED TO CALL THIS!
    public void reset() {
//    	motorPIDController.reset();
    }

    public void raiseArmTo(RobotArmState state) {
    	
    }

	public void initDefaultCommand() {
		setDefaultCommand(new JoystickCommand());
	}
	
	@Override
	public void updateDashboard() {
//		motorPIDInput.updateDashboard();
//		motorPIDOutput.updateDashboard();
//		SmartDashboard.putData("Motor Controller", motorPIDController);
	}
}

