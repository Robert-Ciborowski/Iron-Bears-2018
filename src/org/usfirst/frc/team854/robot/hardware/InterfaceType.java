/*
 * Name: InterfaceType
 * Author: Julian Dominguez-Schatz
 * Date: 10/02/2018
 * Description: An enum for different types of ports on the RoboRIO.
 */

package org.usfirst.frc.team854.robot.hardware;

import java.util.HashMap;

import edu.wpi.first.wpilibj.SendableBase;

public enum InterfaceType {
	ANALOG, DIGITAL, PWM, RELAY, PCM, MXP;

	HashMap<Integer, SendableBase> deviceList;

	private InterfaceType() {
		deviceList = new HashMap<>();
	}
}