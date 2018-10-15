/**
 * 
 */
package com.innovanon.rnd.ri.suppliers;

import java.util.Random;

import com.innovanon.rnd.func.CharSupplier;
import com.innovanon.rnd.ri.suppliers.special.RangedIntSupplier;

/**
 * @author gouldbergstein
 *
 */
public class RandomCharSupplier extends RangedIntSupplier implements CharSupplier {

	/**
	 * @param random
	 */
	public RandomCharSupplier(Random random) {
		super(Character.MIN_VALUE, Character.MAX_VALUE, random);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.innovanon.rnd.func.CharSupplier#getAsChar()
	 */
	@Override
	public char getAsChar() {
		int temp = super.getAsInt();
		assert Character.MIN_VALUE <= temp;
		assert Character.MAX_VALUE >= temp;
		return (char) temp;
	}
}
