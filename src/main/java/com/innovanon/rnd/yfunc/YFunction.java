/**
 * 
 */
package com.innovanon.rnd.yfunc;

import java.util.function.Function;

/**
 * @author gouldbergstein
 *
 */
public interface YFunction<T,R,F extends YFunction<T,R,F>> extends Function<T,R>, Delegator<F>{
	
}
