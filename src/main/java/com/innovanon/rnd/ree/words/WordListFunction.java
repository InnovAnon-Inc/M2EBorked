/**
 * 
 */
package com.innovanon.rnd.ree.words;

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
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author gouldbergstein
 *
 */
@Deprecated
public class WordListFunction implements Function<Locale,Stream<Word>> {
	
	private Function<Locale,Collection<File>>files;
	
	
	
	/**
	 * @param files
	 */
	public WordListFunction(Function<Locale, Collection<File>> files) {
		this.files = files;
	}



	/* (non-Javadoc)
	 * @see java.util.function.Function#apply(java.lang.Object)
	 */
	@Override
	public Stream<Word> apply(Locale param) {
		Function<File,Stream<Word>> mapper = new Function<File,Stream<Word>>() {

			@Override
			public Stream<Word> apply(File t) {
				InputStream is;
				try {
					is = new FileInputStream (t);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					throw new Error(e);
				}
				Reader r = new InputStreamReader(is);
				BufferedReader reader=null;
				try {reader= new BufferedReader(r);
				Stream<String> stream = reader.lines();
				return stream.map(s->Word.valueOf (s, param));
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
			
		};
		return files.apply(param).stream().flatMap(mapper );
	}

}
