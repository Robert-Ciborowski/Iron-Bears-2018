package org.usfirst.frc.team854.robot.utils;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team854.robot.Robot;
import org.usfirst.frc.team854.robot.hardware.SensorProvider.SensorType;

public class PIDSourceLogger {

	private List<Double> values;
	private SensorType type;
	private int port;
	
	public PIDSourceLogger(SensorType type, int port) {
		values = new ArrayList<>();
		this.port = port;
	}
	
	public void log() {
		values.add(Robot.sensors.getSensorValue(type, port));
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
