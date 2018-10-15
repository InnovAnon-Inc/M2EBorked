/**
 * 
 */
package com.innovanon.rnd.struct.ss;

import java.util.Collection;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.javasync.streams.Replayer;

import com.innovanon.rnd.struct.bag.BagImpl;

/**
 * @author gouldbergstein
 *
 */
public enum App {
	/* no instances */ ;
	
	public static void main(String... args) {
		//Stream<Integer> data1 = Stream.iterate(0, x->3 * x + 2).limit((Integer.MAX_VALUE - 2) / 3);
		Stream<Integer> data1 = Stream.iterate(0, x->3 * x + 2).limit(100);
		Supplier<Stream<Integer>> supplier1 = Replayer.replay(data1 );
		Collection<Integer> c1 = new ImmutableStreamCollectionImpl<>(supplier1);
		//
		//Stream<Integer> data2 = Stream.iterate(1, x->x * x).limit((int) Math.sqrt(Integer.MAX_VALUE));
		Stream<Integer> data2 = Stream.iterate(1, x->x * x).limit(100);
		Supplier<Stream<Integer>> supplier2 = Replayer.replay(data2 );
		Collection<Integer> c2 = new ImmutableStreamCollectionImpl<>(supplier2);
		//
		Collection<Integer> c1Ic2 = SetUtil.intersection(c1, c2);
		Random random = new Random ();
		//c1Ic2.stream().forEach(System.out::println);
		Collection<Integer> rc1Ic2 = new BagImpl<>(c1Ic2, random);
		rc1Ic2.stream().forEach(System.out::println);
	}
}
