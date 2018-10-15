/**
 * 
 */
package com.innovanon.rnd.struct.subset;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.innovanon.rnd.struct.ss.SetUtil;

/**
 * @author gouldbergstein
 *
 */
public class SubsetSupplier<T> implements Supplier< Collection< T>> {

	private Iterator<Stream<T>> filters;

	public SubsetSupplier(Collection<T> suppliers, IntSupplier ns, Random random) {
		filters = SetUtil.randomSubsetsSupplier(suppliers, ns, random).map(s -> s.get()).iterator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Supplier#get()
	 */
	@Override
	public Collection<T> get() {
		assert filters.hasNext();
		Stream<T> next = filters.next();
		return next.collect(Collectors.toList());
	}

}
