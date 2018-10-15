package com.innovanon.rnd.ree.dict;

import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public enum App {
	/* no instances */ ;

	/**
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		Random random = new Random();
		Supplier<Optional<String>> dict = new DictionarySupplier(random);
		long maxSize = 10;
		Stream.generate(dict).filter(o->o.isPresent()).limit(maxSize).forEach(System.out::println);
	}
}
