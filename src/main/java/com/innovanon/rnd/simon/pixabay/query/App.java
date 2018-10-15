package com.innovanon.rnd.simon.pixabay.query;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

import javax.xml.bind.JAXBException;

/**
 * Hello world!
 *
 */
public enum App {
	/* no instances */ ;

	/**
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws JAXBException
	 */
	public static void main(String... args) throws FileNotFoundException, IOException, JAXBException {
		Random random = new Random();
		//Supplier<String> userAgents = new UserAgentSupplier(random);
		//for (int k = 1; k <= 10; k++)
		//	System.out.println(userAgents.get());

		//String[] array = { "a", "b", "c" };
		// IntSupplier nterm = new RangedIntSupplier(random, 1,array.length - 1);
		//Supplier<Collection<String>> searchTerms = new DiscreteSubsetSupplier<String>(random, 1, array);
		/*
		 * Supplier<String>queries = new Supplier<String>() {
		 * 
		 * 
		 * @Override public String get() { Collection<String>searchTerm =
		 * searchTerms.get(); return String.join("+", searchTerm); }
		 * 
		 * };
		 */
		//Supplier<QueryLang> langs = new EnumSupplier<QueryLang>(QueryLang.class, random);
		
		//Function<Locale,Collection<Word>> words = new WordSupplier(random);
		// TODO how to say 1..5 search terms?
		//IntFunction<Integer> sizes = new BoundedIntFunction(random);
		//Function<Locale, DiscreteSubsetFunction<Word>> f = words.andThen(c -> new DiscreteSubsetFunction<Word>(c, random, sizes));
		
		//IntSupplier sizes = new RangedIntSupplier(1, 3, random);
		//Supplier<Pair<QueryLang,Collection<String>>> queries = new QuerySupplier(random, sizes );
		
		//IntSupplier widths = new RangedIntSupplier(random, 0, 100);
		//IntSupplier heights = new RangedIntSupplier(random, 0, 100);
		//IntSupplier page = new RangedIntSupplier(random, 0, 10);
		//IntSupplier pageAmt = new RangedIntSupplier(random, 3, 200);
		Supplier<URL> urls = new PixabayURLSupplier(random);
		//Supplier<Stream<URL>> urls = new PixabayImageURLSupplier(random);
		long maxSize = 10;
		Stream.generate(urls).limit(maxSize )./*reduce((a, b) -> Stream.concat(a, b)).get().*/forEach(System.out::println);
	}
}
