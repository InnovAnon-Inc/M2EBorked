/**
 * 
 */
package com.innovanon.rnd.struct.iter;

import java.util.Iterator;
import java.util.Objects;

import com.innovanon.rnd.struct.bag.Bag;

/**
 * re-invokes iterable.iterator() once the iterator is exhausted.
 * 
 * @see Bag#iterator()
 * @author gouldbergstein
 */
@Deprecated
public class Reiterator<T> implements Iterator<T> {

	/**
	 * 
	 */
	private Iterable<T> iterable;
	/**
	 * 
	 */
	private Iterator<T> iterator;

	/**
	 * @param iterable
	 * @see Reiterator#iterable
	 */
	public Reiterator(Iterable<T> iterable) {
		Objects.requireNonNull(iterable);
		this.iterable = iterable;
		iterator = iterable.iterator();
	}

	/**
	 * reinitializes the iterator if it is exhausted
	 * 
	 * @see java.util.Iterator#hasNext()
	 * @see Iterable#iterator()
	 */
	private void checkIterator() {
		if (!iterator.hasNext())
			iterator = iterable.iterator();
	}

	/**
	 * @return true unless there's a bug in the iterator or the iterable is empty
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		checkIterator();
		return iterator.hasNext();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#next()
	 */
	@Override
	public T next() {
		checkIterator();
		return iterator.next();
	}
}
