/**
 * 
 */
package com.innovanon.rnd.ri.functions;

import java.lang.reflect.Array;
import java.util.Random;
import java.util.function.IntSupplier;

import com.innovanon.rnd.ri.suppliers.special.SizeSupplier;

/**
 * @author gouldbergstein
 *
 */
public class ArrayInstantiator implements Instantiator<Class<?>, Object> {

	/**
	 * 
	 */
	private IntSupplier lengths;

	/**
	 * @param lengths
	 */
	public ArrayInstantiator(IntSupplier lengths) {
		this.lengths = lengths;
	}

	/**
	 * 
	 * @param random
	 */
	public ArrayInstantiator(Random random) {
		this(new SizeSupplier(random));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Function#apply(java.lang.Object)
	 */
	@Override
	public Object apply(Class<?> t) {
		assert test(t);
		Class<?> componentType = t.getComponentType();
		int length = lengths.getAsInt();
		Object array = Array.newInstance(componentType, length);
		return array;
	}

	/* (non-Javadoc)
	 * @see java.util.function.Predicate#test(java.lang.Object)
	 */
	@Override
	public boolean test(Class<?> t) {
		return t.isArray();
	}
}
