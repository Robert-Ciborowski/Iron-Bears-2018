/**
 * Name: ChassisSubsystem
 * Authors: Robert Ciborowski
 * Date: 20/01/2018
 * Description: Personal implementation of FRC's periodic subsystem, mainly adding some PID features
 */

package org.usfirst.frc.team854.robot.subsystems;

import org.usfirst.frc.team854.robot.CustomSubsystem;
import org.usfirst.frc.team854.robot.Robot;
import org.usfirst.frc.team854.robot.constants.RobotInterfaceConstants;
import org.usfirst.frc.team854.robot.constants.RobotTuningConstants;
import org.usfirst.frc.team854.robot.hardware.Motors;
import org.usfirst.frc.team854.robot.hardware.SensorProvider.SensorType;
import org.usfirst.frc.team854.robot.teleopdrive.JoystickCommand;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;

public class RobotArmSubsystem extends CustomSubsystem {
	private PIDController armController = new PIDController(
			RobotTuningConstants.ARM_PROPORTIONAL,
			RobotTuningConstants.ARM_INTEGRAL,
			RobotTuningConstants.ARM_DERIVATIVE,
			Robot.sensors.getSensor(SensorType.DIGITAL, RobotInterfaceConstants.PORT_ENCODER_ARM),
			Motors.armMotor);

    public RobotArmSubsystem() {
    	armController.setSetpoint(0);
    	Robot.sensors.<Encoder>getSensor(SensorType.DIGITAL, RobotInterfaceConstants.PORT_ENCODER_ARM).reset();
    }

    public void reset() {
    	armController.reset();
    }

    public void setArmLevel(RobotArmLevel level) {
    	armController.setSetpoint(level.getSetpoint());
    }

	public void initDefaultCommand() {
		setDefaultCommand(new JoystickCommand());
	}
	
	@Override
	public void updateDashboard() {
	}
}

