/**
 * 
 */
package com.innovanon.rnd.M2EBorked;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * {@link https://docs.python.org/2/library/itertools.html#itertools.combinations}
 * @author gouldbergstein
 *
 */
public interface Itertools4J {

	/**
	 * <code>
	 * def repeat(object, times=None):
	 *    # repeat(10, 3) --> 10 10 10
	 *    if times is None:
	 *       while True:
	 *          yield object
	 *    else:
	 *       for i in xrange(times):
	 *          yield object
	 * </code>
	 * 
	 * @param args
	 * @param repeat
	 * @return
	 */
	<T> Collection<Stream<T>> repeat(Collection<Stream<T>> args, int repeat);

	<T> Collection<Supplier<Stream<T>>> repeat2(Collection<Supplier<Stream<T>>> args, int repeat);

	<T> Collection<Collection<T>> repeat3(Collection<Collection<T>> args, int repeat);
	
	<T> Collection<T> repeat4(Collection<T> args, int repeat);

	/**
	 * <code>
	 * def product(*args, **kwds):
	 *    # product('ABCD', 'xy') --> Ax Ay Bx By Cx Cy Dx Dy
	 *    # product(range(2), repeat=3) --> 000 001 010 011 100 101 110 111
	 *    pools = map(tuple, args) * kwds.get('repeat', 1)
	 *    result = [[]]
	 *    for pool in pools:
	 *       result = [x+[y] for x in result for y in pool]
	 *    for prod in result:
	 *       yield tuple(prod)
	 * </code>
	 * 
	 * @param args
	 * @param repeat
	 * @return
	 */
	<T> Stream<Collection<T>> product(Collection<Stream<T>> args, int repeat);

	<T> Stream<Collection<T>> product(Collection<Stream<T>> args);

	<T> Collection<Collection<T>> product2(Collection<Collection<T>> args, int repeat);

	<T> Collection<Collection<T>> product2(Collection<Collection<T>> args);

	/**
	 * <code>
	 * def combinations(iterable, r):
	 *    pool = tuple(iterable)
	 *    n = len(pool)
	 *    for indices in permutations(range(n), r):
	 *       if sorted(indices) == list(indices):
	 *          yield tuple(pool[i] for i in indices)
	 * </code>
	 * @param c
	 * @return
	 */
	<T> Collection<Collection<T>> combinations(List<T> c, int repeat);

	<T> Collection<Collection<T>> combinations(List<T> c);
	
	/**
	 * <code>
	 * def cycle(iterable):
	 *    # cycle('ABCD') --> A B C D A B C D A B C D ...
	 *    saved = []
	 *    for element in iterable:
	 *       yield element
	 *       saved.append(element)
	 *    while saved:
	 *       for element in saved:
	 *          yield element
	 * </code>
	 * 
	 * @param s
	 * @return
	 */
	<T> Stream<T> cycle(Stream<T> s);
}
