/*
 * Interface: Filter
 * Author: Julian Dominguez-Schatz, Robert Ciborowski
 * Date: 25/01/2018
 * Description: An interface for any sort of filter, which accepts a value and modifies it.
 */

package oi;

public interface Filter {
	/** Runs the filter on a value.*/
	double filter(double value);
}
