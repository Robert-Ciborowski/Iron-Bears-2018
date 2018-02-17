package org.usfirst.frc.team854.robot.hardware;

import java.util.HashMap;

import edu.wpi.first.wpilibj.SendableBase;

public enum InterfaceType {
	ANALOG, DIGITAL, PWM, RELAY, PCM;

	HashMap<Integer, SendableBase> deviceList;

	private InterfaceType() {
		deviceList = new HashMap<>();
	}
}