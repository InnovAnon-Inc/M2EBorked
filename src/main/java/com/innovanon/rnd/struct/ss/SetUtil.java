/**
 * 
 */
package com.innovanon.rnd.struct.ss;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.javasync.streams.Replayer;

import com.innovanon.rnd.at.Todo;
import com.innovanon.rnd.struct.bag.BagUtil;
import com.innovanon.rnd.struct.memo.Memoizer;
import com.innovanon.rnd.struct.stream.StreamUtil;

/**
 * @author gouldbergstein
 *
 */
public enum SetUtil {
	/* no instances */ ;

	/**
	 * the union of a set and b set
	 * 
	 * @param a the a set
	 * @param b the b set
	 * @return the elements which are in either a set and/or b set
	 */
	public static <E> Collection<E> union(Collection<E> a, Collection<E> b) {
		Predicate<? super E> predicate = o -> !a.contains(o);
		Stream<E> as = a.stream();
		Stream<E> bs = b.stream().filter(predicate);
		Stream<E> ab = Stream.concat(as, bs);
		Supplier<Stream<E>> s = Replayer.replay(ab);
		return new ImmutableStreamCollectionImpl<>(s);
	}

	/**
	 * the intersections of a set and b set
	 * 
	 * @param a the a set
	 * @param b the b set
	 * @return the elements which are in both a set and b set
	 */
	public static <E> Collection<E> intersection(Collection<E> a, Collection<E> b) {
		Predicate<? super E> predicate = o -> a.contains(o);
		// Stream<E> as = a.stream();
		Stream<E> bs = b.stream().filter(predicate);
		// Stream<E> ab = Stream.concat(as, bs);
		Stream<E> ab = bs;
		Supplier<Stream<E>> s = Replayer.replay(ab);
		return new ImmutableStreamCollectionImpl<>(s);
	}

	/**
	 * the difference of a set and b set
	 * 
	 * @param a the minuend set
	 * @param b the subtrahend set
	 * @return the elements which are in a set but not in b set
	 */
	public static <E> Collection<E> difference(Collection<E> a, Collection<E> b) {
		Predicate<? super E> predicate = o -> !b.contains(o);
		Stream<E> as = a.stream();
		// Stream<E> bs = b.stream();
		Stream<E> ab = as.filter(predicate);
		Supplier<Stream<E>> s = Replayer.replay(ab);
		return new ImmutableStreamCollectionImpl<>(s);
	}

	/**
	 * the set difference (of the set union (of a set and b set) and the set
	 * intersection (of a set and b set))
	 * 
	 * @param a the a set
	 * @param b the b set
	 * @return the elements which are in either a set or b set but not both
	 */
	public static <E> Collection<E> antiIntersection(Collection<E> a, Collection<E> b) {
		Predicate<? super E> aPredicate = o -> b.contains(o);
		Predicate<? super E> bPredicate = o -> a.contains(o);
		Stream<E> as = a.stream().filter(aPredicate);
		Stream<E> bs = b.stream().filter(bPredicate);
		Stream<E> ab = Stream.concat(as, bs);
		Supplier<Stream<E>> s = Replayer.replay(ab);
		return new ImmutableStreamCollectionImpl<>(s);
	}

	/**
	 * 
	 * @param c the source collection
	 * @param n the size of the subsets
	 * @return the subsets of c collection that are n size
	 */
	public static <E> Collection<Collection<E>> subsets(Collection<E> c, int n) {
		// if (c.size() < n)return null;
		if (c.size() < n)
			return Collections.emptyList();
		// Stream<E>s = c.stream();
		// Supplier<Stream<E>> r = Replayer.replay(s);
		// Function<Integer, Collection<E>> mapper = i ->
		// r.get().skip(i).limit(n).collect(Collectors.toList());
		// Stream<Collection<E>> ss = Stream.iterate(0, x -> x + 1).limit
		// (c.size()-n).map(mapper );
		// return ss.collect(Collectors.toList());
		return StreamUtil.collectTheCollectors(substreams(c, n));
	}

	/**
	 * 
	 * @param c the source collection
	 * @return the set of all subsets of c collection
	 */
	public static <E> Collection<Collection<E>> superset(Collection<E> c) {
		return StreamUtil.collectTheCollectors(superstream(c));
	}



	/**
	 * https://stackoverflow.com/questions/25311535/generate-one-stream-from-multiple-suppliers
	 * 
	 * @param c the source collection
	 * @param n the size of the subsets
	 * @return the subsets of c collection that are n size
	 */
	public static <E> Stream<Stream<E>> substreams(Collection<E> c, int n) {
		if (n == 0 || c.isEmpty() || c.size() < n)
			// return Optional.empty();
			return null;
		Stream<E> s = c.stream();
		Supplier<Stream<E>> r = Replayer.replay(s);
		Function<Integer, Stream<E>> mapper = i -> r.get().skip(i).limit(n);
		Stream<Stream<E>> ss = Stream.iterate(0, x -> x + 1).limit(Math.max(c.size() - n, 0)).map(mapper);
		// return Optional.of(ss);
		return ss;
	}

