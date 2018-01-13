package subsystems;

import org.usfirst.frc.team854.robot.M_Subsystem;
import org.usfirst.frc.team854.robot.RobotInterfaceConstants;
import edu.wpi.first.wpilibj.Spark;
import org.usfirst.frc.team854.robot.commands.IntakeOff;

public class IntakeSubsystem extends M_Subsystem{
	private Spark intakeMotor = new Spark(RobotInterfaceConstants.intakeMotorPort);
	
	public IntakeSubsystem() {
		intakeMotor.setInverted(RobotInterfaceConstants.intakeMotorInverted);
	}
	
	public void init() {
		
	}
	
	public void setIntakeSpeed(double speed) {
		intakeMotor.set(speed);
	}
	
	public void intakeOff() {
		setIntakeSpeed(0);
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new IntakeOff());
	}
	
	@Override
	public void updateDashboard() {
	}
	
}
