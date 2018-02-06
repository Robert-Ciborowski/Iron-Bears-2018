package utils;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.PIDSource;

public class PIDSourceLogger {

	private List<Double> values;
	private PIDSource source;
	
	public PIDSourceLogger(PIDSource source) {
		values = new ArrayList<>();
		this.source = source;
	}
	
	public void log() {
		values.add(source.pidGet());
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
