/**
 * 
 */
package com.innovanon.rnd.util;

import java.lang.reflect.Array;
import java.util.Objects;
import java.util.function.IntFunction;

/**
 * @author gouldbergstein
 *
 */
public class ArrayGenerator<T> implements IntFunction<T> {

	private Class<?> eClass;

	/**
	 * @param tClass
	 */
	public ArrayGenerator(Class<T> tClass) {
		Objects.requireNonNull(tClass);
		eClass = tClass.getComponentType();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.IntFunction#apply(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T apply(int value) {
		Object array = Array.newInstance(eClass, value);
		return (T) array;
	}
}
