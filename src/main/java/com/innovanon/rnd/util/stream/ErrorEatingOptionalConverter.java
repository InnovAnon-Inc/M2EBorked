/**
 * 
 */
package com.innovanon.rnd.util.stream;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author gouldbergstein
 */
@Deprecated
public class ErrorEatingOptionalConverter<A, R> implements Function<A, Optional<R>> {
	//private Function<A, R> nameToClassConverter;
	//private Collection<Class<? extends Throwable>> tastyExceptions;
	//private List<Pair<A, ? extends Throwable>> errors;
	private ErrorEatingFunction<A,R> eater;

	/**
	 * @param nameToClassConverter
	 * @param exceptionClassesToCatch
	 * @param rememberErrors
	 */
	public ErrorEatingOptionalConverter(Function<A, R> nameToClassConverter,
			Collection<Class<? extends Throwable>> exceptionClassesToCatch, boolean rememberErrors) {
		//this.nameToClassConverter = nameToClassConverter;
		//tastyExceptions = exceptionClassesToCatch;
		//if (rememberErrors)
		//	errors = new ArrayList<>();
		eater = new ErrorEatingFunction<A,R>(exceptionClassesToCatch, rememberErrors, nameToClassConverter::apply);
	}

	/**
	 * @throws Error
	 * @see java.lang.ClassNotFoundException
	 * @see Class#forName(String)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Function#apply(java.lang.Object)
	 */
	@Override
	public Optional<R> apply(A className) {
		// Objects.requireNonNull(className);
		return eater.apply(className);
	}
}
