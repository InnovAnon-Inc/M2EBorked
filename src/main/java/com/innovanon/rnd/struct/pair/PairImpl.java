/**
 * Om
 * Krim Krim Krim Hum Hum Hrim Hrim Daksine Kalike
 * Krim Krim Krim Hum Hum Hrim Hrim Svaha
 * 
 * InnovAnon, Inc. Proprietary
 * 
 * NOTICE:
 * All reproductions, total or partial, of this work must retain this notice.
 * 
 * Possession or use of this technology implies consent to our Terms of Service.
 * 
 * Owner and management NOT responsible for injury or death resulting from the use of this equipment.
 * 
 * If any clause in this notice is invalid or unenforceable in your jurisdiction, that does NOT necessarily invalidate any other clause in this notice.
 * 
 * Om
 * Krim Krim Krim Hum Hum Hrim Hrim Daksine Kalike
 * Krim Krim Krim Hum Hum Hrim Hrim Svaha
 */
package com.innovanon.rnd.struct.pair;

/**
 * @author gouldbergstein
 * @param <CAR> Contents of the Address part of Register number
 * @param <CDR> Contents of the Decrement part of Register number
 */
public abstract class PairImpl<CAR, CDR> implements Pair<CAR, CDR> {
	/**
	 * @see #toString(Class)
	 * @see Object#getClass()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return toString(this.getClass());
	}
	
	/**
	 * @param subtype the type of the invoking object
	 * @return a formated String for use with {@link #toString()}
	 */
	protected String toString(Class<?> subtype) {
		return String.format("%s [car=%s, cdr=%s]", subtype.getSimpleName(),getCar(),getCdr());
	}
}
