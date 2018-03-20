/**
 * Name: ArmSubsystem
 * Authors: Robert Ciborowski, Julian Dominguez-Schatz, Shaiza Hashmi
 * Date: 10/02/2018
 * Description: The subsystem of our robot that controls its arm.
 */

package org.usfirst.frc.team854.robot.subsystems;

import org.usfirst.frc.team854.robot.CustomSubsystem;
import org.usfirst.frc.team854.robot.Robot;
import org.usfirst.frc.team854.robot.RobotMode;
import org.usfirst.frc.team854.robot.PID.ArmPIDInput;
import org.usfirst.frc.team854.robot.PID.ArmPIDOutput;
import org.usfirst.frc.team854.robot.constants.RobotInterfaceConstants;
import org.usfirst.frc.team854.robot.constants.RobotTuningConstants;
import org.usfirst.frc.team854.robot.hardware.InterfaceType;
import org.usfirst.frc.team854.robot.utils.Direction1D;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArmSubsystemWithoutPID extends CustomSubsystem {
	private DigitalInput homeLimitSwitch = Robot.devices.getDevice(InterfaceType.DIGITAL, RobotInterfaceConstants.PORT_SWITCH_INTAKE_FULL);
	private Encoder encoder = Robot.devices.getDevice(InterfaceType.DIGITAL, RobotInterfaceConstants.PORT_ENCODER_ARM);
	private RobotArmLevel targetLevel = RobotArmLevel.GROUND;
	private boolean calibrated = false;
	
	private Encoder armEncoder = Robot.devices.getDevice(InterfaceType.DIGITAL, RobotInterfaceConstants.PORT_ENCODER_ARM);
	private boolean moving = false;
	private Direction1D directionOfMoving;
	private final double tolerance = 15;
	private final double switchArmHoldValue = 0.3, scaleArmHoldValue = 0.15, climbArmHoldValue = 0.1;

    public ArmSubsystemWithoutPID() {
    }

    @Override
    public void periodic() {
//    	if (!isCalibrated && homeLimitSwitch.get()) {
//    		isCalibrated = true;
//    		armEncoder.reset();
//    	}
    	
    	if (moving) {
	    	if (directionOfMoving == Direction1D.FORWARD) {
	    		if (encoder.get() >= targetLevel.getSetpoint() - tolerance) {
	    			double value;
	    			switch (targetLevel) {
	    				case SWITCH:
	    					value = switchArmHoldValue;
	    					break;
	    				case SCALE:
	    					value = scaleArmHoldValue;
	    					break;
	    				case CLIMB:
	    					value = climbArmHoldValue;
	    					break;
	    				default:
	    					value = 0;
	    					break;
	    			}
	    			setMotor(value);
	    			moving = false;
	    			directionOfMoving = Direction1D.OFF;
	    			System.out.println("At target forward!");
	    		}
	    	}
	    	if (directionOfMoving == Direction1D.REVERSE) {
	    		if (encoder.get() <= targetLevel.getSetpoint() + tolerance) {
	    			setMotor(0);
	    			moving = false;
	    			directionOfMoving = Direction1D.OFF;
	    			System.out.println("At target reverse!");
	    		}
	    	}
    	}
    	// System.out.println("Target: " + targetLevel.getSetpoint() + ", Current: " + encoder.get() + "Motor: " + ((Spark) Robot.devices.getDevice(InterfaceType.PWM, RobotInterfaceConstants.PORT_MOTOR_ARM)).get());
    }

    @Override
    public void setEnabled(boolean enabled) {
    	super.setEnabled(enabled);
    }

    @Override
	public void setCurrentMode(RobotMode mode) {
    	super.setCurrentMode(mode);
    	
    	if (enabled) {
	    	// armEncoder.reset();
	
			switch (mode) {
				case TELEOPERATED:
					break;
				case AUTONOMOUS:
					break;
				case DISABLED:
					break;
				case TEST:
					break;
				default:
					break;
			}
    	}
	}
    
    public boolean isInHomePosition() {
    	return homeLimitSwitch.get();
    }
    

    public void setArmLevel(RobotArmLevel level) {
    	if (targetLevel.getSetpoint() > level.getSetpoint()) {
    		directionOfMoving = Direction1D.REVERSE;
    		setMotor(-0.7);
    		moving = true;
//    		armController.setPID(RobotTuningConstants.ARM_UP_PROPORTIONAL, RobotTuningConstants.ARM_UP_INTEGRAL,
//    				RobotTuningConstants.ARM_UP_DERIVATIVE);
    		System.out.println("DOWN! " + targetLevel.getSetpoint() + ", " + level.getSetpoint());
    	} else if (targetLevel.getSetpoint() < level.getSetpoint()) {
    		directionOfMoving = Direction1D.FORWARD;
    		setMotor(0.9);
    		moving = true;
//    		armController.setPID(RobotTuningConstants.ARM_DOWN_PROPORTIONAL, RobotTuningConstants.ARM_DOWN_INTEGRAL,
//    				RobotTuningConstants.ARM_DOWN_DERIVATIVE);
    		System.out.println("UP! " + targetLevel.getSetpoint() + ", " + level.getSetpoint());
    	}
    	System.out.println("Set arm level to: " + level.getSetpoint());
    	targetLevel = level;
    }
    
    public void setMotor(double speed) {
    	((Spark) Robot.devices.getDevice(InterfaceType.PWM, RobotInterfaceConstants.PORT_MOTOR_ARM)).set(speed);
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
    			if (temporaryHomeLockOverride || !Robot.intakeSubsystem.isFull()) {
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
    	return !moving;
    }

    public RobotArmLevel getTargetLevel() {
    	return targetLevel;
    }
	
	public boolean isArmCalibrated() {
		return calibrated;
	}
	
	@Override
	public void updateDashboard() {
//		SmartDashboard.putData("Arm Controller", armController);
//		SmartDashboard.putNumber("Arm Setpoint", armController.getSetpoint());
		SmartDashboard.putNumber("Arm Encoder", encoder.get());
//		System.out.println("Encoder: " + encoder.get());
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}

