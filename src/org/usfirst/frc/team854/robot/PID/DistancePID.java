package org.usfirst.frc.team854.robot.PID;

import org.usfirst.frc.team854.robot.hardware.Motors;
import org.usfirst.frc.team854.robot.hardware.Sensors;
import org.usfirst.frc.team854.robot.subsystems.ChassisSubsystem;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class DistancePID extends PIDSubsystem {
	
	private double prevCountleft,prevCountRight,
				   distancePerPulse,
				   setpoint,
				   DistanceCoveredLeft, distanceCoveredRight;
	private boolean intialCountTaken = false;
	private ChassisSubsystem chassisSubsystem;

	public DistancePID(double p, double i, double d,
			double feedForward, double distance, double distancePerPulse, ChassisSubsystem chassisSubsystem) {
		super(p, i, d, feedForward);
		this.distancePerPulse = distancePerPulse;
		this.setpoint = distance;
		this.chassisSubsystem = chassisSubsystem;
		setSetpoint(setpoint);
		
		setAbsoluteTolerance(0.05); 
		getPIDController().setContinuous(false);
		
	}

	@Override
	protected double returnPIDInput() {
		if (!intialCountTaken) {
			prevCountleft=Sensors.leftEncoder.get();
			prevCountRight=Sensors.rightEncoder.get();
			
			intialCountTaken=true;
		return 0;
		}

		double currentCountLeft = Sensors.leftEncoder.get();
		double currentCountRight = Sensors.rightEncoder.get();
		DistanceCoveredLeft += (currentCountLeft - prevCountleft) * distancePerPulse;
		prevCountleft = currentCountLeft;
		
		distanceCoveredRight += (currentCountRight - prevCountRight) * distancePerPulse;
		System.out.println(currentCountLeft + " is the current count left.");
		
		if (setpoint != 0) {
			double scaledDistance = DistanceCoveredLeft / Math.abs(setpoint);
			System.out.println("Scaled Distance: " + scaledDistance + ", Setpoint: " + Math.abs(setpoint));
			return scaledDistance;
		} else {
			return 0;
		}
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
