package com.innovanon.rnd.ree.re;

import java.io.IOException;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.innovanon.rnd.at.Todo;
import com.innovanon.rnd.ri.suppliers.special.SizeSupplier;
import com.mifmif.common.regex.Generex;

/**
 * Hello world!
 *
 */
public class App implements Consumer<String> {
	/**
	 * 
	 */
	private Random random;

	/**
	 * @param random
	 */
	public App(Random random) {
		this.random = random;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Consumer#accept(java.lang.Object)
	 */
	@Override
	public void accept(String regex) {
		System.out.printf("testing: %s%n", regex);
		Generex generex = new Generex(regex, random);
		int minLength = 0;
		IntSupplier sizes = new SizeSupplier(random);
		Supplier<String> dict = new RegexStringSupplier(generex, minLength, sizes);
		/*
		Supplier<String> dick = new Supplier<String>() {

			@Override
			public String get() {
				// TODO this won't work well on a finite generex
				while (true)
				try {
					return dict.get();
				} catch (StackOverflowError e) {
					//System.err.println(e);
				}
				}
			};*/
		long maxSize = 10;
		try {
		Stream.generate(dict).limit(maxSize).forEach(System.out::println);
		} catch (StackOverflowError e) {
			System.err.println(e);
		}
	}

	/**
	 * 
	 * @param args
	 * @throws IOException
	 */
	@Todo("stop eating errors")
	public static void main(String... args) throws IOException {
		Random random = new Random();
		try {
		RegexUtil.getDictionary().stream().forEach(new App(random));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
}
