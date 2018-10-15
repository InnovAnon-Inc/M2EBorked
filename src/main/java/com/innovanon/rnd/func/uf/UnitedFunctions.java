/**
 * 
 */
package com.innovanon.rnd.func.uf;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author gouldbergstein
 *
 */
@Deprecated
public class UnitedFunctions<T, R> implements Function<T, R>, Predicate<T> {

	/**
	 * 
	 */
	private Map<Predicate<T>, Function<T, R>> delegates;

	/**
	 * @param delegates
	 */
	public UnitedFunctions(Map<Predicate<T>, Function<T, R>> delegates) {
		this.delegates = delegates;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Function#apply(java.lang.Object)
	 */
	@Override
	public R apply(T t) {
		Stream<Entry<Predicate<T>, Function<T, R>>> stream = delegates.entrySet().stream();
		Predicate<? super Entry<Predicate<T>, Function<T, R>>> predicate = new UFPredicate<T, R>(t);
		stream = stream.filter(predicate);
		Optional<Entry<Predicate<T>, Function<T, R>>> optional = stream.findFirst();
		if (!optional.isPresent())
			throw new Error("unsupported object type");
		Entry<Predicate<T>, Function<T, R>> entry = optional.get();
		assert entry.getKey().test(t);
		return entry.getValue().apply(t);
	}

	/* (non-Javadoc)
	 * @see java.util.function.Predicate#test(java.lang.Object)
	 */
	@Override
	public boolean test(T t) {
		Stream<Entry<Predicate<T>, Function<T, R>>> stream = delegates.entrySet().stream();
		Predicate<? super Entry<Predicate<T>, Function<T, R>>> predicate = new UFPredicate<T, R>(t);
		return stream.anyMatch(predicate);
	}
}
