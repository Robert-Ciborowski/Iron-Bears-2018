package org.usfirst.frc.team854.robot.utils;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team854.robot.Robot;
import org.usfirst.frc.team854.robot.hardware.InterfaceType;

public class PIDSourceLogger {

	private List<Double> values;
	private InterfaceType type;
	private int port;
	
	public PIDSourceLogger(InterfaceType type, int port) {
		values = new ArrayList<>();
		this.port = port;
		this.type = type;
	}
	
	public void log() {
		values.add(Robot.devices.getDeviceValue(type, port));
	}
	
	public void output() {
		for (Double doubleToOutput : values) {
			System.out.print(doubleToOutput + "\t");
		}
		//System.out.flush();
		System.out.print("\n");
		
		// Clear the array
		values.clear();
	}
}
