/**
 * 
 */
package com.innovanon.rnd.ri.suppliers.special;

import java.util.Random;
import java.util.function.IntSupplier;


/**
 * @author gouldbergstein
 *
 */
public class RangedIntSupplier implements IntSupplier {
	/**
	 * 
	 */
	private Random random;
	/**
	 * 
	 */
	private int offset;
	/**
	 * 
	 */
	private int bound;

	/**
	 * @param random
	 * @param offset
	 * @param bound
	 */
	public RangedIntSupplier(Random random, int offset, int bound) {
		this.random = random;
		this.offset = offset;
		this.bound = bound;
	}

	/**
	 * 
	 * @param min
	 * @param max
	 * @param random
	 */
	public RangedIntSupplier(int min, int max, Random random) {
		this(random, min, max - min + 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.IntSupplier#getAsInt()
	 */
	@Override
	public int getAsInt() {
		return random.nextInt(bound) + offset;
	}
}
