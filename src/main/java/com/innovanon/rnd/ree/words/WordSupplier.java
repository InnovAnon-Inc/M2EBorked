/**
 * 
 */
package com.innovanon.rnd.ree.words;

import java.util.Iterator;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.innovanon.rnd.io.WordListIO;
import com.innovanon.rnd.ri.functions.Instantiator;
import com.innovanon.rnd.struct.ss.SetUtil;

/**
 * @author gouldbergstein
 *
 */
public class WordSupplier implements Instantiator<Locale, Stream<Word>> {

	// TODO use SubsetSupplier
	// supplier of random subsets
	// supplier of random strings
	//private Function<Locale, Iterator<Supplier<Stream<String>>>> subsets;
	private Function<Locale, Iterator<Supplier<Stream<String>>>> subsets;

	/**
	 * 
	 * @param random
	 */
	public WordSupplier(Random random, IntSupplier ns) {
		Function<Locale, Optional<Stream<String>>> words = lang -> WordListIO.getInstance().apply(lang);
		subsets = l -> {
			if (l == null)
				return null;
			Optional<Stream<String>> sss = words.apply(l);
			Stream<Supplier<Stream<String>>> temp;
			if (sss.isPresent())
			temp = SetUtil
					.randomSubsetsSupplier(sss.get().collect(Collectors.toList()), ns, random);
			else temp = null;
			if (temp == null)
				return null;
			return temp.iterator();
		};

		// System.out.println("B");
		// Function<Locale,Supplier<Supplier<Stream<Supplier<Stream<String>>>>>>
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Predicate#test(java.lang.Object)
	 */
	@Override
	public boolean test(Locale param) {
		// System.out.println("C");
		Iterator<Supplier<Stream<String>>> i = subsets.apply(param);
		return i != null && i.hasNext();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Function#apply(java.lang.Object)
	 */
	@Override
	public Stream<Word> apply(Locale param) {
		// System.out.println("D");
		// System.out.println("apply 0: " + param);
		Iterator<Supplier<Stream<String>>> i = subsets.apply(param);
		// System.out.println("E");
		// System.out.println("apply 1: " + param);
		// TODO
		if (i == null || !i.hasNext())
			return null;
		// if (! i.hasNext())throw new Error();
		// System.out.println("F");
		Supplier<Stream<String>> s = i.next();
		// System.out.println("apply 2: " + param);
		// s.get().forEach(System.out::println);
		// System.out.println("F");
		Stream<Word> ret = s.get().map(S -> Word.valueOf(S, param));
		// ret.forEach(System.out::println);
		return ret;
	}
}
