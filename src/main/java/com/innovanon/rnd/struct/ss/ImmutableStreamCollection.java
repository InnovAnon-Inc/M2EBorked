/**
 * 
 */
package com.innovanon.rnd.struct.ss;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.innovanon.rnd.at.Todo;

/**
 * https://github.com/minborg/infinite-sets/blob/master/src/main/java/com/speedment/infinite_sets/ImmutableStreamSet.java
 * 
 * @author gouldbergstein
 *
 * @param <E>
 */
@FunctionalInterface
public interface ImmutableStreamCollection<E> extends Collection<E> {

	@Override
	public Stream<E> stream();

	@Todo("gracefully handle big sets > Integer.MAX_VALUE")
	@Override
	default int size() {
		// TODO big sets
		return (int) stream().limit(Integer.MAX_VALUE).count();
	}

	@Override
	default boolean contains(Object o) {
		return stream().anyMatch(e -> Objects.equals(e, o));
	}

	@Override
	default boolean containsAll(Collection<?> c) {
		return (this == c) ? true : c.stream().allMatch(this::contains);
	}

	@Override
	default boolean isEmpty() {
		return !stream().findAny().isPresent();
	}

	@Override
	default <T> T[] toArray(T[] a) {
		return stream().collect(Collectors.toList()).toArray(a);
	}

	@Override
	default Object[] toArray() {
		return stream().toArray();
	}

	@Override
	default Spliterator<E> spliterator() {
		return stream().spliterator();
	}

	@Override
	default Iterator<E> iterator() {
		return stream().iterator();
	}

	@Override
	default Stream<E> parallelStream() {
		return stream().parallel();
	}

	@Override
	default void forEach(Consumer<? super E> action) {
		stream().forEach(action);
	}

	// We are immutable
	@Override
	default boolean removeIf(Predicate<? super E> filter) {
		throw new UnsupportedOperationException();
	}

	@Override
	default void clear() {
		throw new UnsupportedOperationException();
	}

	@Override
	default boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	default boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	default boolean addAll(Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	default boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	default boolean add(E e) {
		throw new UnsupportedOperationException();
	}

	//static <E> ImmutableStreamSet<E> of(Supplier<Stream<E>> supplier) {
	//	return new ImmutableStreamSetImpl<>(supplier);
	//}
}
