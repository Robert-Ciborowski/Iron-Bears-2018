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
