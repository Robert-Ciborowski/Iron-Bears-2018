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
			setOuterIntakeDirection(Direction1D.REVERSE);
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

    	if (extended) {
    		leftSolenoid.set(DoubleSolenoid.Value.kForward);
    		rightSolenoid.set(DoubleSolenoid.Value.kForward);
    	} else {
    		leftSolenoid.set(DoubleSolenoid.Value.kReverse);
    		rightSolenoid.set(DoubleSolenoid.Value.kReverse);
    	}
    }

    public void setInnerIntakeDirection(Direction1D direction) {
    	if (!enabled) {
    		return;
    	}

    	switch (direction) {
			case FORWARD:
				leftIntakeInnerMotor.set(-RobotCommandConstants.INTAKE_MOTOR_SPEED);
				rightIntakeInnerMotor.set(RobotCommandConstants.INTAKE_MOTOR_SPEED);
				break;
			case REVERSE:
				leftIntakeInnerMotor.set(RobotCommandConstants.INTAKE_MOTOR_SPEED);
				rightIntakeInnerMotor.set(-RobotCommandConstants.INTAKE_MOTOR_SPEED);
				break;
			case OFF:
				leftIntakeInnerMotor.set(0);
				rightIntakeInnerMotor.set(0);
				break;
    	}
    }

    public void setOuterIntakeDirection(Direction1D direction) {
    	if (!enabled) {
    		return;
    	}

    	switch (direction) {
			case FORWARD:
				leftIntakeOuterMotor.set(-RobotCommandConstants.INTAKE_MOTOR_SPEED);
				rightIntakeOuterMotor.set(-RobotCommandConstants.INTAKE_MOTOR_SPEED);
				break;
			case REVERSE:
				leftIntakeOuterMotor.set(RobotCommandConstants.INTAKE_MOTOR_SPEED);
				rightIntakeOuterMotor.set(RobotCommandConstants.INTAKE_MOTOR_SPEED);
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
		SmartDashboard.putString("Is there a box?", fullLimitSwitch.get() ? "yes" : "no");
//		BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
//		img.getGraphics().setColor(Color.RED);
//		img.getGraphics().fillRect(0, 0, 100, 100);
//		try {
//			ByteArrayOutputStream b = new ByteArrayOutputStream();
//			ImageIO.write(img, "png", b);
//			SmartDashboard.putRaw("abcdefg", b.toByteArray());
//			GyroBase
//			img.getGraphics().dispose();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}	
	}
}
