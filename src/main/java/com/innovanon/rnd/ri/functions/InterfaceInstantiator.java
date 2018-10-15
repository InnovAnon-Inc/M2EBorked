/**
 * 
 */
package com.innovanon.rnd.ri.functions;

import java.util.Set;

import com.innovanon.rnd.refl.Refl;

/**
 * @author gouldbergstein
 *
 */
public class InterfaceInstantiator implements Instantiator<Class<?>, Object> {

	/**
	 * 
	 */
	private Instantiator<Class<?>, Object> delegate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Function#apply(java.lang.Object)
	 */
	@Override
	public Object apply(Class<?> t) {
		assert test(t);
		Set<? extends Class<?>> subtypes = Refl.INSTANCE.getReflections().getSubTypesOf(t);
		// TODO randomly iterate subtypes
		for (Class<?> subtype : subtypes)
			return delegate.apply(subtype);
		throw new Error("no compatible subtypes");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Predicate#test(java.lang.Object)
	 */
	@Override
	public boolean test(Class<?> t) {
		if (!t.isInterface())
			return false;
		// return delegate.test (t);
		return true;
	}
}
