/**
 * 
 */
package com.innovanon.rnd.func.cf;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author gouldbergstein
 *
 */
@Deprecated
public class ConsumingFunction<T, R> implements Function<T, R> {
	/**
	 * 
	 */
	private Function<T, R> function;
	/**
	 * 
	 */
	private Consumer<R> consumer;

	/**
	 * @param function
	 * @param consumer
	 */
	public ConsumingFunction(Function<T, R> function, Consumer<R> consumer) {
		this.function = function;
		this.consumer = consumer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Function#apply(java.lang.Object)
	 */
	@Override
	public R apply(T t) {
		R ret = function.apply(t);
		consumer.accept(ret);
		return ret;
	}
}
