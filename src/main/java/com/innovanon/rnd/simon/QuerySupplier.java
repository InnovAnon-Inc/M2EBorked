/**
 * 
 */
package com.innovanon.rnd.simon;

import java.util.Collection;
import java.util.Locale;
import java.util.Random;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.innovanon.rnd.at.Todo;
import com.innovanon.rnd.ree.words.Word;
import com.innovanon.rnd.ree.words.WordSupplier;
import com.innovanon.rnd.ri.suppliers.special.EnumSupplier;
import com.innovanon.rnd.ri.suppliers.special.RangedIntSupplier;
import com.innovanon.rnd.struct.pair.ImmutablePairImpl;
import com.innovanon.rnd.struct.pair.Pair;

/**
 * @author gouldbergstein
 *
 */
public class QuerySupplier implements Supplier<Pair<QueryLang, Collection<String>>> {

	private Supplier<QueryLang> langs;
	private Function<Locale, Stream<Word>> words;

	public QuerySupplier(Random random, IntSupplier ns) {
		langs = new EnumSupplier<QueryLang>(QueryLang.class, random);
		words = new WordSupplier(random, ns);
		//System.out.println("A");
	}
	
	public QuerySupplier(Random random) {
		this (random, new RangedIntSupplier(1, 3, random));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Supplier#get()
	 */
	@Override
	@Todo("possible failure to terminate")
	public Pair<QueryLang, Collection<String>> get() {
		// TODO this loop is scary. langs needs to be Supplier<Stream>. do langs.get().iterator(). return the first working value. if no return, then shit the bed
		QueryLang ql;
		do {
			ql= langs.get();
		//System.out.println(ql);
		Locale l = ql.getLocale();
		//System.out.println(l);
		Stream<Word> words = this.words.apply(l);
		if (words == null)continue;
		//System.out.println("QuerySupplier.get()");
		Collection<String> c = words.map(w -> w.getString()).collect(Collectors.toList());
		//System.out.println(c);
		return new ImmutablePairImpl<QueryLang, Collection<String>>(ql, c);
		} while (true);
	}
}
