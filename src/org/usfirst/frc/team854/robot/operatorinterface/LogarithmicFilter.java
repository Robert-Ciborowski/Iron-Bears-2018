/*
 * Class: LogarithmicFilter
 * Author: Julian Dominguez-Schatz, Robert Ciborowski
 * Date: 25/01/2018
 * Description: A filter which modifies a value based on a logarithmic function.
 */

package org.usfirst.frc.team854.robot.operatorinterface;

public class LogarithmicFilter implements Filter {
	
	private double maxValueConstant;

	public LogarithmicFilter() {
		this(0);
	}

	public LogarithmicFilter(double maxValue) {
	    maxValueConstant = 1 / Math.log(2);
	    
		// We must divide by the max value, if it is zero, we ignore it.
		if (maxValue != 0) {
			// Precompute a constant that will scale the output up to the max value.
			maxValueConstant *= maxValue;
		}
	}

	@Override
	public double filter(double value) {
		// Ensure the value is in the domain of log_e(v).
		double transformedValue = Math.abs(value) + 1;
		double signOfValue = Math.signum(value);
		
		// Take the log of the value in the base specified in the constructor.
		// This uses the change of base formula: log_a(v) = log_e(v) / log_e(a)
		return (Math.log(transformedValue) * maxValueConstant) * signOfValue;
	}

}
