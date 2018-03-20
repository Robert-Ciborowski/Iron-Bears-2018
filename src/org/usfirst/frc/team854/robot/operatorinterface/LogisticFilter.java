/*
 * Class: LogisticFilter
 * Author: Julian Dominguez-Schatz, Robert Ciborowski
 * Date: 17/03/2018
 * Description: A filter which modifies a value based on a logistic function, i.e.
 */

package org.usfirst.frc.team854.robot.operatorinterface;

public class LogisticFilter implements Filter {
	
	private double numerator;
	private double offset;
	private double xStretch;
	private double halfMaxValue;

	public LogisticFilter() {
		this(1);
	}

	public LogisticFilter(double maxValue) {
		this(1, 0.2);
	}

	public LogisticFilter(double maxValue, double sensitivity) {
		halfMaxValue = maxValue / 2;
		numerator = maxValue * (1 + sensitivity);
		offset = -(sensitivity * maxValue) / 2;
		xStretch = (-2 / maxValue) * Math.log((2 / sensitivity) + 1);
	}

	@Override
	public double filter(double value) {
		// Ensure the value is in the domain of log_e(v).
		double transformedValue = Math.abs(value);
		double signOfValue = Math.signum(value);
		
		double power = xStretch * (transformedValue - halfMaxValue);
		return ((numerator / (1 + Math.exp(power))) + offset) * signOfValue;
	}

}
