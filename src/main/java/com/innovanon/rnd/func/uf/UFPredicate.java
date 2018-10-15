/**
 * 
 */
package com.innovanon.rnd.func.uf;

import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 
 * @author gouldbergstein
 *
 * @param <T>
 * @param <R>
 */
@Deprecated
public class UFPredicate<T, R> implements Predicate<Entry<Predicate<T>, Function<T, R>>> {
	/**
	 * 
	 */
	private final T t;

	/**
	 * 
	 * @param t
	 */
	public UFPredicate(T t) {
		this.t = t;
	}

	/**
	 * 
	 */
	@Override
	public boolean test(Entry<Predicate<T>, Function<T, R>> entry) {
		Predicate<T> p = entry.getKey();
		return p.test(t);
	}
}