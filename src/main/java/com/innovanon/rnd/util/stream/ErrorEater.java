/**
 * 
 */
package com.innovanon.rnd.util.stream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.innovanon.rnd.util.IsInstancePredicate;

/**
 * @author gouldbergstein
 *
 */
@Deprecated
public class ErrorEater<R,E> {

	private Collection<Class<? extends Throwable>> tastyExceptions;
	private List<E> errors;

	/**
	 * @param exceptionClassesToCatch
	 * @param rememberErrors
	 */
	public ErrorEater(Collection<Class<? extends Throwable>> exceptionClassesToCatch, boolean rememberErrors) {
		tastyExceptions = exceptionClassesToCatch;
		if (rememberErrors)
			errors = new ArrayList<>();
	}

	public Optional<R> apply(Supplier<R> f, Function<Throwable,E> errfunc) {
		try {
			return Optional.ofNullable(f.get());
		} catch (Throwable e) {
			Predicate<? super Class<? extends Throwable>> predicate = new IsInstancePredicate<>(e);
			if (!tastyExceptions.stream().anyMatch(predicate))
				throw e;
			if (errors != null)
				errors.add(errfunc.apply(e));
		}
		return Optional.empty();
	}

	/**
	 * @return the errors
	 */
	public List<E> getErrors() {
		return Collections.unmodifiableList(errors);
	}
}