	/**
	 * gather all subsets up to the given size
	 * 
	 * @param mapper yields subsets of the specified sizes
	 * @param size   the size of the collection
	 * @return the mapper function applied to each non-negative integer up to and
	 *         not including size
	 */
	private static <T> Stream<T> superstreamHelper(Function<Integer, Stream<T>> mapper, int size) {
		return Stream.iterate(0, x -> x + 1).limit(size).flatMap(mapper);
	}

	/**
	 * 
	 * @param c the source collection
	 * @return the set of all subsets of c collection
	 * @see #superstreamHelper(Function, int)
	 * @see #substreams(Collection, int)
	 */
	public static <E> Stream<Stream<E>> superstream(Collection<E> c) {
		// Function<Integer, Optional<Stream<Stream<E>>>> mapper = n -> substreams(c,
		// n);
		// return Stream.iterate(0, x -> x + 1).limit(c.size()).flatMap(mapper);
		if (c.isEmpty())
			return null;
		// Function<Integer, Stream<Stream<E>>> mapper = n -> substreams(c, n);
		// return Stream.iterate(0, x -> x + 1).limit(c.size()).flatMap(mapper);
		return superstreamHelper(n -> substreams(c, n), c.size());
	}

	/**
	 * 
	 * @param sss    the source stream
	 * @param random the source of entropy
	 * @return a supplier of randomized streams of sss
	 * @see BagUtil#getRandomStreamSupplier(Stream, Random)
	 */
	private static <T> Supplier<Stream<T>> randomSubstreamsHelper(Stream<T> sss, Random random) {
		return BagUtil.getRandomStreamSupplier(sss, random);
	}

	/**
	 * 
	 * @param c      the source collection
	 * @param n      the size of the subsets
	 * @param random the source of entropy
	 * @return a supplier of randomized streams of suppliers of randomized
	 *         subset-streams of the specified size
	 *    @see #randomSubstreamsHelper(Stream, Random)
	 *    @see #substreams(Collection, int)
	 *    @see BagUtil#getRandomStreamSupplier(Stream, Random)
	 */
	public static <E> Supplier<Stream<Supplier<Stream<E>>>> randomSubstreams(Collection<E> c, int n, Random random) {
		// if (c.size() < n)return Optional.empty();
		if (n == 0 || c.isEmpty() || c.size() < n)
			return null;
		return randomSubstreamsHelper(substreams(c, n).map(s -> BagUtil.getRandomStreamSupplier(s, random)), random);
		// Stream<Supplier<Stream<E>>> sss = substreams(c, n).map(s ->
		// BagUtil.getRandomStreamSupplier(s, random));
		//// return Optional.of(BagUtil.getRandomStreamSupplier(sss, random));
		// return BagUtil.getRandomStreamSupplier(sss, random);
	}

	/**
	 * 
	 * @param sss    the source stream
	 * @param random the source of entropy
	 * @return a supplier of randomized streams of sss
	 * @see BagUtil#getRandomStreamSupplier(Stream, Random)
	 */
	private static <T> Supplier<Stream<T>> randomSuperstreamHelper(Stream<T> sss, Random random) {
		return BagUtil.getRandomStreamSupplier(sss, random);
	}

	/**
	 * 
	 * @param c      the source collection
	 * @param random the source of entropy
	 * @return a supplier of randomized streams of suppliers of randomized
	 *         subset-streams
	 *         @see #randomSuperstreamHelper(Stream, Random)
	 *         @see #superstream(Collection)
	 *         @see BagUtil#getRandomStreamSupplier(Stream, Random)
	 */
	public static <E> Supplier<Stream<Supplier<Stream<E>>>> randomSuperstream(Collection<E> c, Random random) {
		if (c.isEmpty())
			return null;
		return randomSuperstreamHelper(superstream(c).map(s -> BagUtil.getRandomStreamSupplier(s, random)), random);
		// Stream<Supplier<Stream<E>>> sss = superstream(c).map(s ->
		// BagUtil.getRandomStreamSupplier(s, random));
		// return BagUtil.getRandomStreamSupplier(sss, random);
	}

