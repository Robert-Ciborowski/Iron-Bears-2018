package subsystems;

import org.usfirst.frc.team854.robot.PeriodicSubsystem;
import org.usfirst.frc.team854.robot.RobotInterfaceConstants;
import org.usfirst.frc.team854.robot.commands.ShooterOff;
import com.ctre.CANTalon;

import PID.M_PIDController;
import PID.M_PIDInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class EncodedShooterSubsystem extends PeriodicSubsystem {
	
	private CANTalon shooterMotor1 = new CANTalon(RobotInterfaceConstants.shooterCANMotorPort);
	private Victor shooterMotor2 = new Victor(RobotInterfaceConstants.shooterVictorMotorPort);
	private Encoder shooterEncoder = new Encoder(RobotInterfaceConstants.shooterMotorEncoder1, RobotInterfaceConstants.shooterMotorEncoder2);
	
	M_PIDInput shooterPIDInput1 = new M_PIDInput() {
		@Override
		public double pidGet() {
			return -shooterEncoder.getRate() / RobotInterfaceConstants.shooterEncoderMaxRate;
		}
	};
	
	M_PIDInput shooterPIDInput2 = new M_PIDInput() {
		@Override
		public double pidGet() {
			return -shooterEncoder.getRate() / RobotInterfaceConstants.shooterEncoderMaxRate;
		}
	};
	
	M_PIDController shooterMotor1PID = new M_PIDController(0.05, 0, 0, 1.0, shooterPIDInput1, shooterMotor1);
	M_PIDController shooterMotor2PID = new M_PIDController(0.05, 0, 0, 1.0, shooterPIDInput2, shooterMotor2);
	
	

	// Motor inversions MUST be declared in the constructor!!!
    public EncodedShooterSubsystem() {
    	shooterMotor1.setInverted(RobotInterfaceConstants.shooterMotorInverted);
    	shooterMotor2.setInverted(RobotInterfaceConstants.shooterMotor2Inverted);
    }
    
  //MIGHT NEED TO CALL THIS!
    public void init() {
    }
    
    @Override
	public void periodic() {
    	// This will calculate the PIDs!
    	shooterMotor1PID.calculate();
    	shooterMotor2PID.calculate();
	}
    
    public void setShooterSpeed(double speed) {
    	if (!shooterMotor1PID.isEnabled()) {
    		shooterMotor1PID.enable();
		}
		if (!shooterMotor2PID.isEnabled()) {
			shooterMotor2PID.enable();
		}
    	
    	shooterMotor1.set(speed);
    	shooterMotor2.set(speed);
    }
    
    public void shooterOff() {
    	setShooterSpeed(0);
    }

	public void initDefaultCommand() {
	    setDefaultCommand(new ShooterOff());
	}
	
	public void resetEncoders() {
		this.shooterEncoder.reset();
	}
	
	@Override
	public void updateDashboard() {
		SmartDashboard.putData("Shooter encoder", shooterEncoder);
	}
}
