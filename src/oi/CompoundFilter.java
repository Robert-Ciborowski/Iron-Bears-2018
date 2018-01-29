package oi;

import java.util.ArrayList;

public class CompoundFilter implements Filter {
	private Filter[] filters;
	
	private CompoundFilter(Filter[] filters) {
		this.filters = filters;
	}
	
	@Override
	public double filter(double value) {
		for (Filter filter : filters) {
			value = filter.filter(value);
		}
		return value;
	}
	
	public static class Builder {
		private ArrayList<Filter> inProgressFilters;
		
		public Builder() {
			inProgressFilters = new ArrayList<>();
		}
		
		public Builder addFilter(Filter filter) {
			inProgressFilters.add(filter);
			return this;
		}
		
		public CompoundFilter build() {
			Filter[] filters = new Filter[inProgressFilters.size()];
			filters = inProgressFilters.toArray(filters);
			return new CompoundFilter(filters);
		}
	}
}
