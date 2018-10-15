/**
 * 
 */
package com.innovanon.rnd.ri.suppliers.special;

import java.util.Collection;
import java.util.Random;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.innovanon.rnd.struct.bag.BagImpl;

/**
 * @author gouldbergstein
 *
 */
public class DiscreteSubsetSupplier<T> implements Supplier<Collection<T>> {
	/**
	 * 
	 */
	private Collection<T> delegates;
	//private IntFunction<Integer> sizes;
	private IntSupplier sizes;

	/**
	 * 
	 * @param random
	 * @param array
	 */
	@SafeVarargs
	public DiscreteSubsetSupplier(Random random, IntSupplier sizes, T... array) {
		// TODO Auto-generated constructor stub
		delegates = new BagImpl<T>(random, array);
		this.sizes = sizes;
	}
	
	@SafeVarargs
	public DiscreteSubsetSupplier(Random random, int min,T...array) {
		this(random,new RangedIntSupplier(min, array.length + 1,random) , array);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Supplier#get()
	 */
	@Override
	public Collection<T> get() {
		//int value = delegates.size();
		//int maxSize = sizes.apply(value);
		int maxSize = sizes.getAsInt();
		return StreamSupport.stream(delegates.spliterator(), false).limit(maxSize).collect(Collectors.toList());
	}

}
