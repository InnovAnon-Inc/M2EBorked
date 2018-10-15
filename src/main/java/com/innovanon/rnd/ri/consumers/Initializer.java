/**
 * 
 */
package com.innovanon.rnd.ri.consumers;

import java.util.function.Consumer;
import java.util.function.Predicate;

import com.innovanon.rnd.ri.functions.YInstantiator;
import com.innovanon.rnd.yfunc.Delegator;

/**
 * @author gouldbergstein
 *
 */
public interface Initializer<R, T> extends Predicate<T>, Consumer<T>, Delegator<YInstantiator<R, T>>{

}
