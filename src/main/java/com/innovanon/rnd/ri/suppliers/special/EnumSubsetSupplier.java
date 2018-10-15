/**
 * 
 */
package com.innovanon.rnd.ri.suppliers.special;

import java.util.Collection;
import java.util.Random;
import java.util.function.Supplier;

/**
 * @author gouldbergstein
 *
 */
public class EnumSubsetSupplier<T > implements Supplier<Collection<T>> {

	private Supplier<Collection<T>> delegate;

	/**
	 * @param random
	 * @param delegate
	 */
	public EnumSubsetSupplier(Class<T> t, Random random) {
		assert t.isEnum();
		this.delegate = new DiscreteSubsetSupplier<T>(random,0, t.getEnumConstants());
	}

	@Override
	public Collection<T> get() {
		return delegate.get();
	}

}
