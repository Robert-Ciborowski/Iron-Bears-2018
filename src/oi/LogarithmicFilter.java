package oi;

public class LogarithmicFilter extends ValueFilter {
	
	private double logOfMaxValue;

	public LogarithmicFilter(double thresholdValue) {
		this(thresholdValue, -1);
	}

	public LogarithmicFilter(double thresholdValue, double maxValue) {
		super(thresholdValue);

		// Logs of bases less than or equal to one don't exist!
		if (maxValue <= 0) {
			logOfMaxValue = 1;
		} else {
			// Precompute the value of the log of the base for later calculations.
			logOfMaxValue = Math.log(maxValue + 1);
		}
	}

	@Override
	protected double transform(double value) {
		// Ensure the value is in the domain of log_e(v).
		double transformedValue = Math.abs(value) + 1;
		double signOfValue = Math.signum(value);
		
		// Take the log of the value in the base specified in the constructor.
		// This uses the change of base formula: log_a(v) = log_e(v) / log_e(a)
		return (Math.log(transformedValue) / logOfMaxValue) * signOfValue;
	}

}
