/**
 * 
 */
package com.innovanon.rnd.ree.re;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.innovanon.rnd.at.Todo;
import com.innovanon.rnd.util.StringUtil;

/**
 * @author gouldbergstein
 *
 */
@Todo("convert to IO")
public enum RegexUtil {
	/* no instances */ ;

	/**
	 * 
	 */
	private static Collection<String> dictionary;

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public static Collection<String> getDictionary() throws IOException {
		if (dictionary != null)
			return dictionary;
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream(StringUtil.packageToPath(RegexUtil.class.getPackage())+"regex");
		Reader r = new InputStreamReader(is);
		BufferedReader reader = new BufferedReader(r);
		String skip = null;
		//Pattern p = Pattern.compile("# *\\(\\S+\\)");
		Pattern p = Pattern.compile("# *.*");
		Collection<String> strings = new ArrayList<>();
		do {
			String description = skip == null ? reader.readLine() : skip;
			if (description == null)
				break;
			Matcher m = p.matcher(description);
			boolean find = m.matches();
			if (find) {
				//description = m.replaceFirst("$&");
			}
			String regex = find ? reader.readLine() : description;
			if (regex == null) break;
			strings.add(regex);
			skip = reader.readLine();
			if (skip == null)break;
			if (skip.trim().isEmpty()) {
				skip = null;
				continue;
			}
		} while (true);
		//
		dictionary = Collections.unmodifiableCollection(strings);
		return dictionary;
	}
}
