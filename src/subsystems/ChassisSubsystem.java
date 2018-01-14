package subsystems;

import org.usfirst.frc.team854.robot.PeriodicSubsystem;
import org.usfirst.frc.team854.robot.RobotInterfaceConstants;
import org.usfirst.frc.team854.robot.RobotTuningConstants;

import PID.DriveMotorPIDInput;
import PID.DriveMotorPIDOutput;
import edu.wpi.first.wpilibj.PIDController;
import teleopdrive.JoystickCommand;

/**
 *
 */
public class ChassisSubsystem extends PeriodicSubsystem {
	private DriveMotorPIDInput motorPIDInput = new DriveMotorPIDInput();
	private DriveMotorPIDOutput motorPIDOutput = new DriveMotorPIDOutput();
	private PIDController motorPIDController = new PIDController(
			RobotTuningConstants.DRIVE_PROPORTIONAL,
			RobotTuningConstants.DRIVE_INTEGRAL,
			RobotTuningConstants.DRIVE_DERIVATIVE,
			RobotTuningConstants.DRIVE_FEED_FORWARD,
			motorPIDInput,
			motorPIDOutput);

    public ChassisSubsystem() {
    	motorPIDController.setInputRange(0, 2 * Math.PI);
    	motorPIDController.setOutputRange(0, 2 * Math.PI);
    }
    
    // MIGHT NEED TO CALL THIS!
    public void init() {
    	motorPIDInput.init();
    	setMotorSpeed(0, 0);
    }
    
    // MIGHT NEED TO CALL THIS!
    public void reset() {		
    	motorPIDController.reset();
    }
    
    @Override
	public void periodic() {
	}
    
    public void setMotorSpeed(double angle, double speed) {
		// If it doesn't drive, this is the likely culprit.
		motorPIDController.setSetpoint(angle);
		motorPIDOutput.setSpeed(speed);
		
		if (!motorPIDController.isEnabled()) {
			motorPIDController.enable();
		}
    }

	public void initDefaultCommand() {
		setDefaultCommand(new JoystickCommand());
	}
	
	@Override
	public void updateDashboard() {
		motorPIDInput.updateDashboard();
		motorPIDOutput.updateDashboard();
	}
}

