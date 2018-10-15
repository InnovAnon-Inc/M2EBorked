/**
 * 
 */
package com.innovanon.rnd.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collection;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.innovanon.rnd.at.Todo;

/**
 * @author gouldbergstein
 *
 */
public class WordListIO extends IOStream<Locale,String>{
	
	private static IOStream<Locale,String> instance;
	
	public static IOStream<Locale,String>getInstance(){
		if(instance==null)instance=new WordListIO();return instance;
	}
	
	private WordListIO() {}

	protected  Optional<Stream<String>> helper(Locale lang) {
	Collection<File> files =	WordListsDirIO.getData();
	Predicate<? super File> predicate=f -> f.getName().toLowerCase().contains(lang.getDisplayLanguage().toLowerCase());
	//System.out.println(lang.getDisplayLanguage());
	//files.stream().forEach(System.out::println);
	//files.stream().filter(predicate).forEach(System.out::println);
	return Optional.of(files.stream().filter(predicate).flatMap(f -> getLines (f)));
	}
	
	@Todo("specialized errors")
	private static Stream<String> getLines (File file) {
		assert file.exists();
		assert file.isFile();
		InputStream is;
		try {
			is = new FileInputStream (file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new Error(e);
		}
		Reader r = new InputStreamReader(is);
		BufferedReader reader=null;
		try {reader= new BufferedReader(r);
		Stream<String> stream = reader.lines();
		return stream.collect(Collectors.toList()
				).stream();
		//return stream;
		} finally {
			try {
				if (reader != null)
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new Error(e);
			}
		}
	}
}
