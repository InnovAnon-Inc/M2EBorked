/**
 * 
 */
package com.innovanon.rnd.ri.suppliers;

import java.util.Random;
import java.util.function.LongSupplier;


/**
 * @author gouldbergstein
 *
 */
public class RandomLongSupplier implements LongSupplier {
	/**
	 * 
	 */
	private Random random;

	/**
	 * @param random
	 */
	public RandomLongSupplier(Random random) {
		this.random = random;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.LongSupplier#getAsLong()
	 */
	@Override
	public long getAsLong() {
		return random.nextLong();
	}
}
