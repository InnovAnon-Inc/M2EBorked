/**
 * 
 */
package com.innovanon.rnd.ts;

import com.innovanon.rnd.util.StringUtil;

/**
 * @author gouldbergstein
 *
 */
public class Dick {

	private static final String shaft = "=";
	private static final String glans = ">";
	
	/**
	 * the length of the dick, measured in characters
	 */
	private int length;

	/**
	 * @param length
	 */
	public Dick(int length) {
		this.length = length;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("%s%s", StringUtil.repeatv1 (shaft, length), glans);
	}		
}
