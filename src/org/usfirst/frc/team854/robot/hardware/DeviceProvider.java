/*
 * Name: DeviceProvider
 * Author: Julian Dominguez-Schatz
 * Date: 10/02/2018
 * Description: A class which provides access to devices.
 */

package org.usfirst.frc.team854.robot.hardware;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SendableBase;

public class DeviceProvider {
	
	public void putDevice(InterfaceType type, int port, SendableBase sensor) {
		type.deviceList.put(port, sensor);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends SendableBase> T getDevice(InterfaceType type, int port) {
		return (T) type.deviceList.get(port);
	}

	public double getDeviceValue(InterfaceType type, int port) {
		SendableBase device = type.deviceList.get(port);
		
		if (!(device instanceof PIDSource)) {
			throw new IllegalStateException("Are you kidding me? Why on earth would you try to get a \"value\" from a motor?");
		}
		
		return ((PIDSource) type.deviceList.get(port)).pidGet();
	}
}
