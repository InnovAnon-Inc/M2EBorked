/**
 * 
 */
package com.innovanon.rnd.ri.suppliers;

import java.util.Random;
import java.util.function.DoubleSupplier;


/**
 * @author gouldbergstein
 *
 */
public class RandomDoubleSupplier implements DoubleSupplier {
	/**
	 * 
	 */
	private Random random;

	/**
	 * @param random
	 */
	public RandomDoubleSupplier(Random random) {
		this.random = random;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.DoubleSupplier#getAsDouble()
	 */
	@Override
	public double getAsDouble() {
		return random.nextDouble();
	}
}
