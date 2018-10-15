/**
 * 
 */
package com.innovanon.rnd.struct.bag;

import java.util.Collection;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author gouldbergstein
 *
 */
public enum BagUtil {
	/* no instances */ ;

	/**
	 * 
	 * @param s
	 * @param random
	 * @return
	 */
	public static <E> Stream<E> getRandomStream(Stream<E> s, Random random) {
		Supplier<Stream<E>> ss = BagUtil.getRandomStreamSupplier(s, random);
		//return StreamUtil.concat(Stream.generate(ss));
		return ss.get();
	}

	

	public static <E> Supplier<Stream<E>> getRandomStreamSupplier(Stream<E> s, Random random) {
		Collection<E> copy = s.collect(Collectors.toList());
		Collection<E> randomCopy = new BagImpl<>(copy, random);
		return () -> randomCopy.stream();
	}
}
