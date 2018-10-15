/**
 * 
 */
package com.innovanon.rnd.ri.suppliers;

import java.util.Random;

import com.innovanon.rnd.func.ShortSupplier;
import com.innovanon.rnd.ri.suppliers.special.RangedIntSupplier;

/**
 * @author gouldbergstein
 *
 */
public class RandomShortSupplier extends RangedIntSupplier implements ShortSupplier {

	/**
	 * @param random
	 */
	public RandomShortSupplier(Random random) {
		super(Short.MIN_VALUE, Short.MAX_VALUE,random);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.innovanon.rnd.func.ShortSupplier#getAsShort()
	 */
	@Override
	public short getAsShort() {
		int temp = super.getAsInt();
		assert Short.MIN_VALUE <= temp;
		assert Short.MAX_VALUE >= temp;
		return (short)temp;
	}
}
