/**
 * 
 */
package com.innovanon.rnd.util.stream;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

import com.innovanon.rnd.struct.pair.ImmutablePairImpl;
import com.innovanon.rnd.struct.pair.Pair;

/**
 * @author gouldbergstein
 *
 */
@Deprecated
public class ErrorEatingFunction<A, R> extends ErrorEater<R, Pair<A, Throwable>> implements Function<A, Optional<R>> {

	private Function<A, R> supplier;

	/**
	 * @param exceptionClassesToCatch
	 * @param rememberErrors
	 */
	public ErrorEatingFunction(Collection<Class<? extends Throwable>> exceptionClassesToCatch, boolean rememberErrors,
			Function<A, R> supplier) {
		super(exceptionClassesToCatch, rememberErrors);
		this.supplier = supplier;
	}

	@Override
	public Optional<R> apply(A arg) {
		return super.apply(() -> supplier.apply(arg), e -> new ImmutablePairImpl<>(arg, e));
	}
}
