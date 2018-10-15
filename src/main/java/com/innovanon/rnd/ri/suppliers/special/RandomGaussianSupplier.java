/**
 * 
 */
package com.innovanon.rnd.ri.suppliers.special;

import java.util.Random;
import java.util.function.DoubleSupplier;


/**
 * @author gouldbergstein
 *
 */
public class RandomGaussianSupplier implements DoubleSupplier {
	/**
	 * 
	 */
	private Random random;

	/**
	 * @param random
	 */
	public RandomGaussianSupplier(Random random) {
		this.random = random;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.DoubleSupplier#getAsDouble()
	 */
	@Override
	public double getAsDouble() {
		return random.nextGaussian();
	}
}
