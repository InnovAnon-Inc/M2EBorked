/**
 * 
 */
package com.innovanon.rnd.M2EBorked;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.javasync.streams.Replayer;

import com.innovanon.rnd.struct.stream.StreamUtil;
import com.innovanon.rnd.util.CountingUtil;

/**
 * @author gouldbergstein
 *
 */
public enum Itertools4JImpl implements Itertools4J {
	INSTANCE;
	
	public <T> Collection<Stream<T>> repeat(Collection<Stream<T>> args, int repeat) {
		Collection<Supplier<Stream<T>>> args2 = StreamUtil.map(args, x -> Replayer.replay(x));
		Supplier<Collection<Stream<T>>> mapper = () -> args2.stream().map(x -> x.get()).collect(Collectors.toList());
		BinaryOperator<Collection<Stream<T>>> m = (a, b) -> CollectionUtil.union(a, b);
		return Stream.generate(mapper).limit(repeat).reduce(new ArrayList<>(), m);
	}

	public <T> Collection<Supplier<Stream<T>>> repeat2(Collection<Supplier<Stream<T>>> args, int repeat) {
		// Collection<Supplier<Stream<T>>> args2 = StreamUtil.map(args, x ->
		// Replayer.replay(x));
		Supplier<Collection<Supplier<Stream<T>>>> mapper = () -> args.stream().collect(Collectors.toList());
		BinaryOperator<Collection<Supplier<Stream<T>>>> m = (a, b) -> CollectionUtil.union(a, b);
		return Stream.generate(mapper).limit(repeat).reduce(new ArrayList<>(), m);
	}

	public <T> Stream<Collection<T>> product(Collection<Stream<T>> args, int repeat) {
		Collection<Supplier<Stream<T>>> args2 = StreamUtil.map(args, x -> Replayer.replay(x));
		Collection<Supplier<Stream<T>>> pools = repeat2(args2, repeat);
		Supplier<Stream<Collection<T>>> result = Replayer.replay(Stream.of(new ArrayList<>()));
		for (Supplier<Stream<T>> pool2 : pools) {
			Stream<T> pool = pool2.get();
			Supplier<Stream<Collection<T>>> myResult = result;
			Function<T, Stream<Collection<T>>> yf = y -> myResult.get().map(x -> CollectionUtil.add(x, y));
			result = Replayer.replay(pool.flatMap(yf));
		}
		return result.get();
	}

	public <T> Collection<Collection<T>> product2(Collection<Collection<T>> args, int repeat) {
		Collection<Stream<T>> args2 = StreamUtil.map(args, x -> x.stream());
		Stream<Collection<T>> args3 = product(args2, repeat);
		return new ArrayList<>(args3.collect(Collectors.toList()));
	}
	
	public <T> Collection<Collection<T>> product2(Collection<Collection<T>> args) {
		return product2(args,1);
	}

	public <T> Collection<Collection<T>> repeat3(Collection<Collection<T>> args, int repeat) {
		Supplier<Collection<Collection<T>>> mapper = () -> args;
		BinaryOperator<Collection<Collection<T>>> m = (a, b) -> CollectionUtil.union(a, b);
		return Stream.generate(mapper).limit(repeat).reduce(new ArrayList<>(), m);
	}

	public <T> Stream<Collection<T>> product(Collection<Stream<T>> args) {
		return product(args, 1);
	}

	@Override
	public <T> Collection<Collection<T>> combinations(List<T> fpool, int n) {
			//  int  n = pool.size();
			 // for (Collection<T> indices: permutations(Stream.iterate(0, x->x+1).limit(n), repeat))
			  //      if sorted(indices) == list(indices):
			   //         yield tuple(pool[i] for i in indices)
	
		// for (int i = 1; i < L.size(); i++)
		// for (int j = 0; j < i; j++)
			// System.out.println(Arrays.asList(L.get(i), L.get(j)));
		
		Collection<Collection<T>> ret = new ArrayList<>(CountingUtil.nCr(fpool.size(), n));
		for (int i = 0; i < fpool.size(); i++) {
			Collection<T> e = new ArrayList<>(n);
			e.add(fpool.get(i));
			//ret.add(e);
			ret.addAll(combinationsHelper (fpool, e, n-1, i));
		}
		return ret;
	}
	
	private <T> Collection<Collection<T>> combinationsHelper(List<T>fpool,Collection<T>e, int n, int i) {
		if (n == 0) return Arrays.asList(e);
		Collection<Collection<T>>ee = new ArrayList<>(/* TODO */);
		for (int j = 0; j < i; j++) {
			Collection<T>E = new ArrayList<>(e.size() + n);
			E.addAll(e);
			E. add(fpool.get(j));
			//ee.add(E);
			ee.addAll(combinationsHelper (fpool, E, n - 1, j));
		}
		return ee;
	}
	
	public <T> Collection<T> repeat4(Collection<T> fpool, int repeat) {
		Collection<T> pool = new ArrayList<>(fpool.size()*repeat);
		for (int k = 1; k <= repeat; k++)
			pool.addAll(fpool);
		return pool;
	}

	@Override
	public <T> Collection<Collection<T>> combinations(List<T> pool) {
		if (pool.isEmpty())throw new Error();
		Collection<Collection<T>> C = new ArrayList<>(CountingUtil.nCrs (pool.size()));
		for (int n = 1; n <= pool.size(); n++) {
			Collection<Collection<T>>c=combinations(pool, n);
			C.addAll(c);
		}
		return C;
	}

	@Override
	public <T> Stream<T> cycle(Stream<T> s) {
		Supplier<Stream<T>>ss = Replayer.replay(s);
		Stream<Stream<T>> SS = Stream.generate(ss);
		return StreamUtil.concat(SS );
	}
}
