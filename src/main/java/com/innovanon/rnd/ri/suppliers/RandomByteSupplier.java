/**
 * 
 */
package com.innovanon.rnd.ri.suppliers;

import java.util.Random;

import com.innovanon.rnd.func.ByteSupplier;

/**
 * @author gouldbergstein
 *
 */
public class RandomByteSupplier implements ByteSupplier {
	/**
	 * 
	 */
	private Random random;

	/**
	 * @param random
	 */
	public RandomByteSupplier(Random random) {
		this.random = random;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.innovanon.rnd.func.ByteSupplier#getAsByte()
	 */
	@Override
	public byte getAsByte() {
		byte[] bytes = new byte[1];
		random.nextBytes(bytes);
		return bytes[0];
	}
}