	/**
	 * 
	 * @param f0 a memoize-able function
	 * @param ns the supplier of sizes
	 * @return a supplier which applies the f0 function to the sizes yielded by the
	 *         ns supplier
	 *         @see Memoizer#memoize(Function)
	 */
	private static <T> Supplier<T> randomSubsetSupplierHelper(Function<Integer, T> f0, IntSupplier ns) {
		Function<Integer, T> f = Memoizer.memoize(f0);
		// System.out.println("randomSubsetSupplier 2");
		return () -> f.apply(ns.getAsInt());
	}

	/**
	 * 
	 * @param c the source collection
	 * @param ns the supplier of sizes
	 * @param random the source of entropy
	 * @return a supplier that yields random streams of suppliers that yield random subset-streams of random sizes
	 * @see #randomSubsetsSupplierHelper(Supplier)
	 * @see #randomSubstreams(Collection, int, Random)
	 */
	public static <E> Supplier<Supplier<Stream<Supplier<Stream<E>>>>> randomSubsetSupplier(Collection<E> c,
			IntSupplier ns, Random random) {
		if (c.isEmpty())
			return null;
		return randomSubsetSupplierHelper(n -> randomSubstreams(c, n, random), ns);
		//// System.out.println("randomSubsetSupplier 0");
		// Function<Integer, Supplier<Stream<Supplier<Stream<E>>>>> f0 = n ->
		//// randomSubstreams(c, n, random);
		//// Function<Integer, Supplier<Stream<Supplier<Stream<E>>>>> f0 = n -> n <
		//// c.size() ? Optional.empty() : Optional.ofNullable(randomSubstreams(c, n,
		//// random));
		//// Function<Integer, Supplier<Stream<Supplier<Stream<E>>>>> f0 = n ->
		//// randomSubstreams(c, Math.min(n, c.size()), random);
		//// System.out.println("randomSubsetSupplier 1");
		// Function<Integer, Supplier<Stream<Supplier<Stream<E>>>>> f =
		//// Memoizer.memoize(f0);
		//// System.out.println("randomSubsetSupplier 2");
		// return () -> f.apply(ns.getAsInt());
	}

	/**
	 * flatten the supplier:
	 * for each element yielded by the supplier, flat-map its stream
	 * @param supplier the supplier of streams
	 * @return a flattened stream
	 */
	private static <T> Stream<T> randomSubsetsSupplierHelper(Supplier<Supplier<Stream<T>>> supplier) {
		Stream<Supplier<Stream<T>>> stream = Stream.generate(supplier);
		Function<Supplier<Stream<T>>, Stream<T>> mapper = s -> s != null ? s.get() : null;
		// System.out.println(c.size());
		// stream.forEach(s -> s.get().forEach(ss ->
		// ss.get().forEach(System.out::println)));
		// System.out.println("randomSubsetsSupplier 1");
		Stream<T> stream2 = stream.flatMap(mapper);
		// System.out.println("randomSubsetsSupplier 2");
		return stream2;
	}

	// TODO use optionals
	@Todo("use optionals instead of nulls")
	/**
	 * @param c the source collection
	 * @param ns the supplier of sizes
	 * @param random the source of entropy
	 * @return a stream of suppliers of random subset-streams of random sizes
	 * @see #randomSubsetsSupplierHelper(Supplier)
	 * @see #randomSubsetSupplier(Collection, IntSupplier, Random)
	 */
	public static <E> Stream<Supplier<Stream<E>>> randomSubsetsSupplier(Collection<E> c, IntSupplier ns,
			Random random) {
		// System.out.println("randomSubsetsSupplier 0");
		if (c.isEmpty())
			return null;
		// Supplier<Supplier<Stream<Supplier<Stream<E>>>>>supplier=randomSubsetSupplier(c,
		// ns, random);
		// Stream<Supplier<Stream<Supplier<Stream<E>>>>> stream =
		// Stream.generate(supplier);
		// Function<? super Supplier<Stream<Supplier<Stream<E>>>>, ? extends
		// Stream<Supplier<Stream<E>> >> mapper=s->s.get();
		//// System.out.println(c.size());
		//// stream.forEach(s -> s.get().forEach(ss ->
		// ss.get().forEach(System.out::println)));
		//// System.out.println("randomSubsetsSupplier 1");
		// Stream<Supplier<Stream<E>>> stream2 = stream.flatMap( mapper);
		//// System.out.println("randomSubsetsSupplier 2");
		// return stream2;
		return randomSubsetsSupplierHelper(randomSubsetSupplier(c, ns, random));
	}
	
	/**
	 * https://www.baeldung.com/java-filter-stream-of-optional
	 * @param s stream of optionals
	 * @return stream with the empty optionals removed
	 */
	public static <T> Stream<T> filterOptionals (Stream<Optional<T>> s) {
		//return s.flatMap(Optional::stream);
		return s.filter(o -> o.isPresent()).map(o -> o.get());
	}
}
