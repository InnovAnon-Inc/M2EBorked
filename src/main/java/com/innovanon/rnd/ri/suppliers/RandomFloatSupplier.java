/**
 * 
 */
package com.innovanon.rnd.ri.suppliers;

import java.util.Random;

import com.innovanon.rnd.func.FloatSupplier;

/**
 * @author gouldbergstein
 *
 */
public class RandomFloatSupplier implements FloatSupplier {
	/**
	 * 
	 */
	private Random random;

	/**
	 * @param random
	 */
	public RandomFloatSupplier(Random random) {
		this.random = random;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.innovanon.rnd.func.FloatSupplier#getAsFloat()
	 */
	@Override
	public float getAsFloat() {
		return random.nextFloat();
	}
}
