/**
 * Name: RobotArmSubsystem
 * Authors: Julian Dominguez-Schatz, Robert Ciborowski, Shaiza Hashmi
 * Date: 10/02/2018
 * Description: The subsystem of our robot that controls its arm.
 */

package org.usfirst.frc.team854.robot.subsystems;

import org.usfirst.frc.team854.robot.CustomSubsystem;
import org.usfirst.frc.team854.robot.Robot;
import org.usfirst.frc.team854.robot.constants.RobotInterfaceConstants;
import org.usfirst.frc.team854.robot.constants.RobotTuningConstants;
import org.usfirst.frc.team854.robot.hardware.InterfaceType;
import org.usfirst.frc.team854.robot.teleopdrive.JoystickCommand;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;

public class ArmSubsystem extends CustomSubsystem {
	private DigitalInput homeLimitSwitch = Robot.devices.getDevice(InterfaceType.DIGITAL, RobotInterfaceConstants.PORT_SWITCH_INTAKE_FULL);
	private Encoder encoder = Robot.devices.getDevice(InterfaceType.DIGITAL, RobotInterfaceConstants.PORT_ENCODER_ARM);
	private RobotArmLevel targetLevel = RobotArmLevel.GROUND;
	private boolean isCalibrated = false;

	private PIDController armController = new PIDController(
			RobotTuningConstants.ARM_PROPORTIONAL,
			RobotTuningConstants.ARM_INTEGRAL,
			RobotTuningConstants.ARM_DERIVATIVE,
			Robot.devices.getDevice(InterfaceType.DIGITAL, RobotInterfaceConstants.PORT_ENCODER_ARM),
			Robot.devices.getDevice(InterfaceType.PWM, RobotInterfaceConstants.PORT_MOTOR_ARM));

    public ArmSubsystem() {
    	armController.setSetpoint(0);
    	armController.setAbsoluteTolerance(.05);
    	armController.setContinuous(false);
    	Robot.devices.<Encoder>getDevice(InterfaceType.DIGITAL, RobotInterfaceConstants.PORT_ENCODER_ARM).reset();
    }

    @Override
    public void periodic() {
    	if (!isCalibrated && homeLimitSwitch.get()) {
    		isCalibrated = true;
    		encoder.reset();
    	}
    }

    public void reset() {
    	armController.reset();
    }
    
    public boolean isInHomePosition() {
    	return homeLimitSwitch.get();
    }

    public void setArmLevel(RobotArmLevel level) {
    	armController.setSetpoint(level.getSetpoint());
    	targetLevel = level;
    }

    public void increaseTargetLevel() {
    	switch (targetLevel) {
    		case GROUND:
    			setArmLevel(RobotArmLevel.SWITCH);
    			Robot.intakeSubsystem.setOuterIntakeDirection(Direction1D.REVERSE);
    			break;
    		case SWITCH:
    			setArmLevel(RobotArmLevel.SCALE);
    			break;
    		case SCALE:
    			setArmLevel(RobotArmLevel.CLIMB);
    			break;
			case CLIMB:
				break;
			default:
				break;
    	}
    }
    
    private boolean temporaryHomeLockOverride = false;
    
    public void overrideHomeLock() {
    	temporaryHomeLockOverride = true;
    }

    public void decreaseTargetLevel() {
    	switch (targetLevel) {
    		case CLIMB:
    			setArmLevel(RobotArmLevel.SCALE);
    			break;
    		case SCALE:
    			setArmLevel(RobotArmLevel.SWITCH);
    			break;
    		case SWITCH:
    			if (temporaryHomeLockOverride) {
    				setArmLevel(RobotArmLevel.GROUND);
    			}
    			break;
			case GROUND:
				break;
			default:
				break;
    	}
    }

    public boolean isArmOnTarget() {
    	return armController.onTarget();
    }

    public RobotArmLevel getTargetLevel() {
    	return targetLevel;
    }

	public void initDefaultCommand() {
		setDefaultCommand(new JoystickCommand());
	}
	
	public boolean isArmCalibrated() {
		return isCalibrated;
	}
	
	@Override
	public void updateDashboard() {
	}
}

