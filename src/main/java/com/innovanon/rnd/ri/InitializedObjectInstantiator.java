/**
 * 
 */
package com.innovanon.rnd.ri;

import java.util.Random;

import com.innovanon.rnd.ri.consumers.Initializer;
import com.innovanon.rnd.ri.consumers.ObjectInitializer;
import com.innovanon.rnd.ri.functions.ObjectInstantiator;
import com.innovanon.rnd.ri.functions.YInstantiator;

/**
 * @author gouldbergstein
 *
 */
public class InitializedObjectInstantiator implements YInstantiator<Class<?>, Object> {

	/**
	 * 
	 */
	private YInstantiator<Class<?>, Object> function;
	/**
	 * 
	 */
	private Initializer<Class<?>,Object> consumer;

	/**
	 * @param function
	 * @param consumer
	 */
	public InitializedObjectInstantiator(YInstantiator<Class<?>, Object> function, Initializer<Class<?>,Object> consumer) {
		this.function = function;
		this.consumer = consumer;
	}

	/**
	 * 
	 * @param function
	 */
	public InitializedObjectInstantiator(YInstantiator<Class<?>, Object> function) {
		this(function, new ObjectInitializer());
		this.consumer.setDelegate(this);
	}

	/**
	 * 
	 * @param random
	 */
	public InitializedObjectInstantiator(Random random) {
		this(new ObjectInstantiator(random));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Function#apply(java.lang.Object)
	 */
	@Override
	public Object apply(Class<?> t) {
		assert test(t);
		Object ret = function.apply(t);
		assert consumer.test(ret);
		consumer.accept(ret);
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Predicate#test(java.lang.Object)
	 */
	@Override
	public boolean test(Class<?> t) {
		return function.test(t);
	}

	/* (non-Javadoc)
	 * @see com.innovanon.rnd.yfunc.YFunction#setDelegate(com.innovanon.rnd.yfunc.YFunction)
	 */
	@Override
	public void setDelegate(YInstantiator<Class<?>, Object> delegate) {
	function.setDelegate (delegate);
	consumer.setDelegate (delegate);
	}
}
