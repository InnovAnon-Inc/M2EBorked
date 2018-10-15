/**
 * 
 */
package com.innovanon.rnd.yfunc;

/**
 * @author gouldbergstein
 *
 */
public interface Delegator<F extends Delegator<F>> {
	/**
	 * 
	 * @return
	 */
	//F getDelegate();

	/**
	 * 
	 * @param delegate
	 */
	void setDelegate(F delegate);
}
