package org.usfirst.frc.team854.robot.PID;

import org.usfirst.frc.team854.robot.hardware.Sensors;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class DistanceInputPID implements PIDSource {

	Encoder leftEncoder = Sensors.leftEncoder;
	
	boolean intialCountTaken=false;
	double prevCount;
	
	
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		if(!intialCountTaken) {
			prevCount=leftEncoder.get();
			intialCountTaken=true;
			return 0;
			}
		
		
		
		return 
		}
	

}
