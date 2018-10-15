package com.innovanon.rnd.struct.subset;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * 
 * @author gouldbergstein
 *
 */
public enum App {
/* no instances */ ; 
	/**
	 * 
	 * @param args command-line arguments
	 */
	public static void main(String... args) {
		Collection<String> suppliers=Arrays.asList("a", "b", "c", "d", "e", "f", "g");
		Random random=new Random();
		IntSupplier ns = () -> random.nextInt(3);
		Supplier<Collection<String>> ss = new SubsetSupplier<String>(suppliers, ns, random);
		Stream.generate(ss).limit (100).forEach(System.out::println);
	}
}
