package org.usfirst.frc.team854.robot.hardware;

import java.util.HashMap;

import edu.wpi.first.wpilibj.PIDSource;

public class SensorProvider {
	
	public enum SensorType {
		ANALOG, DIGITAL, PWM;

		private HashMap<Integer, PIDSource> sensorList;

		private SensorType() {
			sensorList = new HashMap<>();
		}
	}

	public void putSensor(SensorType type, int port, PIDSource sensor) {
		type.sensorList.put(port, sensor);
	}
	
	public PIDSource getSensor(SensorType type, int port) {
		return type.sensorList.get(port);
	}

	public double getSensorValue(SensorType type, int port) {
		return type.sensorList.get(port).pidGet();
	}
}
