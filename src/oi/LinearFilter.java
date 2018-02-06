/*
 * Class: LinearFilter
 * Author: Julian Dominguez-Schatz, Robert Ciborowski
 * Date: 25/01/2018
 * Description: A class which modifies a value by an offset, and then adjusts it based
 *              on one of two scaling constants. The scaling constant that is used is
 *              based on if the original value was less or greater than the offset.
 */

package oi;

public class LinearFilter implements Filter {
	
	private double offset;
	private double positiveScalingConstant;
	private double negativeScalingConstant;

	public LinearFilter(double offset) {
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
