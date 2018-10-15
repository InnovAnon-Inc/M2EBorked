/**
 * 
 */
package com.innovanon.rnd.func;

/**
 * @author gouldbergstein
 *
 */
@FunctionalInterface
public interface ByteSupplier {
	/**
	 * Gets a result.
	 *
	 * @return a result
	 */
	byte getAsByte();
}