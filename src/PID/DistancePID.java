package PID;

import org.usfirst.frc.team854.robot.hardware.Sensors;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class DistancePID extends PIDSubsystem{
	
	private double prevCount,
				   DistancePerPulse,
				   Setpoint,
				   DistanceCovered;
	private boolean intialCountTaken=false;
	
	
	
	
	public DistancePID(double p, double i, double d, double Setpoint, double DistancePerPulse) {
		super(p, i, d);
		this.DistancePerPulse=DistancePerPulse;
		this.Setpoint=Setpoint;
		setSetpoint(Setpoint);
		
		
	}



	@Override
	protected double returnPIDInput() {
		if (!intialCountTaken) {
			prevCount=(Sensors.leftEncoder.get()+Sensors.rightEncoder.get())/2;
			intialCountTaken=true;
		return 0;}
		
		
		double currentCount= (Sensors.leftEncoder.get()+Sensors.rightEncoder.get())/2;
		DistanceCovered= (currentCount-prevCount)*DistancePerPulse;
		prevCount=currentCount;
		
		return DistanceCovered/Math.abs(Setpoint) ;
		
		
	}



	@Override
	protected void usePIDOutput(double output) {
		
		
	}



	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}


}
