/**
 * 
 */
package com.innovanon.rnd.ri.functions;

import java.util.Random;

/**
 * @author gouldbergstein
 *
 */
public class EnumInstantiator implements Instantiator<Class<?>, Object> {

	/**
	 * 
	 */
	private Random random;

	/**
	 * @param random
	 */
	public EnumInstantiator(Random random) {
		this.random = random;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Function#apply(java.lang.Object)
	 */
	@Override
	public Object apply(Class<?> t) {
	assert test(t);
		Object[] instances = t.getEnumConstants();
		int index = random.nextInt(instances.length);
		return instances[index];
	}

	/* (non-Javadoc)
	 * @see java.util.function.Predicate#test(java.lang.Object)
	 */
	@Override
	public boolean test(Class<?> t) {
		return t.isEnum();
	}
}
