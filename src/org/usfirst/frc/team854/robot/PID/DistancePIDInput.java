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

public class DistancePIDInput implements PIDSource {
	private double initialLeftEncoderValue, initialRightEncoderValue;
	private boolean intialEncoderValueWasTaken;
	private double targetEncoderValue;
	
	public DistancePIDInput(double distanceInInches) {
		setDistance(distanceInInches);
	}
	
	// Do nothing with this method as it is not used.
	public void setPIDSourceType(PIDSourceType pidSource) {
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}
	
	public void setDistance(double distanceInInches) {
		this.targetEncoderValue = distanceInInches * RobotStructureConstants.ENCODER_COUNTS_PER_INCH;
		intialEncoderValueWasTaken = false;
	}

	@Override
	public double pidGet() {
		if (targetEncoderValue == 0) {
			return 1;
		}
		
		if (!intialEncoderValueWasTaken) {
			initialLeftEncoderValue = Robot.devices.getDeviceValue(InterfaceType.DIGITAL, RobotInterfaceConstants.PORT_ENCODER_LEFT);
			initialRightEncoderValue = Robot.devices.getDeviceValue(InterfaceType.DIGITAL, RobotInterfaceConstants.PORT_ENCODER_RIGHT);
			intialEncoderValueWasTaken = true;
			return 0;
		}

		double currentCountLeft = Robot.devices.getDeviceValue(InterfaceType.DIGITAL, RobotInterfaceConstants.PORT_ENCODER_LEFT) - initialLeftEncoderValue;
		double currentCountRight = Robot.devices.getDeviceValue(InterfaceType.DIGITAL, RobotInterfaceConstants.PORT_ENCODER_RIGHT) - initialRightEncoderValue;

		double averageCurrentCount = (currentCountLeft + currentCountRight) / 2;
		
		double returnValue = averageCurrentCount / targetEncoderValue;
		System.out.println("Target: " + targetEncoderValue + ", Average: " + averageCurrentCount);
		return returnValue;
	}
}
