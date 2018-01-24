/**
 * Name: ChassisSubsystem
 * Authors: Robert Ciborowski
 * Date: 20/01/2018
 * Description: Personal implementation of FRC's periodic subsystem, mainly adding some PID features
 */

package subsystems;

import org.usfirst.frc.team854.robot.PeriodicSubsystem;
import org.usfirst.frc.team854.robot.constants.RobotTuningConstants;

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
    	motorPIDController.setInputRange(-Math.PI, Math.PI);
    	motorPIDController.setOutputRange(-Math.PI, Math.PI);
    	motorPIDController.setSetpoint(0);
    }
    
    // MIGHT NEED TO CALL THIS!
    public void init() {
    	motorPIDInput.init();
    	setTargetMotion(0, 0);
    }
    
    // MIGHT NEED TO CALL THIS!
    public void reset() {		
    	motorPIDController.reset();
    }
    
    @Override
	public void periodic() {
	}
    
    public void setTargetMotion(double angle, double speed) {
		// If it doesn't drive, this is the likely culprit.
		motorPIDInput.setTargetAngle(angle);
		motorPIDOutput.setTargetSpeed(speed);
		
		if (!motorPIDController.isEnabled()) {
			motorPIDController.enable();
		}
    }
    
    public void setTurningMode(TurningMode turningMode) {
    	motorPIDInput.setTurningMode(turningMode);
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

