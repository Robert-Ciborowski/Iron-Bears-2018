/*
 * Class: DistancePID
 * Author: Robert Ciborowski and Rana Rauf
 * Date: 10/02/2018
 * Description: An old class used to control robot driving distance. It is only here for reference.
 */

package org.usfirst.frc.team854.robot.PID;

import org.usfirst.frc.team854.robot.Robot;
import org.usfirst.frc.team854.robot.constants.RobotInterfaceConstants;
import org.usfirst.frc.team854.robot.constants.RobotStructureConstants;
import org.usfirst.frc.team854.robot.hardware.InterfaceType;
import org.usfirst.frc.team854.robot.subsystems.ChassisSubsystem;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class DistancePID extends PIDSubsystem {

	public DistancePID(double p, double i, double d) {
		super(p, i, d);
		// TODO Auto-generated constructor stub
	}
//	private double initialLeftEncoderValue, initialRightEncoderValue;
//	private boolean intialEncoderValueWasTaken;
//	private double targetEncoderValue;
//
//	public DistancePID(double p, double i, double d,
//			double feedForward, double distanceInInches, ChassisSubsystem chassisSubsystem) {
//		super("", p, i, d, feedForward);
//		targetEncoderValue = distanceInInches * RobotStructureConstants.ENCODER_COUNTS_PER_INCH;
//		
//		setSetpoint(1);
//		
//		setAbsoluteTolerance(0.05); 
//		getPIDController().setContinuous(false);
//		
//	}
//	
//	public void setDistance(double distanceInInches) {
//		this.targetEncoderValue = distanceInInches * RobotStructureConstants.ENCODER_COUNTS_PER_INCH;
//		intialEncoderValueWasTaken = false;
//	}
//
//	@Override
//	protected double returnPIDInput() {
//		if (!intialEncoderValueWasTaken) {
//			initialLeftEncoderValue = Robot.devices.getDeviceValue(InterfaceType.DIGITAL, RobotInterfaceConstants.PORT_ENCODER_LEFT);
//			initialRightEncoderValue = Robot.devices.getDeviceValue(InterfaceType.DIGITAL, RobotInterfaceConstants.PORT_ENCODER_RIGHT);
//			intialEncoderValueWasTaken = true;
//			return 0;
//		}
//
//		double currentCountLeft = Robot.devices.getDeviceValue(InterfaceType.DIGITAL, RobotInterfaceConstants.PORT_ENCODER_LEFT) - initialLeftEncoderValue;
//		double currentCountRight = Robot.devices.getDeviceValue(InterfaceType.DIGITAL, RobotInterfaceConstants.PORT_ENCODER_RIGHT) - initialRightEncoderValue;
//
//		double averageCurrentCount = (currentCountLeft + currentCountRight) / 2;
//		
//		double returnValue = averageCurrentCount / targetEncoderValue;
//		System.out.println("Scaled Distance: " + returnValue);
//		return returnValue;
//	}
//
//	@Override
//	protected void usePIDOutput(double output) {
//		Robot.chassisSubsystem.setMotors(output, output);
//	}
//	
//	@Override
//	protected void initDefaultCommand() {
//		// TODO Auto-generated method stub
//	}

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}
