/**
 * 
 */
package com.innovanon.rnd.ri.consumers;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.StreamSupport;

import com.innovanon.rnd.ri.functions.YInstantiator;

/**
 * @author gouldbergstein
 *
 */
public class ObjectInitializer implements Initializer<Class<?>, Object> {
	/**
	 * 
	 * @param delegate
	 * @return
	 */
	private static Iterable<Initializer<Class<?>, Object>> getDelegates() {
		Initializer<Class<?>, Object> arrays = new ArrayInitializer();
		Set<Initializer<Class<?>, Object>> firstPass = new HashSet<>();
		firstPass.add(arrays);
		return firstPass;
	}

	/**
	 * 
	 */
	private Iterable<Initializer<Class<?>, Object>> delegates;

	/**
	 * @param delegates
	 */
	public ObjectInitializer(Iterable<Initializer<Class<?>, Object>> delegates) {
		this.delegates = delegates;
	}

	/**
	 * 
	 * @param delegate
	 */
	public ObjectInitializer() {
		this(getDelegates());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Predicate#test(java.lang.Object)
	 */
	@Override
	public boolean test(Object t) {
		return StreamSupport.stream(delegates.spliterator(), true).anyMatch(t0->t0.test(t));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Consumer#accept(java.lang.Object)
	 */
	@Override
	public void accept(Object t) {
		Iterator<Initializer<Class<?>, Object>> iterator = StreamSupport.stream(delegates.spliterator(), true)
				.filter(t0->t0.test(t)).iterator();
		if (!iterator.hasNext())
			throw new Error("unsupported type");
		while (iterator.hasNext()) {
			Initializer<Class<?>, Object> instantiator = iterator.next();
			instantiator.accept(t);
			return;
		}
		throw new Error("failed");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.innovanon.rnd.yfunc.Delegator#setDelegate(com.innovanon.rnd.yfunc.
	 * Delegator)
	 */
	@Override
	public void setDelegate(YInstantiator<Class<?>, Object> delegate) {
		StreamSupport.stream(delegates.spliterator(), true).forEach(new Consumer<Initializer<Class<?>, Object>>() {
			@Override
			public void accept(Initializer<Class<?>, Object> t) {
				t.setDelegate(delegate);
			}
		});
	}
}
