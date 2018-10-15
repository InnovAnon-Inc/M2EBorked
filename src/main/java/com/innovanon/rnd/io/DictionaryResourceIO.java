/**
 * 
 */
package com.innovanon.rnd.io;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Optional;
import java.util.stream.Stream;

import com.innovanon.rnd.at.Todo;

/**
 * @author gouldbergstein
 *
 */
public class DictionaryResourceIO extends IOStream<String, String>{
	
	private static IOStream<String,String> instance;
	
	public static IOStream<String,String> getInstance () {
		if (instance == null)
			instance = new DictionaryResourceIO ();
		return instance;
	}
	
	private DictionaryResourceIO() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @return
	 */
	@Todo("filter out comments")
	protected Optional<Stream<String>> helper(String resource) {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream(resource);
		Reader r = new InputStreamReader(is);
		BufferedReader reader = new BufferedReader(r);
		Stream<String> stream = reader.lines();
//		dictionary=stream;
		// TODO filter out comments
		// List<String> temp = stream.collect(Collectors.toList());
		// dictionary = Collections.unmodifiableList(temp);
		// return dictionary;
		//stream.forEach(System.out::println);
		return Optional.of(stream);
	}
}
