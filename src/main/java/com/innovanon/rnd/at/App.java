package com.innovanon.rnd.at;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public enum App {
	/* no instances */ ;

	/**
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		for (int k = 1; k <= 10; k++)
			try {
				throw TodoException.factory(new Object() {}, Arrays.toString(args));
			} catch (TodoException e) {
				e.printStackTrace();
			}
	}
}
