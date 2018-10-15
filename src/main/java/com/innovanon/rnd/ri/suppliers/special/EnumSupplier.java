/**
 * 
 */
package com.innovanon.rnd.ri.suppliers.special;

import java.util.Random;
import java.util.function.Supplier;

/**
 * @author gouldbergstein
 *
 */
@Deprecated
public class EnumSupplier<T > implements Supplier<T> {

	private Supplier<T> delegate;

	/**
	 * @param random
	 * @param delegate
	 */
	public EnumSupplier(Class<T> t, Random random) {
		assert t.isEnum();
		this.delegate = new DiscreteSupplier<T>(random, t.getEnumConstants());
	}

	@Override
	public T get() {
		return delegate.get();
	}

}
