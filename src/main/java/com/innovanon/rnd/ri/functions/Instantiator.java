/**
 * 
 */
package com.innovanon.rnd.ri.functions;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 
 * @author gouldbergstein
 *
 * @param <T>
 * @param <R>
 */
public interface Instantiator<T,R> extends Predicate<T>, Function<T,R>{

}
