/**
 * 
 */
package com.innovanon.rnd.ri.functions.special;

import java.util.Random;
import java.util.function.IntFunction;

/**
 * @author gouldbergstein
 *
 */
@Deprecated
public class BoundedIntFunction implements IntFunction<Integer> {

	private Random random;
	

	/**
	 * @param random
	 */
	public BoundedIntFunction(Random random) {
		this.random = random;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.IntFunction#apply(int)
	 */
	@Override
	public Integer apply(int bound) {
		return random.nextInt(bound);
	}

}
