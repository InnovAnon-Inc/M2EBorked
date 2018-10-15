/**
 * 
 */
package com.innovanon.rnd.ri.functions;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.StreamSupport;

/**
 * @author gouldbergstein
 *
 */
public class ObjectInstantiator implements YInstantiator<Class<?>, Object> {
	/**
	 * 
	 * @param random
	 * @param parent should initialize instantiate
	 * @return
	 */
	private static Iterable<Instantiator<Class<?>, Object>> getDelegates(Random random) {
		Instantiator<Class<?>, Object> arrays = new ArrayInstantiator(random);
		Instantiator<Class<?>, Object> primitives = new PrimitiveInstantiator(random);
		Instantiator<Class<?>, Object> enums = new EnumInstantiator(random);
		Set<Instantiator<Class<?>, Object>> firstPass = new HashSet<>();
		firstPass.add(arrays);
		firstPass.add(primitives);
		firstPass.add(enums);
		return firstPass;
	}

	private static Iterable<YInstantiator<Class<?>, Object>> getYDelegates(Random random) {
		// TODO interfaces
		// TODO constructors
		// TODO factory methods
		Set<YInstantiator<Class<?>, Object>> firstPass = new HashSet<>();
		return firstPass;
	}

	/**
	 * 
	 */
	// private List<Set<Instantiator<Class<?>,Object>>> delegates;
	private Iterable<Instantiator<Class<?>, Object>> delegates;
	private Iterable<YInstantiator<Class<?>, Object>> ydelegates;

	/**
	 * @param delegates
	 * @param ydelegates
	 */
	public ObjectInstantiator(Iterable<Instantiator<Class<?>, Object>> delegates,
			Iterable<YInstantiator<Class<?>, Object>> ydelegates) {
		this.delegates = delegates;
		this.ydelegates = ydelegates;
	}

	/**
	 * 
	 * @param random
	 */
	public ObjectInstantiator(Random random) {
		this(getDelegates(random), getYDelegates(random));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Function#apply(java.lang.Object)
	 */
	@Override
	public Object apply(Class<?> t) {
		Iterator<Instantiator<Class<?>, Object>> iterator = StreamSupport.stream(delegates.spliterator(), true)
				.filter(t0->t0.test(t)).iterator();
		if (!iterator.hasNext())
			throw new Error("unsupported type");
		while (iterator.hasNext()) {
			Instantiator<Class<?>, Object> instantiator = iterator.next();
			return instantiator.apply(t);
		}

		Iterator<YInstantiator<Class<?>, Object>> yiterator = StreamSupport.stream(ydelegates.spliterator(), true)
				.filter(t0->t0.test(t)).iterator();
		if (!yiterator.hasNext())
			throw new Error("unsupported type");
		while (yiterator.hasNext()) {
			Instantiator<Class<?>, Object> instantiator = yiterator.next();
			return instantiator.apply(t);
		}
		throw new Error("failed");

		// if (t.isAnnotation())
		// if (t.isArray())
		// return arrays.apply(t);
		// if (t.isPrimitive())
		// return primitives.apply(t);
		// if (t.isEnum())
		// return enums.apply(t);
		// if (t.isInterface()) {
		// get random subclass

		// }
		//
		// if (t.isAnonymousClass() || t.isLocalClass()) {
		// Method m = t.getEnclosingMethod();
		// Constructor<?> c = t.getEnclosingConstructor();
		// Class<?> cls = t.getEnclosingClass();
		// }
		//
		// if (t.isMemberClass()) {
		// t.getDeclaringClass();
		// }
		// if (t.isSynthetic())

		// TODO generic declarations

		// goes on top of this one... don't wanna invoke a method with an uninit'd
		// collection/array
		// TODO factory methods
		// TODO constructors

		// specialized instantiator... goes on top of this one
		// if (t.isAssignableFrom(cls))
		// even more specialized instantiator... idk what to do with it
		// if (t.isAnnotationPresent(annotationClass))
		// TODO
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Predicate#test(java.lang.Object)
	 */
	@Override
	public boolean test(Class<?> t) {
		if (StreamSupport.stream(delegates.spliterator(), true).anyMatch(t0->t0.test(t)))
			return true;
		return StreamSupport.stream(ydelegates.spliterator(), true).anyMatch(t0->t0.test(t));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.innovanon.rnd.yfunc.YFunction#setDelegate(com.innovanon.rnd.yfunc.
	 * YFunction)
	 */
	@Override
	public void setDelegate(YInstantiator<Class<?>, Object> delegate) {
		StreamSupport.stream(ydelegates.spliterator(), true).forEach(new Consumer<YInstantiator<Class<?>, Object>>() {
			@Override
			public void accept(YInstantiator<Class<?>, Object> t) {
				t.setDelegate(delegate);
			}
		});
	}
}
