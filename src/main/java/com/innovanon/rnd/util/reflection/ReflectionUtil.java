/**
 * 
 */
package com.innovanon.rnd.util.reflection;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.innovanon.rnd.util.IsInstancePredicate;

/**
 * @author gouldbergstein
 *
 */
@Deprecated
public enum ReflectionUtil {
	;
	@SafeVarargs
	public static <T> boolean isInstance(T object, Class<? extends T>... types) {
		return isInstance(object, Stream.of(types));
	}

	public static <T> boolean isInstance(T object, Collection<Class<? extends T>> types) {
		return isInstance(object, types.stream());
	}

	public static <T> boolean isInstance(T object, Stream<Class<? extends T>> types) {
		Predicate<Class<? extends T>> p = new IsInstancePredicate<>(object);
		return types.anyMatch(p);
	}
}
