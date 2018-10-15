/**
 * 
 */
package com.innovanon.rnd.io;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * @author gouldbergstein
 *
 */
public class WordListsDirIO extends IO2<String, Collection<File>> {

	private static IO<String, Collection<File>, Collection<File>> instance;

	public static IO<String, Collection<File>, Collection<File>> getInstance() {
		if (instance == null)
			instance = new WordListsDirIO();
		return instance;
	}

	private WordListsDirIO() {

	}

	public static Collection<File> getData() {
		// String pathname =
		// String.format("%1$svar%1$slib%1$sdictionaries-common%1$swordlist",
		// File.separator);
		String pathname = String.format("%1$susr%1$sshare%1$sdict", File.separator);
		// String pathname = String.format("%1$s%2$sdicts",
		// System.getProperty("user.home"), File.separator);
		return getInstance().apply(pathname).get();
	}

	@Override
	protected Optional<Collection<File>> outputHelper(Optional<Collection<File>> input) {
		if (input.isPresent())
			return Optional.of(Collections.unmodifiableCollection(input.get()));
		return Optional.empty();
	}

	@Override
	protected Optional<Collection<File>> inputHelper(String input) {
		File dir = new File(input);
		assert dir.isDirectory();
		File[] files = dir.listFiles();
		// return Stream.of(files);
		return Optional.of(Arrays.asList(files));
	}
}
