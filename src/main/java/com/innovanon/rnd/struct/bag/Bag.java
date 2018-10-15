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
package com.innovanon.rnd.struct.bag;

import java.util.Collection;
import java.util.Queue;

/**
 * a random queue
 * @author gouldbergstein
 */
public interface Bag<E> extends Collection<E>, Queue<E> {
	/**
	 * removes from the bag the specified number of elements selected arbitrarily
	 * (most implementations will use random selection)
	 * @param amount the number of elements to remove from the bag
	 * @return the elements which were removed from the bag
	 * @see java.util.Queue#remove()
	 */
	Collection<E> bulkRemove (int amount) ;
	/**
	 * removes from the bag an arbitrarily selected number of elements 
	 * (most implementations will use random selection)
	 * @return the elements which were removed from the bag
	 * @see Bag#bulkRemove(int)
	 */
	Collection<E> bulkRemove ();
}
