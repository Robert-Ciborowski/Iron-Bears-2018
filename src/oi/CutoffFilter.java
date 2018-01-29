package oi;

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
