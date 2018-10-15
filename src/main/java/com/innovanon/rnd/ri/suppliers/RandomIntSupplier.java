/**
 * 
 */
package com.innovanon.rnd.ri.suppliers;

import java.util.Random;
import java.util.function.IntSupplier;


/**
 * @author gouldbergstein
 *
 */
public class RandomIntSupplier implements IntSupplier {
	/**
	 * 
	 */
	private Random random;

	/**
	 * @param random
	 */
	public RandomIntSupplier(Random random) {
		this.random = random;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.IntSupplier#getAsInt()
	 */
	@Override
	public int getAsInt() {
		return random.nextInt();
	}
}
