/**
 * 
 */
package com.innovanon.rnd.io;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.javasync.streams.Replayer;

/**
 * @author gouldbergstein
 *
 */
public abstract class IOStream<T, R> extends IO<T, Supplier<Stream<R>>, Stream<R>> {

	@Override
	protected Optional<Stream<R>> outputHelper(Optional<Supplier<Stream<R>>> input) {
		if (input.isPresent())
			return Optional.of(input.get().get());
		//return input.get();
		return Optional.empty();
	}

	@Override
	protected Optional<Supplier<Stream<R>>> inputHelper(T input) {
		Optional<Stream<R>> h = helper(input);
		//if (h == null)return null;
		if (h.isPresent())return Optional.of(Replayer.replay(h.get()));
		//return Replayer.replay(h);
		return Optional.empty();
	}

	protected abstract Optional<Stream<R>> helper(T input);
}
