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
public class BoundedIntFunction2 implements IntFunction<Integer> {

	private Random random;
	private int offset;
	private int bound;
	

	
	
	/**
	 * @param random
	 * @param offset
	 * @param bound
	 */
	public BoundedIntFunction2(Random random, int offset, int bound) {
		this.random = random;
		this.offset = offset;
		this.bound = bound;
	}




	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.IntFunction#apply(int)
	 */
	@Override
	public Integer apply(int bound) {
		return offset + random.nextInt(Math.min(this.bound, bound - offset));
	}

}
