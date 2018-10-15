/**
 * 
 */
package com.innovanon.rnd.util.stream;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author gouldbergstein
 *
 */
@Deprecated
public enum StreamUtil {
	;
	/**
	 * 
	 * @param stream
	 * @param conv
	 * @param exceptionClassesToCatch
	 * @return
	 */
	public static <A, R> Stream<R> errorEatingConversion(Stream<A> stream, Function<A, R> conv,
			Collection<Class<? extends Throwable>> exceptionClassesToCatch) {
		ErrorEatingOptionalConverter<A, R> eater = new ErrorEatingOptionalConverter<A, R>(conv, exceptionClassesToCatch,
				false);
		Stream<Optional<R>> a = stream.map(eater::apply);
		/*
		Stream<Optional<R>>a = stream.map(new Function<A,Optional<R>>(){

			@Override
			public Optional<R> apply(A t) {
	return eater.apply(t);		}
			
		});
		*/
		Stream<R> b = a.flatMap(new OptionalEatingMap<>());
		return b;
	}

	/**
	 * https://stackoverflow.com/questions/24511052/how-to-convert-an-iterator-to-a-stream#24511534
	 * 
	 * @param sourceIterator
	 * @return
	 */
	public static <T> Stream<T> iteratorToStream(Iterator<T> sourceIterator) {
		Iterable<T> iterable = () -> sourceIterator;
		Stream<T> targetStream = StreamSupport.stream(iterable.spliterator(), false);
		return targetStream;
	}
}
