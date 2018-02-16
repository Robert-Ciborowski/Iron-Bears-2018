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

import edu.wpi.first.wpilibj.Relay;

public class IntakeSubsystem extends CustomSubsystem {

	private Relay leftRelay = Robot.devices.getDevice(InterfaceType.RELAY, RobotInterfaceConstants.PORT_RELAY_LEFT);
	private Relay rightRelay = Robot.devices.getDevice(InterfaceType.RELAY, RobotInterfaceConstants.PORT_RELAY_RIGHT);

    public IntakeSubsystem() {
    }

    public void updateState(boolean extended) {
    	if (extended) {
    		leftRelay.set(Relay.Value.kForward);
    		rightRelay.set(Relay.Value.kForward);
    	} else {
    		leftRelay.set(Relay.Value.kReverse);
    		rightRelay.set(Relay.Value.kReverse);
    	}
    }

	public void initDefaultCommand() {
	}

	@Override
	public void updateDashboard() {
	}
}

