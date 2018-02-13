package org.usfirst.frc.team854.robot.PID;

import org.usfirst.frc.team854.robot.hardware.Motors;
import org.usfirst.frc.team854.robot.hardware.Sensors;
import org.usfirst.frc.team854.robot.subsystems.ChassisSubsystem;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class DistancePID extends PIDSubsystem {
	
	private double initialCount,
				   pulsesPerInch,
				   targetInPulses;
	private boolean initialCountTaken = false;
	private ChassisSubsystem chassisSubsystem;

	public DistancePID(double p, double i, double d,
			double feedForward, double distanceInInches, double pulsesPerInch, ChassisSubsystem chassisSubsystem) {
		super("", p, i, d, feedForward);
		this.pulsesPerInch = pulsesPerInch;
		this.targetInPulses = distanceInInches * pulsesPerInch;
		this.chassisSubsystem = chassisSubsystem;
		setSetpoint(1);
		System.out.println("Distance in Inches: " + distanceInInches + ", Pulses Per Inch: " + pulsesPerInch);
		
		setAbsoluteTolerance(0.05); 
		getPIDController().setContinuous(false);
		
	}
	
	public void setDistance(double distanceInInches) {
		this.targetInPulses = distanceInInches * pulsesPerInch;
		initialCountTaken = false;
	}

	@Override
	protected double returnPIDInput() {
		if (targetInPulses == 0) {
			return 0;
		}
		if (!initialCountTaken) {
			initialCount = Sensors.leftEncoder.get();
			initialCountTaken = true;
			return 0;
		}
		double returnValue = (Sensors.leftEncoder.get() - initialCount) / targetInPulses;
		System.out.println("Here is the PIDInput's return: " + returnValue);
		System.out.println("Tagret in Pulses: " + targetInPulses + ", Left Encoder: " + Sensors.leftEncoder.get() + ", Initial Count: " + initialCount);
		return returnValue;
		
		
	}

	@Override
	protected void usePIDOutput(double output) {
		chassisSubsystem.setMotors(output, output);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}
