package oi;

public abstract class ValueFilter {
	
	private double thresholdValue;
	
	public ValueFilter(double thresholdValue) {
		this.thresholdValue = thresholdValue;
	}
	
	protected abstract double transform(double value);
	
	public double filter(double value) {
		double transformedValue = transform(value);
		
		// If the input value is very close to 0, clamp it to 0.
		// This helps prevent unnecessary input noise.
		if (Math.abs(transformedValue) < thresholdValue) {
			return 0;
		}
		
		return transformedValue;
	}
}
