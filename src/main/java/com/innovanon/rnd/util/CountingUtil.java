/**
 * 
 */
package com.innovanon.rnd.util;

import java.util.stream.Stream;

/**
 * @author gouldbergstein
 *
 */
public enum CountingUtil {
	/* no instances */ ;
	
	/**
	 * like this will help you with off-by-one errors
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int inclusiveRange(int min, int max) {
		return exclusiveRange(min, max) + 1;
	}

	/**
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int exclusiveRange(int min, int max) {
		return max - min;
	}

	/**
	 * nPr = factorial(n) / factorial(n-r)
	 * 
	 * @param n
	 * @param r
	 * @return
	 */
	public static int nPr(int n, int r) {
		return Stream.iterate(r, x->x+1).limit(inclusiveRange(r, n)).reduce((x, y) -> x * y).get();
	}

	/**
	 * nCr = factorial(n)/(factorial(n-r) * factorial(r))
	 * 
	 * @param n
	 * @param r
	 * @return
	 */
	public static int nCr(int n, int r) {
		return nPr (n, r) * factorial (r);
	}
	
	/**
	 * 
	 * @param n
	 * @return
	 */
	public static int factorial (int n) {
		return Stream.iterate(1, x->x+1).limit(n).reduce((x, y) -> x * y).get();	
	}

	public static int nCrs(int n) {
		return Stream.iterate(1, x->x+1).limit(n).map(x -> nCr(n, x)).reduce((a,b)->a+b).get();
	}
}
