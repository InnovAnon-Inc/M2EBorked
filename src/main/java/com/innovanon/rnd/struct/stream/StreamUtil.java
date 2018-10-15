/**
 * 
 */
package com.innovanon.rnd.struct.stream;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author gouldbergstein
 *
 */
public enum StreamUtil {
	/* no instances */ ;

	/**
	 * map from a collection of T objects to a collection of R objects
	 * @param ct the input collection
	 * @param mapper the mapping function
	 * @return the output collection
	 */
	public static <T, R> Collection<R> map(Collection<T> ct, Function<T, R> mapper) {
		return ct.stream().map(mapper).collect(Collectors.toList());
	}
	
	public static <T, R>Supplier<R>map(Supplier<T>st, Function<T,R>mapper){
		//System.out.println("StreamUtil.map(Supplier, Function)");
	return () -> {
		//System.out.println("StreamUtil.map(Supplier, Function) lambda");
		T in = st.get();
		//System.out.println("StreamUtil.map(Supplier, Function) in="+in);
		R ret = mapper.apply(in);
		//System.out.println("StreamUtil.map(Supplier, Function) ret="+ret);
		return ret;
	};
	}
	
		/**
		 * https://www.techempower.com/blog/2016/10/19/efficient-multiple-stream-concatenation-in-java/
		 * 
		 * @param ss
		 * @return
		 */
		public static <E> Stream<E> concat(Stream<Stream<E>> ss) {
			return ss.reduce((a, b) -> Stream.concat(a, b)).get();
		}
		
		/**
		 * https://www.baeldung.com/java-filter-stream-of-optional
		 * @param ss
		 * @return
		 */
		public static <E> Collection<E> concatStream (Stream<Collection<E>> ss){
			return ss.flatMap(c -> c.stream()).collect(Collectors.toList());
		}
		
		/**
		 * https://www.baeldung.com/java-filter-stream-of-optional
		 * @param ss
		 * @return
		 */
		public static <E> Collection<E> concatCollection (Collection<Collection<E>> ss){
			return concatStream(ss.stream());
		}
		
		/**
		 * https://www.baeldung.com/java-filter-stream-of-optional
		 * @param ss
		 * @return
		 */
		@SafeVarargs
		public static <E> Collection<E> concat (Collection<E>... ss){
			return concatStream(Stream.of(ss));
		}
		
		/**
		 * convert a stream of streams to a collection of collections
		 * 
		 * @param ss the stream of streams to convert
		 * @return the collected stream of collected streams
		 */
		public static <E> Collection<Collection<E>> collectTheCollectors(Stream<Stream<E>> ss) {
			return ss.map(s -> s.collect(Collectors.toList())).collect(Collectors.toList());
		}
}
