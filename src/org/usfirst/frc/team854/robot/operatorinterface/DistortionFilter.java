/*
 * Class: DistortionFilter
 * Author: Julian Dominguez-Schatz, Robert Ciborowski
 * Date: 25/01/2018
 * Description: A class which scales a value within a range based on a given centre point. For example,
 * 				a range of [-1, 1] and a centre of 0.4 would scale all values in [-1, 0.4] to [-1, 0]
 * 				and all values in [0.4, 1] to [0, 1]. This can be used to adjust for calibration issues
 * 				such as the centre position of a joystick being offset from 0.
 */

package org.usfirst.frc.team854.robot.operatorinterface;

public class DistortionFilter implements Filter {
	
	private double offset;
	private double positiveScalingConstant;
	private double negativeScalingConstant;

	public DistortionFilter(double offset) {
		// This value must be less than the maximum input value, in this case 1.
		this.offset = offset;
		
		this.positiveScalingConstant = 1 / (1 - offset);
		this.negativeScalingConstant = 1 / (1 + offset);
	}

	@Override
	public double filter(double value) {
		if (value >= offset) {
			return (value - offset) * positiveScalingConstant;
		}
		
		return (value - offset) * negativeScalingConstant;
		
		// There is a slightly less optimized, but more correct, algorithm. It is as follows:
		// double signedOffset = Math.signum(value) * offset;
		// return (value - offset) / (1 - signedOffset);
	}

}
