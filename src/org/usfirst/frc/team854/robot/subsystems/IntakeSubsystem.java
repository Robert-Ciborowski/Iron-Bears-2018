/**
 * Name: IntakeSubsystem
 * Authors: Julian Dominguez-Schatz
 * Date: 13/02/2018
 * Description: Represents the intake subsystem of the robot, i.e. how the robot grabs boxes.
 */

package org.usfirst.frc.team854.robot.subsystems;

import org.usfirst.frc.team854.robot.CustomSubsystem;
import org.usfirst.frc.team854.robot.Robot;
import org.usfirst.frc.team854.robot.RobotMode;
import org.usfirst.frc.team854.robot.constants.RobotCommandConstants;
import org.usfirst.frc.team854.robot.constants.RobotInterfaceConstants;
import org.usfirst.frc.team854.robot.hardware.InterfaceType;
import org.usfirst.frc.team854.robot.utils.Direction1D;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IntakeSubsystem extends CustomSubsystem {
	private DoubleSolenoid leftSolenoid = Robot.devices.getDevice(InterfaceType.PCM, RobotInterfaceConstants.PORT_PNEUMATIC_LEFT);
	private DoubleSolenoid rightSolenoid = Robot.devices.getDevice(InterfaceType.PCM, RobotInterfaceConstants.PORT_PNEUMATIC_RIGHT);

	private Spark leftIntakeInnerMotor = Robot.devices.getDevice(InterfaceType.PWM, RobotInterfaceConstants.PORT_MOTOR_INTAKE_INNER_LEFT);
	private Spark rightIntakeInnerMotor = Robot.devices.getDevice(InterfaceType.PWM, RobotInterfaceConstants.PORT_MOTOR_INTAKE_INNER_RIGHT);
	private Spark leftIntakeOuterMotor = Robot.devices.getDevice(InterfaceType.PWM, RobotInterfaceConstants.PORT_MOTOR_INTAKE_OUTER_LEFT);
	private Spark rightIntakeOuterMotor = Robot.devices.getDevice(InterfaceType.PWM, RobotInterfaceConstants.PORT_MOTOR_INTAKE_OUTER_RIGHT);

	private DigitalInput fullLimitSwitch = Robot.devices.getDevice(InterfaceType.DIGITAL, RobotInterfaceConstants.PORT_SWITCH_INTAKE_FULL);

	private long fullElapsedTime;
	private long fullStartTime;
	
	private boolean pneumaticsExtended;

    public IntakeSubsystem() {
    }

    @Override
	public void setCurrentMode(RobotMode mode) {
    	super.setCurrentMode(mode);
    	
    	if (enabled) {
	    	// This resets time values.
	    	fullElapsedTime = -1;
	    	fullStartTime = 0;

			setInnerIntakeDirection(Direction1D.OFF);
			setOuterIntakeDirection(Direction1D.OFF);
//			setOuterIntakeDirection(Direction1D.REVERSE);
//			setPneumaticsExtended(false);
    	}
	}
    
    @Override
    public void setEnabled(boolean enabled) {
    	super.setEnabled(enabled);
    	if (!enabled) {
    		leftIntakeInnerMotor.set(0);
    		rightIntakeInnerMotor.set(0);
    		leftIntakeOuterMotor.set(0);
    		rightIntakeOuterMotor.set(0);
    	}
    }
    
    @Override
    public void periodic() {
    }
    
    public boolean isFull() {
    	boolean value = fullLimitSwitch.get();
    	if (!value) {
    		fullElapsedTime = -1;
    		return false;
    	}

    	if (fullElapsedTime == -1) {
    		fullStartTime = System.currentTimeMillis();
    		fullElapsedTime = 0;
    		return false;
    	}

    	fullElapsedTime = System.currentTimeMillis() - fullStartTime;
    	return fullElapsedTime >= RobotCommandConstants.INTAKE_FULL_SWITCH_DELAY_MS;
    }
    
    public void setPneumaticsExtended(boolean extended) {
    	if (!enabled) {
    		return;
    	}

    	pneumaticsExtended = extended;
    	if (extended) {
    		leftSolenoid.set(DoubleSolenoid.Value.kForward);
    		rightSolenoid.set(DoubleSolenoid.Value.kForward);
    	} else {
    		leftSolenoid.set(DoubleSolenoid.Value.kReverse);
    		rightSolenoid.set(DoubleSolenoid.Value.kReverse);
    	}
    }
    
    public boolean getPneumaticsExtended() {
    	return pneumaticsExtended;
    }

    public void setInnerIntakeDirection(Direction1D direction) {
    	if (!enabled) {
    		return;
    	}
    	
    	double intakeSpeed;
    	if (currentMode == RobotMode.TELEOPERATED || currentMode == RobotMode.TEST) {
    		intakeSpeed = Robot.oi.getIntakeSpeed();
    	} else {
    		intakeSpeed = RobotCommandConstants.INTAKE_MOTOR_SPEED;
    	}

    	switch (direction) {
			case FORWARD:
				leftIntakeInnerMotor.set(-intakeSpeed);
				rightIntakeInnerMotor.set(intakeSpeed);
				break;
			case REVERSE:
				leftIntakeInnerMotor.set(intakeSpeed);
				rightIntakeInnerMotor.set(-intakeSpeed);
				break;
			case OFF:
				leftIntakeInnerMotor.set(0);
				rightIntakeInnerMotor.set(0);
				break;
    	}
    }
    
    public void setInnerMotors(double leftSpeed, double rightSpeed) {
    	leftIntakeInnerMotor.set(leftSpeed);
    	rightIntakeInnerMotor.set(rightSpeed);
    }
    
    public void setOuterMotors(double leftSpeed, double rightSpeed) {
    	leftIntakeOuterMotor.set(leftSpeed);
    	rightIntakeOuterMotor.set(rightSpeed);
    }

    public void setInnerMotorDirections(Direction1D directionLeft, Direction1D directionRight) {
    	if (!enabled) {
    		return;
    	}
    	
    	double intakeSpeed;
    	if (currentMode == RobotMode.TELEOPERATED || currentMode == RobotMode.TEST) {
    		intakeSpeed = Robot.oi.getIntakeSpeed();
    	} else {
    		intakeSpeed = RobotCommandConstants.INTAKE_MOTOR_SPEED;
    	}

    	switch (directionLeft) {
			case FORWARD:
				leftIntakeInnerMotor.set(-intakeSpeed);
				break;
			case REVERSE:
				leftIntakeInnerMotor.set(intakeSpeed);
				break;
			case OFF:
				leftIntakeInnerMotor.set(0);
				break;
    	}

    	switch (directionRight) {
			case FORWARD:
				rightIntakeInnerMotor.set(intakeSpeed);
				break;
			case REVERSE:
				rightIntakeInnerMotor.set(-intakeSpeed);
				break;
			case OFF:
				rightIntakeInnerMotor.set(0);
				break;
    	}
    }

    public void setOuterMotorDirections(Direction1D directionLeft, Direction1D directionRight) {
    	if (!enabled) {
    		return;
    	}
    	
    	double intakeSpeed;
    	if (currentMode == RobotMode.TELEOPERATED || currentMode == RobotMode.TEST) {
    		intakeSpeed = Robot.oi.getIntakeSpeed();
    	} else {
    		intakeSpeed = RobotCommandConstants.INTAKE_MOTOR_SPEED;
    	}

    	switch (directionLeft) {
			case FORWARD:
				leftIntakeOuterMotor.set(-intakeSpeed);
				break;
			case REVERSE:
				leftIntakeOuterMotor.set(intakeSpeed);
				break;
			case OFF:
				leftIntakeOuterMotor.set(0);
				break;
    	}

    	switch (directionRight) {
			case FORWARD:
				rightIntakeOuterMotor.set(intakeSpeed);
				break;
			case REVERSE:
				rightIntakeOuterMotor.set(-intakeSpeed);
				break;
			case OFF:
				rightIntakeOuterMotor.set(0);
				break;
    	}
    }

    public void setOuterIntakeDirection(Direction1D direction) {
    	if (!enabled) {
    		return;
    	}
    	
    	double intakeSpeed;
    	if (currentMode == RobotMode.TELEOPERATED || currentMode == RobotMode.TEST) {
    		intakeSpeed = Robot.oi.getIntakeSpeed();
    	} else {
    		intakeSpeed = RobotCommandConstants.INTAKE_MOTOR_SPEED;
    	}

    	switch (direction) {
			case FORWARD:
				leftIntakeOuterMotor.set(-intakeSpeed);
				rightIntakeOuterMotor.set(-intakeSpeed);
				break;
			case REVERSE:
				leftIntakeOuterMotor.set(intakeSpeed);
				rightIntakeOuterMotor.set(intakeSpeed);
				break;
			case OFF:
				leftIntakeOuterMotor.set(0);
				rightIntakeOuterMotor.set(0);
				break;
    	}
    }

    @Override
	public void initDefaultCommand() {
	}

	@Override
	public void updateDashboard() {
//		SmartDashboard.putString("Is there a box?", fullLimitSwitch.get() ? "yes" : "no");
		SmartDashboard.putBoolean("cube", fullLimitSwitch.get());
	}
}
