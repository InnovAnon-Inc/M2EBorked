/**
 * 
 */
package com.innovanon.rnd.ree.words;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author gouldbergstein
 *
 */
public class Word {

	private String string;
	private Locale lang;

	/**
	 * @param string
	 * @param lang
	 */
	public Word(String string, Locale lang) {
		this.string = string;
		this.lang = lang;
	}

	/**
	 * @return the string
	 */
	public String getString() {
		return string;
	}

	/**
	 * @return the lang
	 */
	public Locale getLang() {
		return lang;
	}

	private static Map<Locale, Map<String, Word>> cache = new HashMap<>();

	public static Word valueOf(String s, Locale param) {
		Map<String, Word> sub;
		if (cache.containsKey(param)) {
			sub = cache.remove(param);
			if (sub.containsKey(s)) {
				cache.put(param, sub);
				return sub.get(s);
			}
		} else
			sub = new HashMap<>();
		
		Word w = new Word(s, param);
		sub.put(s, w);
		cache.put(param, sub);
		return w;
	}

}
