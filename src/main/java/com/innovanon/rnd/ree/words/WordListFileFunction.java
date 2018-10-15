/**
 * 
 */
package com.innovanon.rnd.ree.words;

import java.io.File;
import java.util.Collection;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.innovanon.rnd.io.WordListsDirIO;

/**
 * @author gouldbergstein
 *
 */
@Deprecated
public class WordListFileFunction implements Function<Locale,Collection<File>> {
	
	/**
	 * 
	 */
	private Collection<File> dictionaries = WordListsDirIO.getData();

	/* (non-Javadoc)
	 * @see java.util.function.Function#apply(java.lang.Object)
	 */
	@Override
	public Collection<File> apply(Locale param) {
		Predicate<File> predicate = new Predicate<File>() {
			@Override
			public boolean test(File t) {
				String fileName = t.getName();
				String displayName = param.getDisplayName();
				return fileName.contains(displayName);
			}
		};
		
		return dictionaries.stream().filter(predicate).collect(Collectors.toList());
	}

}
