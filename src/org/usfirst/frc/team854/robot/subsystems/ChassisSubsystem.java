/**
 * Name: ChassisSubsystem
 * Authors: Robert Ciborowski
 * Date: 20/01/2018
 * Description: Personal implementation of FRC's periodic subsystem, mainly adding some PID features
 */

package org.usfirst.frc.team854.robot.subsystems;

import org.usfirst.frc.team854.robot.PeriodicSubsystem;
import org.usfirst.frc.team854.robot.RobotMode;
import org.usfirst.frc.team854.robot.PID.TeleoperatedPIDInput;
import org.usfirst.frc.team854.robot.PID.TeleoperatedPIDOutput;
import org.usfirst.frc.team854.robot.PID.DistancePID;
import org.usfirst.frc.team854.robot.constants.RobotStructureConstants;
import org.usfirst.frc.team854.robot.constants.RobotTuningConstants;
import org.usfirst.frc.team854.robot.constants.UserInterfaceConstants;
import org.usfirst.frc.team854.robot.hardware.Motors;
import org.usfirst.frc.team854.robot.teleopdrive.JoystickCommand;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ChassisSubsystem extends PeriodicSubsystem {
	private TeleoperatedPIDInput motorPIDInput = new TeleoperatedPIDInput();
	private TeleoperatedPIDOutput motorPIDOutput = new TeleoperatedPIDOutput(this);
	private PIDController teleoperatedPIDController = new PIDController(
			RobotTuningConstants.DRIVE_PROPORTIONAL,
			RobotTuningConstants.DRIVE_INTEGRAL,
			RobotTuningConstants.DRIVE_DERIVATIVE,
			RobotTuningConstants.DRIVE_FEED_FORWARD,
			motorPIDInput,
			motorPIDOutput);
	private DistancePID autonomousPIDController = new DistancePID(
			RobotTuningConstants.DISTANCE_PROPORTIONAL,
			RobotTuningConstants.DISTANCE_INTEGRAL,
			RobotTuningConstants.DISTANCE_DERIVATIVE,
			RobotTuningConstants.DISTANCE_FEED_FORWARD,
			0,
			RobotStructureConstants.ENCODER_COUNTS_PER_INCH,
			this);
	private RobotMode currentMode;
	
    public ChassisSubsystem() {
    	Motors.leftMotor.setInverted(UserInterfaceConstants.MOTOR_LEFT_INVERT);
    	Motors.rightMotor.setInverted(UserInterfaceConstants.MOTOR_RIGHT_INVERT);
    	Motors.leftMiniCIM.setInverted(UserInterfaceConstants.MINICIM_LEFT_INVERT);
    	Motors.rightMiniCIM.setInverted(UserInterfaceConstants.MINICIM_RIGHT_INVERT);
		
    	teleoperatedPIDController.setInputRange(-Math.PI, Math.PI);
    	teleoperatedPIDController.setOutputRange(-Math.PI, Math.PI);
    	teleoperatedPIDController.setSetpoint(0);
    	autonomousPIDController.setInputRange(0, 10);
    	autonomousPIDController.setOutputRange(-1, 1);
    	autonomousPIDController.setSetpoint(0);
    	currentMode = RobotMode.DISABLED;
    }
    
    // MIGHT NEED TO CALL THIS!
    public void init() {
    	motorPIDInput.init();
    	setTeleoperatedTargetMotion(0, 0);
    	
    }
    
    // MIGHT NEED TO CALL THIS!
    public void reset() {		
    	teleoperatedPIDController.reset();
    	// autonomousPIDController.setSetpoint(0);
    }
    
    @Override
	public void periodic() {
	}
    
    public void setCurrentMode(RobotMode mode) {
    	currentMode = mode;
    	switch (mode) {
    		case TELEOPERATED:
    			teleoperatedPIDController.enable();
    			autonomousPIDController.disable();
    			break;
    		case AUTONOMOUS:
    			teleoperatedPIDController.disable();
    			autonomousPIDController.enable();
    			break;
    	}
    }
    
    public void setTeleoperatedTargetMotion(double angle, double speed) {
		// If it doesn't drive, this is the likely culprit.
    	if (currentMode == RobotMode.TELEOPERATED) {
			motorPIDInput.setTargetAngle(angle);
			motorPIDOutput.setTargetSpeed(speed);
    	}
		// System.out.println("Updated joystick turn!: " + angle);
    }
    
    public void setAutonomousTarget(double angle, double distance) {
		// If it doesn't drive, this is the likely culprit.
    	if (currentMode == RobotMode.AUTONOMOUS) {
			autonomousPIDController.setDistance(distance);
			autonomousPIDController.setSetpoint(1);
			System.out.println("A setpoint has been set: " + distance);
    	}
		// System.out.println("Updated joystick turn!: " + angle);
    }
    
    public boolean isAutonomousOnTarget() {
    	return autonomousPIDController.onTarget();
    }
    
    public void endAutonomousCommand() {
    	autonomousPIDController.setSetpoint(0);
    }
    
    public void setMotors(double leftMotors, double rightMotors) {
    	Motors.leftMotor.pidWrite(leftMotors);
		Motors.rightMotor.pidWrite(rightMotors);
		Motors.leftMiniCIM.pidWrite(-leftMotors);
		Motors.rightMiniCIM.pidWrite(-rightMotors);
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
		SmartDashboard.putData("Motor Controller", teleoperatedPIDController);
	}

	public boolean isAngleOnTarget() {
		return teleoperatedPIDController.onTarget();
	}
}

