/**
 * 
 */
package com.innovanon.rnd.ri.functions;

import com.innovanon.rnd.yfunc.YFunction;

/**
 * 
 * @author gouldbergstein
 *
 * @param <T>
 * @param <R>
 */
public interface YInstantiator<T, R> extends Instantiator<T,R>, YFunction<T, R, YInstantiator<T, R>> {
	
}
