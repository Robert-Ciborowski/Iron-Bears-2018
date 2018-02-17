/**
 * Name: IntakeSubsystem
 * Authors: Julian Dominguez-Schatz
 * Date: 13/02/2018
 * Description: Represents the intake subsystem of the robot, i.e. how the robot grabs boxes.
 */

package org.usfirst.frc.team854.robot.subsystems;

import org.usfirst.frc.team854.robot.CustomSubsystem;
import org.usfirst.frc.team854.robot.Robot;
import org.usfirst.frc.team854.robot.constants.RobotInterfaceConstants;
import org.usfirst.frc.team854.robot.hardware.InterfaceType;
import org.usfirst.frc.team854.robot.teleopdrive.IntakeCommand;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;

public class IntakeSubsystem extends CustomSubsystem {

	private DoubleSolenoid leftSolenoid = Robot.devices.getDevice(InterfaceType.PCM, RobotInterfaceConstants.PORT_PNEUMATIC_LEFT);
	private DoubleSolenoid rightSolenoid = Robot.devices.getDevice(InterfaceType.PCM, RobotInterfaceConstants.PORT_PNEUMATIC_RIGHT);

	private Spark leftIntakeInnerMotor = Robot.devices.getDevice(InterfaceType.PWM, RobotInterfaceConstants.PORT_MOTOR_INTAKE_INNER_LEFT);
	private Spark rightIntakeInnerMotor = Robot.devices.getDevice(InterfaceType.PWM, RobotInterfaceConstants.PORT_MOTOR_INTAKE_INNER_RIGHT);
	private Spark leftIntakeOuterMotor = Robot.devices.getDevice(InterfaceType.PWM, RobotInterfaceConstants.PORT_MOTOR_INTAKE_OUTER_LEFT);
	private Spark rightIntakeOuterMotor = Robot.devices.getDevice(InterfaceType.PWM, RobotInterfaceConstants.PORT_MOTOR_INTAKE_OUTER_RIGHT);

	private DigitalInput fullLimitSwitch = Robot.devices.getDevice(InterfaceType.DIGITAL, RobotInterfaceConstants.PORT_SWITCH_INTAKE_FULL);
	private Counter fullCounter = new Counter(fullLimitSwitch);

    public IntakeSubsystem() {
    	resetFullCounter();
    }
    
    public void resetFullCounter() {
    	fullCounter.reset();
    }
    
    public boolean isFull() {
    	return fullCounter.get() > 0;
    }
    
    public void setPneumaticsExtended(boolean extended) {
    	if (extended) {
    		leftSolenoid.set(DoubleSolenoid.Value.kForward);
    		rightSolenoid.set(DoubleSolenoid.Value.kForward);
    	} else {
    		leftSolenoid.set(DoubleSolenoid.Value.kReverse);
    		rightSolenoid.set(DoubleSolenoid.Value.kReverse);
    	}
    }

    public void setInnerIntakeDirection(Direction1D direction) {
    	switch (direction) {
			case FORWARD:
				leftIntakeInnerMotor.set(1);
				rightIntakeInnerMotor.set(-1);
				break;
			case REVERSE:
				leftIntakeInnerMotor.set(-1);
				rightIntakeInnerMotor.set(1);
				break;
			case OFF:
				leftIntakeInnerMotor.set(0);
				rightIntakeInnerMotor.set(0);
				break;
    	}
    }

    public void setOuterIntakeDirection(Direction1D direction) {
    	switch (direction) {
			case FORWARD:
				leftIntakeOuterMotor.set(1);
				rightIntakeOuterMotor.set(-1);
				break;
			case REVERSE:
				leftIntakeOuterMotor.set(-1);
				rightIntakeOuterMotor.set(1);
				break;
			case OFF:
				leftIntakeOuterMotor.set(0);
				rightIntakeOuterMotor.set(0);
				break;
    	}
    }

	public void initDefaultCommand() {
		setDefaultCommand(new IntakeCommand());
	}

	@Override
	public void updateDashboard() {
	}
}
