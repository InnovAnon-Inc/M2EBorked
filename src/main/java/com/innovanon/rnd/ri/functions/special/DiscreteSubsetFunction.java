/**
 * 
 */
package com.innovanon.rnd.ri.functions.special;

import java.util.Collection;
import java.util.Random;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.innovanon.rnd.struct.bag.BagImpl;

/**
 * @author gouldbergstein
 *
 */
@Deprecated
public class DiscreteSubsetFunction<T> implements IntFunction<Collection<T>> {
	/**
	 * 
	 */
	private Collection<T> delegates;
	private IntFunction<Integer> sizes;
	//private IntSupplier sizes;

	/**
	 * 
	 * @param random
	 * @param array
	 */
	@SafeVarargs
	public DiscreteSubsetFunction(Random random, IntFunction<Integer> sizes, T... array) {
		// TODO Auto-generated constructor stub
		delegates = new BagImpl<T>(random, array);
		this.sizes = sizes;
	}
	
	@SafeVarargs
	public DiscreteSubsetFunction(Random random, int min,T...array) {
		this(random,new BoundedIntFunction(random) , array);
	}
	
	public DiscreteSubsetFunction(Collection<T> array, Random random, IntFunction<Integer> sizes) {
		// TODO Auto-generated constructor stub
		delegates = new BagImpl<T>(array,random);
		this.sizes = sizes;
	}
	
	public DiscreteSubsetFunction(Collection<T> array,Random random, int min) {
		this(array, random,new BoundedIntFunction(random));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Supplier#get()
	 */
	@Override
	public Collection<T> apply(int value) {
		//int value = delegates.size();
		int maxSize = sizes.apply(value);
		assert value <= delegates.size();
		return StreamSupport.stream(delegates.spliterator(), false).limit(maxSize).collect(Collectors.toList());
	}

	/**
	 * @return
	 * @see java.util.Collection#size()
	 */
	public int size() {
		return delegates.size();
	}
	
	

}
