/**
 * 
 */
package com.innovanon.rnd.util;

import java.util.function.Predicate;

/**
 * @author gouldbergstein
 *
 */
public class IsInstancePredicate<T> implements Predicate<Class<? extends T>> {

	private T obj;

	/**
	 * @param obj
	 */
	public IsInstancePredicate(T obj) {
		this.obj = obj;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Predicate#test(java.lang.Object)
	 */
	@Override
	public boolean test(Class<?extends T> t) {
		return t.isInstance(obj);
	}
}
