/*
 * Class: DistancePIDInput
 * Author: Robert Ciborowski, Julian Dominguez-Schatz and Rana Rauf
 * Date: 15/02/2018
 * Description: A class for providing input to the distance PID controller.
 */

package org.usfirst.frc.team854.robot.PID;

import org.usfirst.frc.team854.robot.Robot;
import org.usfirst.frc.team854.robot.constants.RobotInterfaceConstants;
import org.usfirst.frc.team854.robot.constants.RobotStructureConstants;
import org.usfirst.frc.team854.robot.hardware.InterfaceType;
import org.usfirst.frc.team854.robot.subsystems.ChassisSubsystem;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class ArmPIDInput implements PIDSource {
	private Encoder armEncoder = Robot.devices.getDevice(InterfaceType.DIGITAL, RobotInterfaceConstants.PORT_ENCODER_ARM);
	
	public ArmPIDInput() {
	}
	
	// Do nothing with this method as it is not used.
	public void setPIDSourceType(PIDSourceType pidSource) {
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		return armEncoder.get();
	}
}
