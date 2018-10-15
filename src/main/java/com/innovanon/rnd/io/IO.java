/**
 * 
 */
package com.innovanon.rnd.io;

import java.util.Optional;
import java.util.function.Function;

import com.innovanon.rnd.struct.memo.Memoizer;

/**
 * @author gouldbergstein
 *
 */
public abstract class IO<T, C, R> implements Function<T,Optional<R>>{
	private Function<T, Optional<C>> cache = Memoizer
			.memoize(uax -> inputHelper(uax));

	public Optional<R> apply(T input) {
		return outputHelper (cache.apply(input));
	}
	
	protected abstract Optional<R> outputHelper (Optional<C> input);
	
	protected abstract Optional<C> inputHelper (T input) ;
}
