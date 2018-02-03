package PID;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DistanceInputPID implements PIDSource {
	
	Encoder leftencoder, rightencoder;
	private double DistanceperPulse,DistanceCovered=0,DistanceLeft=0;
	private double DesiredDistance;
	
	
	
	DistanceInputPID(int[] leftencoder,int[] rightencoder,boolean leftreverse,boolean rightreverse, double DistanceperPulse, double DesiredDistance ){
		this.leftencoder= new Encoder(leftencoder[0],leftencoder[1],leftreverse);
		this.rightencoder=new Encoder(rightencoder[0],rightencoder[1],rightreverse);
		
		this.rightencoder.setDistancePerPulse(DistanceperPulse);
		this.leftencoder.setDistancePerPulse(DistanceperPulse);
		
		this.DesiredDistance=DesiredDistance;
		
	}

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
		DistanceCovered();
		
		DistanceLeft=DesiredDistance-DistanceCovered;
		
		return DistanceLeft;
	}
	
	private void DistanceCovered() {
		    DistanceCovered+= (leftencoder.getRaw()+rightencoder.getRaw())/2*DistanceperPulse;
		}
	
	
	void ResetEncoders() {
	     rightencoder.reset();
	     leftencoder.reset();
	}
 
	void setDistanceperPulse(double distanceperpulse) {
		DistanceperPulse= distanceperpulse;
	}
	
	public void updateDashboard() {
		SmartDashboard.putNumber("Distance left", DistanceCovered);
	}
	
	
}
