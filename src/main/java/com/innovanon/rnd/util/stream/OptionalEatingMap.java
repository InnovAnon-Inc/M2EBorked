/**
 * 
 */
package com.innovanon.rnd.util.stream;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author gouldbergstein
 *
 */
@Deprecated
public class OptionalEatingMap<T> implements Function<Optional<T>, Stream<T>> {
	/**
	 * https://stackoverflow.com/questions/24947198/return-empty-element-from-java-8-map-operation
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Function#apply(java.lang.Object)
	 */
	@Override
	public Stream<T> apply(Optional<T> o) {
		return o.isPresent() ? Stream.of(o.get()) : Stream.empty();
	}
}