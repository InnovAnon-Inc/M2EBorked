/**
 * 
 */
package com.innovanon.rnd.struct.ss;

import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * https://github.com/minborg/infinite-sets/blob/master/src/main/java/com/speedment/infinite_sets/internal/ImmutableStreamSetImpl.java
 * 
 * @author gouldbergstein
 *
 * @param <E>
 */
public class ImmutableStreamCollectionImpl<E> implements ImmutableStreamCollection<E> {

	/**
	 * 
	 */
	private Supplier<Stream<E>> supplier;

	/**
	 * @param supplier
	 */
	public ImmutableStreamCollectionImpl(Supplier<Stream<E>> supplier) {
		Objects.requireNonNull(supplier);
		this.supplier = supplier;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.innovanon.rnd.struct.ss.ImmutableStreamCollection#stream()
	 */
	@Override
	public Stream<E> stream() {
		return supplier.get();
	}
}
