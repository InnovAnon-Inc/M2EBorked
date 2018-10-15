/**
 * 
 */
package com.innovanon.rnd.ri.suppliers.special;

import java.util.Random;
import java.util.function.IntSupplier;

// TODO we don't want this fed to a stream that ends up being replayed
/**
 * @author gouldbergstein
 *
 */
public class SizeSupplier implements IntSupplier {
	/**
	 * 
	 */
	private static final double DEFAULT_EPSILON = .001;

	/**
	 * 
	 */
	private Random random;
	/**
	 * 
	 */
	private double epsilon;

	/**
	 * @param random
	 * @param epsilon
	 */
	public SizeSupplier(Random random, double epsilon) {
		this.random = random;
		this.epsilon = epsilon;
	}

	/**
	 * 
	 * @param random
	 */
	public SizeSupplier(Random random) {
		this(random, DEFAULT_EPSILON);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.IntSupplier#getAsInt()
	 */
	@Override
	public int getAsInt() {
		Runtime runtime = Runtime.getRuntime();
		long totalMemory = runtime.totalMemory();
		long freeMemory = runtime.freeMemory();
		double precision = (double) (totalMemory - freeMemory) / totalMemory;
		precision *= epsilon;
		// TODO analytics
		int memory = (int) (precision * freeMemory);
		return random.nextInt(memory);
	}
}
