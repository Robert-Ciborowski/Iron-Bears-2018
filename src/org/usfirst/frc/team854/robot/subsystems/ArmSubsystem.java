/**
 * Name: RobotArmSubsystem
 * Authors: Julian Dominguez-Schatz, Robert Ciborowski, Shaiza Hashmi
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
import org.usfirst.frc.team854.robot.operatorinterface.OperatorInterface;
import org.usfirst.frc.team854.robot.teleopdrive.JoystickCommand;
import org.usfirst.frc.team854.robot.utils.Direction1D;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArmSubsystem extends CustomSubsystem {
	private DigitalInput homeLimitSwitch = Robot.devices.getDevice(InterfaceType.DIGITAL, RobotInterfaceConstants.PORT_SWITCH_INTAKE_FULL);
	private Encoder encoder = Robot.devices.getDevice(InterfaceType.DIGITAL, RobotInterfaceConstants.PORT_ENCODER_ARM);
	private RobotArmLevel targetLevel = RobotArmLevel.GROUND;
	private boolean isCalibrated = false;
	
	private Encoder armEncoder = Robot.devices.getDevice(InterfaceType.DIGITAL, RobotInterfaceConstants.PORT_ENCODER_ARM);

	private ArmPIDInput armPIDInput = new ArmPIDInput();
	private ArmPIDOutput armPIDOutput = new ArmPIDOutput();
	private PIDController armController = new PIDController(
			RobotTuningConstants.ARM_UP_PROPORTIONAL,
			RobotTuningConstants.ARM_UP_INTEGRAL,
			RobotTuningConstants.ARM_UP_DERIVATIVE,
			armPIDInput,
			armPIDOutput);

    public ArmSubsystem() {
    	armController.setSetpoint(0);
    	armController.setAbsoluteTolerance(0.05);
    	armController.setContinuous(false);
    	armController.setOutputRange(-1, 1);
    	armController.setInputRange(-10000, 10000);
    }

    @Override
    public void periodic() {
//    	if (!isCalibrated && homeLimitSwitch.get()) {
//    		isCalibrated = true;
//    		armEncoder.reset();
//    	}
    }

    @Override
    public void setEnabled(boolean enabled) {
    	super.setEnabled(enabled);
    	
    	if (!enabled) {
    		armController.disable();
    	} else {
    		armController.enable();
    	}
    }

    @Override
	public void setCurrentMode(RobotMode mode) {
    	super.setCurrentMode(mode);
    	armController.reset();
    	armEncoder.reset();

		switch (mode) {
			case TELEOPERATED:
				armController.enable();
				break;
			case AUTONOMOUS:
				armController.enable();
				break;
			case DISABLED:
				armController.disable();
				break;
			case TEST:
				armController.enable();
				break;
			default:
				armController.disable();
				break;
		}
	}
    
    public boolean isInHomePosition() {
    	return homeLimitSwitch.get();
    }
    

    public void setArmLevel(RobotArmLevel level) {
    	armController.setSetpoint(level.getSetpoint());
    	if (targetLevel.ordinal() < level.ordinal()) {
//    		armController.setPID(RobotTuningConstants.ARM_UP_PROPORTIONAL, RobotTuningConstants.ARM_UP_INTEGRAL,
//    				RobotTuningConstants.ARM_UP_DERIVATIVE);
//    		System.out.println("UP!");
    	} else if (targetLevel.ordinal() > level.ordinal()) {
    		armController.setPID(RobotTuningConstants.ARM_DOWN_PROPORTIONAL, RobotTuningConstants.ARM_DOWN_INTEGRAL,
    				RobotTuningConstants.ARM_DOWN_DERIVATIVE);
    		System.out.println("DOWN!");
    	}
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
		SmartDashboard.putData(armController);
		SmartDashboard.putData(Robot.devices.getDevice(InterfaceType.PWM, RobotInterfaceConstants.PORT_MOTOR_ARM));
	}
}

