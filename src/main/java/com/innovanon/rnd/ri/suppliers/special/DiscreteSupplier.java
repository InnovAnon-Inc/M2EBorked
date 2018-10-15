/**
 * 
 */
package com.innovanon.rnd.ri.suppliers.special;

import java.util.Iterator;
import java.util.Random;
import java.util.function.Supplier;

import com.innovanon.rnd.struct.bag.BagImpl;
import com.innovanon.rnd.struct.iter.Reiterator;

/**
 * @author gouldbergstein
 *
 */
@Deprecated
public class DiscreteSupplier<T> implements Supplier<T> {
	/**
	 * 
	 */
	private Iterator<T> iter;

	/**
	 * 
	 * @param random
	 * @param array
	 */
	@SafeVarargs
	public DiscreteSupplier(Random random, T... array) {
		// TODO Auto-generated constructor stub
		Iterable<T> iterable = new BagImpl<>(random, array);
		iter = new Reiterator<>(iterable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Supplier#get()
	 */
	@Override
	public T get() {
		return iter.next();
	}

}
