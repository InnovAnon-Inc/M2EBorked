/**
 * 
 */
package com.innovanon.rnd.struct.memo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * memoize your I/O and network operations
 * 
 * https://dzone.com/articles/java-8-automatic-memoization
 * 
 * @author gouldbergstein
 *
 */
public class Memoizer<T, U> {

	private final Map<T, U> cache = new ConcurrentHashMap<>();

	private Memoizer() {
	}

	private Function<T, U> doMemoize(final Function<T, U> function) {
		return input -> cache.computeIfAbsent(input, function::apply);
	}

	public static <T, U> Function<T, U> memoize(final Function<T, U> function) {
		return new Memoizer<T, U>().doMemoize(function);
	}
}
