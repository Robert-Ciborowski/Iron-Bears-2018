package subsystems;

import org.usfirst.frc.team854.robot.M_Subsystem;
import org.usfirst.frc.team854.robot.RobotInterfaceConstants;
import com.ctre.CANTalon;
import org.usfirst.frc.team854.robot.commands.IndexerOff;

public class IndexerSubsystem extends M_Subsystem{
	private CANTalon indexerMotor = new CANTalon(RobotInterfaceConstants.indexerCANMotorPort);
	
	public IndexerSubsystem() {
		indexerMotor.setInverted(RobotInterfaceConstants.indexerMotorInverted);
	}
	
	public void init() {
		
	}
	
	public void setIndexerSpeed(double speed) {
		indexerMotor.set(speed);
	}
	
	public void indexerOff() {
		setIndexerSpeed(0);
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new IndexerOff());
	}
	
	@Override
	public void updateDashboard() {
	}
}
