/**
 * 
 */
package com.innovanon.rnd.ts;

/**
 * @author gouldbergstein
 *
 */
public class Boob {

	/**
	 * 
	 */
	private static Boob instance = new Boob ("(",".",")");
	
	/**
	 * 
	 * @return
	 */
	public static Boob getBoob () {
		return instance;
	}
	
	/**
	 * 
	 */
	private String left;
	/**
	 * 
	 */
	private String nipple;
	/**
	 * 
	 */
	private String right;

	/**
	 * 
	 * @param left
	 * @param nipple
	 * @param right
	 */
	public Boob (String left, String nipple, String right) {
		this.left = left;
		this.nipple=nipple;
		this.right=right;
	}

	@Override
	public String toString() {
		return String.format("%s%s%s", left, nipple, right);
	}
}
