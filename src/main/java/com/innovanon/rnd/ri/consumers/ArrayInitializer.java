/**
 * 
 */
package com.innovanon.rnd.ri.consumers;

import java.lang.reflect.Array;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import com.innovanon.rnd.ri.functions.YInstantiator;

/**
 * @author gouldbergstein
 *
 */
public class ArrayInitializer implements Initializer<Class<?>,Object> {

	/**
	 * 
	 */
	private Function<Class<?>, Object> elements;

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Consumer#accept(java.lang.Object)
	 */
	@Override
	public void accept(Object array) {
		assert test(array);
		Class<?> tClass = array.getClass();
		Class<?> eClass = tClass.getComponentType();
		// if (eClass.isPrimitive())
		int length = Array.getLength(array);
		Stream.iterate(0, x -> x + 1).limit(length).parallel().forEach(new Consumer<Integer>() {
			@Override
			public void accept(Integer t) {
				Object value = elements.apply(eClass);
				Array.set(array, t, value);
			}
		});
		//for (int index = 0; index < length; index++) {
	//		Object value = elements.apply(eClass);
	//		Array.set(array, index, value);
	//	}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Predicate#test(java.lang.Object)
	 */
	@Override
	public boolean test(Object t) {
		if (t == null)
			return false;
		Class<?> tClass = t.getClass();
		return tClass.isArray();
	}

	/* (non-Javadoc)
	 * @see com.innovanon.rnd.yfunc.Delegator#setDelegate(com.innovanon.rnd.yfunc.Delegator)
	 */
	@Override
	public void setDelegate(YInstantiator<Class<?>,Object> delegate) {
		this.elements=delegate;
	}
	
	
}
