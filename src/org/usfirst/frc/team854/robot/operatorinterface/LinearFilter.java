/*
 * Class: LinearFilter
 * Author: Julian Dominguez-Schatz, Robert Ciborowski
 * Date: 25/01/2018
 * Description: This literally just applies a linear function to the input.
 */

package org.usfirst.frc.team854.robot.operatorinterface;

public class LinearFilter implements Filter {
	
	private double m;
	private double b;

	public LinearFilter(double m) {
		this(m, 0);
	}

	public LinearFilter(double m, double b) {
		this.m = m;
		this.b = b;
	}

	@Override
	public double filter(double value) {
		return (value * m) + b;
	}

}
