/*
 * Class: CompoundFilter
 * Author: Julian Dominguez-Schatz, Robert Ciborowski
 * Date: 25/01/2018
 * Description: A class which combines multiple filters, using the builder pattern. It is used
 *              to filter an input using as many filters as your heart desires.
 */

package oi;

import java.util.ArrayList;

public class CompoundFilter implements Filter {
	private Filter[] filters;
	
	private CompoundFilter(Filter[] filters) {
		this.filters = filters;
	}
	
	@Override
	/** Uses all filters of the compound filter on the value.*/
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
