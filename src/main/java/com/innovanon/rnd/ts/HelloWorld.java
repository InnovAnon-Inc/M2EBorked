/**
 * 
 */
package com.innovanon.rnd.ts;

/**
 * @author gouldbergstein
 *
 */
public class HelloWorld {
	/**
	 * 
	 */
	private String hello;
	/**
	 * 
	 */
	private String world;

	/**
	 * @param hello
	 * @param world
	 * @param isProperNoun
	 */
	public HelloWorld(String hello, String world, boolean isProperNoun) {
		this.hello = capitalize(hello);
		this.world = capitalize(world, isProperNoun);
	}

	/**
	 * 
	 * @param word
	 * @param isProperNoun
	 * @return
	 */
	private String capitalize(String word, boolean isProperNoun) {
		if (!isProperNoun)
			return word;
		return capitalize(word);
	}

	/**
	 * 
	 * @param word
	 * @return
	 */
	private String capitalize(String word) {
		if (word == null || word.length() == 0)
			return word;
		char[] chars = word.toCharArray();
		chars[0] = Character.toUpperCase(chars[0]);
		return String.valueOf(chars);
	}

	@Override
	public String toString() {
		return String.format("%s, %s!", hello, world);
	}
}
