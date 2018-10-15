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
 * <cite>wikipedia:</cite>
 * <blockquote cite="https://en.wikipedia.org/wiki/CAR_and_CDR">A cons cell is composed of two pointers; the #CAR [{@link #getCar()}] operation
 * extracts the first pointer, and the #CDR [{@link #getCdr()}] operation extracts the
 * second.</blockquote>
 * 
 * @author gouldbergstein
 *
 * @param <CAR> Contents of the Address part of Register number
 * @param <CDR> Contents of the Decrement part of Register number
 */
public interface Pair<CAR, CDR> {
	/**
	 * @return the {@link CAR}
	 * @see Pair#CAR
	 */
	CAR getCar();

	/**
	 * @return the {@link CDR}
	 * @see Pair#CDR
	 */
	CDR getCdr();
}
