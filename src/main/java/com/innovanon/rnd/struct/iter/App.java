/**
 * Om
 * Krim Krim Krim Hum Hum Hrim Hrim Daksine Kalike
 * Krim Krim Krim Hum Hum Hrim Hrim Svaha
 * 
 * InnovAnon, Inc. Proprietary
 * 
 * NOTICE:
 * All reproductions, total or partial, of this work must retain this notice.
 * 
 * Possession or use of this technology implies consent to our Terms of Service.
 * 
 * Owner and management NOT responsible for injury or death resulting from the use of this equipment.
 * 
 * If any clause in this notice is invalid or unenforceable in your jurisdiction, that does NOT necessarily invalidate any other clause in this notice.
 * 
 * Om
 * Krim Krim Krim Hum Hum Hrim Hrim Daksine Kalike
 * Krim Krim Krim Hum Hum Hrim Hrim Svaha
 */
package com.innovanon.rnd.struct.iter;

import java.util.Iterator;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.innovanon.rnd.struct.bag.Bag;
import com.innovanon.rnd.struct.bag.BagImpl;

/**
 * The primary application, currently: Hello world!
 */
public enum App {
	/* no instances */;
	/**
	 * @param args command line arguments
	 */
	@SuppressWarnings("deprecation")
	public static void main(String... args) {
		Random random = new Random();
		String[] copy = { "abc", "def", "ghi", "jkl", "mno" };
		Bag<String> bag = new BagImpl<String>(random, copy);
		Iterator<String> iter = new Reiterator<>(bag);
		Supplier<String> s = new Supplier<String>() {

			@Override
			public String get() {
				return iter.next();
			}

		};
		long maxSize = bag.size() * 2;
		Stream.generate(s).limit(maxSize).forEach(System.out::println);
	}
}
