/*
 * Class: CutoffFilter
 * Author: Julian Dominguez-Schatz, Robert Ciborowski
 * Date: 25/01/2018
 * Description: A filter that will cause a value to be zero if its less than a threshold value.
 */

package org.usfirst.frc.team854.robot.operatorinterface;

public class CutoffFilter implements Filter {
	
	private double thresholdValue;
	
	public CutoffFilter(double thresholdValue) {
		this.thresholdValue = thresholdValue;
	}
	
	@Override
	public double filter(double value) {
		// If the input value is very close to 0, clamp it to 0.
		// This helps prevent unnecessary input noise.
		if (Math.abs(value) < thresholdValue) {
			return 0;
		}
		
		return value;
	}
}
