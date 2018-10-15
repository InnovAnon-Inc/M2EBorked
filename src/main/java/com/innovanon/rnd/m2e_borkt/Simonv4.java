/**
 * 
 */
package com.innovanon.rnd.m2e_borkt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.javasync.streams.Replayer;

import com.innovanon.rnd.M2EBorked.CollectionUtil;
import com.innovanon.rnd.M2EBorked.Itertools4JImpl;
import com.innovanon.rnd.M2EBorked.Itertools4JImpl2;
import com.innovanon.rnd.struct.pair.Pair;
import com.innovanon.rnd.struct.stream.StreamUtil;

/**
 * @author gouldbergstein
 *
 */
public class Simonv4 implements Runnable {

	/*
	 * private static <T> Stream<T> smooth_rec (Collection<T> wheel,
	 * BinaryOperator<T> f) { Pair<T,Collection<T>> p=remove (wheel); T wheel0 =
	 * p.getCar(); if (wheel.size() == 1) return p (wheel0,f); return pp (wheel0,
	 * smooth_rec (p.getCdr(), f), f); }
	 */

	/*
	private static <T> Stream<T> merge(Stream<T> a, Stream<T> b) {
		return Stream.concat(a, b);
	}
	 */
	
	/*
	 * private static <T> Pair<Stream<T>,Stream<T>> tee(Stream<T> s){ return null; }
	 * 
	 * private static <T> Stream<T> pp(T n, Stream<T> s, BinaryOperator<T> f) {
	 * Supplier<Stream<T>>ss = Replayer.replay(s);
	 * 
	 * Function<T, Stream<T>> mapper = x -> pp (x, ss.get(), f); return
	 * Stream.iterate(n, x -> f.apply(x, n)).flatMap(mapper );//.reduce((a, b)->
	 * merge(a,b));
	 */ /*
		 * Function<T, T> ffff=y -> f.apply(n, y); Function<Stream<T>,Stream<T>> fff = S
		 * -> S.map(ffff); Function<Stream<T>, Stream<T>> ff = x -> pp (n, x, f);
		 * 
		 * Stream<T>sss = StreamUtil.concat(Stream.iterate(ss.get(), x ->
		 * ff.apply(fff.apply(x)))); return merge (ss.get(), sss);
		 */
	// def gen ():
	// for x in (Hamming.merge (s, chain ([n], (n * y for y in fb)))):
	// yield x
	/*
	 * }
	 * 
	 * private static <T> Stream<T> p(T wheel0, BinaryOperator<T> f) { // f = x -> x
	 * * wheel0 return Stream.iterate(wheel0, x -> f.apply(x, wheel0)); }
	 * 
	 * public static <T> Stream<T> smooth (Collection<T>wheel, BinaryOperator<T> f){
	 * // [1] is base case return Stream.concat(Stream.empty(), smooth_rec (wheel,
	 * f)); }
	 */
	
	private static <T> Stream<Collection<T>> loop(Collection<T> left, Collection<T> right) {
		System.out.println("left: " + left);
		System.out.println("right: " + right);
		// if (left.isEmpty())return Stream.empty();
		if (left.isEmpty())
			return Stream.of(right);
		// Collection<T> current = union (left, right);
		// Stream<R> ret = Stream.of(f.apply(current ));
		Stream<Collection<T>> ret = Stream.empty();
		for (T t : left) {
			Collection<T> altLeft = CollectionUtil.remove(left, t);
			Collection<T> altRight = CollectionUtil.add(right, t);
			// altLeft + t + right
			ret = Stream.concat(ret, loop(altLeft, altRight));
		}
		return ret;
	}

	public static <T> Stream<Collection<T>> loop(Collection<T> left) {
		return loop(left, Collections.emptyList());
	}

	private static <T> Stream<Collection<T>> loop4(Collection<T> left, Collection<T> right) {
		// System.out.println("left: " + left);
		// System.out.println("right: " + right);
		// if (left.isEmpty())return Stream.empty();
		if (left.isEmpty())
			return Stream.of(right);
		// Collection<T> current = union (left, right);
		// Stream<R> ret = Stream.of(f.apply(current ));
		Stream<Collection<T>> ret = Stream.empty();
		for (T t : left) {
			Collection<T> altLeft = CollectionUtil.remove(left, t);
			Collection<T> altRight = CollectionUtil.add(right, t);
			// altLeft + t + right
			ret = Stream.concat(ret, loop4(altLeft, altRight));
		}
		return Stream.concat(Stream.of(right), ret);
	}

	public static <T> Stream<Collection<T>> loop4(Collection<T> left) {
		return loop4(left, Collections.emptyList());
	}

	/*
	 * public static <T> Stream<Collection<T>> smooth (Map<Supplier<Stream<T>>,T>
	 * pLeft, Map<Supplier<Stream<T>>,T> pRight){ if (pLeft.size() == 1) {
	 * Function<T,Collection<T>> mapper = t -> { Collection<T> row = new
	 * ArrayList<>(pLeft.size()+pRight.size()); //row.addAll(pLeft.values());
	 * row.add(t); row.addAll(pRight.values()); return row; }; return
	 * pLeft.keySet().stream().findFirst().get().get().map(mapper); }
	 * 
	 * Function<Entry<Supplier<Stream<T>>,T>, Stream<Collection<T>>> mapper = entry
	 * -> { Map<Supplier<Stream<T>>, T> altLeft = remove (pLeft, entry);
	 * Map<Supplier<Stream<T>>, T> altRight = add(pLeft, entry); return smooth
	 * (altLeft, altRight); }; Stream<Collection<T>> s =
	 * pLeft.entrySet().stream().flatMap(mapper ); return s; }
	 */

	

	/*
	 * public static <T> Stream<Collection<T>> smooth (Collection<Stream<T>> c ) {
	 * Function<Supplier<Stream<T>>, Supplier<Stream<T>>> keyMapper = x ->
	 * Replayer.replay(x.get().skip(1)); Function<Supplier<Stream<T>>, T>
	 * valueMapper = x -> x.get().findFirst().get(); Map<Supplier<Stream<T>>, T> m =
	 * c.stream().map(x -> Replayer.replay(x)).collect(Collectors.toMap(keyMapper,
	 * valueMapper)); Stream<Collection<T>> head = Stream.of(m.values());
	 * Stream<Collection<T>> tail = smooth (m, Collections.emptyMap()); return
	 * Stream.concat(head, tail); }
	 */
	// deserialize objects from net
	// get stats from objects
	// build model

	// given a model,
	// train ML/AI to produce more secondary models

	// given a type (and other conditions)
	// generate all objects, and according to the model
	// if no model exists, use uniform distro

	// filter bad generated objects according to rules and ML/AI

	// given a type (and other conditions)
	// generate a random object, with probabilities according to model
	// if no model exists, use uniform distro

	// by default, use different model for each package.class.?.parameter
	// nvm... use global model that records all features... ^^^ and also specific
	// parameter values

	// build model of realistic code
	// generate random ASTs based on model --> find all ASTs which satisfy the
	// parameters of the model, including higher order stats
	// filter out code that fails the given test cases


	/*
	 * public static <T> Stream<Collection<T>>
	 * however(Collection<Supplier<Stream<T>>> c, int limit) { for
	 * (Supplier<Stream<T>>) Supplier<Stream<T>>ss=remove(c); Stream<T>s=ss.get();
	 * return s.limit(limit).flatMap(i -> however (new ArrayList<>(c), i)); }
	 */
	// public static <T> Stream<Collection<T>> however(Map<T,Supplier<Stream<T>>> t,
	// Collection<T>save, int chk) {
	// assert t.size() == chk;

	// Map<T,Supplier<Stream<T>>> t2 = new HashMap<>();
	// t2.putAll(t);

	// Collection<T> save2 = new ArrayList<>();
	// save2.addAll(save);

	// Stream<Collection<T>> ret= Stream.of(save);
	// Stream<Collection<T>> ret= Stream.of(union(t.keySet(),save));

//		for (Entry<T,Supplier<Stream<T>>>e: t.entrySet()) {
	/*
	 * Map<Supplier<Stream<T>>,T> t2 = new HashMap<>(); t2.putAll(t);
	 * 
	 * Collection<T> save2 = new ArrayList<>(); save2.addAll(save);
	 */
	// Supplier<Stream<T>> ss = e.getValue();
	// T val = e.getKey(); // base case is that val has already been used
	// Map<Supplier<Stream<T>>,T> t2 = remove(t, ss);

	/*
	 * Optional<T> o = ss.get().findFirst(); if (! o.isPresent()) { //return
	 * Stream.concat(ret, however(t2, save, chk)); continue; //break; }
	 */
	// t2.remove(ss);
	// save2.remove(val);

	// ret = Stream.concat(ret, ss.get().flatMap(x -> however(t2, add(save2, x),
	// chk)));
	// ret = Stream.concat(ret, ss.get().flatMap(x -> however(t2, add(save2, x),
	// chk)));
	// ret = Stream.concat(ret, ss.get().flatMap(x -> however(remove (t2, val),
	// add(save2, x), chk)));
	// ret = Stream.concat(ret, ss.get().map(x -> however(remove (t2, val),
	// add(save2, x), chk).findFirst().get() ));
	/*
	 * Supplier<Stream<T>>ss2 = Replayer.replay(ss.get().skip(1)); T val2 = o.get();
	 * t2.put(ss2, val2);
	 * 
	 * ret = Stream.concat(ret, however(t2, save2, chk)); //ret = Stream.concat(ret,
	 * however(t2, add(save,val), chk)); //ret = Stream.concat(ret, however(t2,
	 * add(save,val2), chk)); //save2.add(val);
	 */
	// break;
	// }
	// return ret;

	/*
	 * Function<Entry<Supplier<Stream<T>>, Collection<T>>, Stream<Collection<T>>>
	 * mapper = e -> { Supplier<Stream<T>> supplier = e.getKey();
	 * Map<Supplier<Stream<T>>,Collection<T>> t2 = remove (t, supplier);
	 * Collection<T> vars = e.getValue(); //if (t2.isEmpty())return Stream.of(vars);
	 * //if (vars.size() == chk) // return Stream.of(vars); //assert t2.isEmpty() ==
	 * (vars.size()==chk); //if (t2.isEmpty())return Stream.of(vars);
	 * 
	 * Optional<T> o = supplier.get().findFirst(); if (! o.isPresent()) { assert
	 * vars.size() == chk; assert t2.isEmpty(); //return however(t2, add(vars),chk);
	 * return Stream.of(vars); } //if (! o.isPresent())return Stream.empty();
	 * 
	 * Supplier<Stream<T>>supplier2=Replayer.replay(supplier.get().skip(1));
	 * 
	 * return Stream.concat(however(add(t2, supplier2, vars),chk), Stream.of(vars));
	 * //however(add(t2, null, add(vars,o.get())),chk);
	 * 
	 * //Collection<T> vars2 = add(vars, o.get()); //return however(add(t2,
	 * supplier2, add(vars,o.get())),chk); }; return
	 * t.entrySet().stream().flatMap(mapper);
	 */
	// return mapper.apply(t.entrySet().stream().findFirst().get());

	// t2.add(var.get().findFirst().get());
	// return however(vars2,t2,chk);
	// return however(vars2, var.get().map(x -> add (t, x)).findFirst().get(),chk);
	// return var.get().map(x -> add (t, x)).flatMap(x -> however (vars2, x, chk));
	// return Stream.of(var.get().map(x -> add (t, x)).flatMap(x -> however (vars2,
	// x, chk)).
	// findFirst()).filter(o->o.isPresent()).map(o->o.get());
	// return var.get().map(x -> add (t, x)).map(x -> however (vars2, x,
	// chk).findFirst().get());
	// .apply(c2.stream().findFirst().get());

	/*
	 * Function<Supplier<Stream<T>>, Stream<Collection<T>>> mapper=var -> {
	 * Collection<Supplier<Stream<T>>>vars2 =remove(c2,var); return var.get().map(x
	 * -> add (t, x)).flatMap(x -> however
	 * (Collections.unmodifiableCollection(vars2), x, chk)); }; return
	 * mapper.apply(c2.stream().findFirst().get());
	 */

	// for each var in vars
	// remove var from vars
	// add to done ?
	// for each e in var
	// add e to t
	// }
	/*
	 * public static <T> Stream<Collection<T>> however(Collection<Stream<T>> c) {
	 * Map<T,Supplier<Stream<T>>> m = new HashMap<>(c.size()); for (Stream<T> s : c)
	 * { Supplier<Stream<T>> ss = Replayer.replay(s); T value =
	 * ss.get().findFirst().get(); Supplier<Stream<T>> key =
	 * Replayer.replay(ss.get().skip(1)); m.put(value, key); } return
	 * however(m,Collections.emptyList(),c.size()); }
	 */
//	public <T> Stream<Collection<T>> whatever (Collection<Stream<T>> vars, Stream<T> var){
//		return var.map(t -> however (vars, t));
//	}

// 	public <T> Stream<Collection<T>> baz (Stream<T> var, Collection<T> consts) {
//		throw new TodoException("not done");
//	}

//	public <T> void bar (Map<Stream<T>,T> current) {
//		
//	}

//	public <T> Stream<Collection<T>> foo (Collection<Stream<T>> vars) {
//		Function<Stream<T>,Stream<T>> keyMapper = Function.identity();
//		Function<Stream<T>,T> valueMapper = x->x.;
//		Collector<Stream<T>, ?, Map<Stream<T>, T>> collector=Collectors.toMap(keyMapper, valueMapper);
//		vars.stream().collect(collector);

//		return vars.stream().flatMap(x -> whatever (vars, x));
//	}

	public <T, A, R> void foo() {
		Function<Class<T>, Stream<T>> instantiator;

		Collector<T, A, R> c = new Collector<T, A, R>() {

			@Override
			public Supplier<A> supplier() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public BiConsumer<A, T> accumulator() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public BinaryOperator<A> combiner() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Function<A, R> finisher() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Set<Characteristics> characteristics() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

	@Override
	public void run() {

	}

	private static <T> Stream<Collection<T>> loop2(Collection<Supplier<Stream<T>>> cc, Collection<T> vars) {
		// for (a in A)
		// for (b in B)
		// for (c in C)
		if (cc.isEmpty())
			return Stream.of(vars);
		Pair<Supplier<Stream<T>>, Collection<Supplier<Stream<T>>>> p = CollectionUtil.remove(cc);
		Stream<T> c = p.getCar().get();
		Collection<Supplier<Stream<T>>> cc2 = p.getCdr();
		Function<T, Stream<Collection<T>>> mapper = x -> loop2(cc2, CollectionUtil.add(vars, x));
		return c.flatMap(mapper);
	}

	public static <T> Stream<Collection<T>> loop2(Collection<Stream<T>> c3) {
		return loop2(StreamUtil.map(c3, x -> Replayer.replay(x)), Collections.emptyList());
	}

	private static <T> Stream<Collection<T>> loop3(Collection<Supplier<Stream<T>>> cc, Collection<T> vars) {
		// for (a in A)
		// for (b in B)
		// for (c in C)
		if (cc.isEmpty())
			return Stream.of(vars);
		Pair<Supplier<Stream<T>>, Collection<Supplier<Stream<T>>>> p = CollectionUtil.removeLast(cc);
		Stream<T> c = p.getCar().get();
		Collection<Supplier<Stream<T>>> cc2 = p.getCdr();
		Function<T, Stream<Collection<T>>> mapper = x -> loop3(cc2, CollectionUtil.add(vars, x));
		return c.flatMap(mapper);
	}



	public static <T> Stream<Collection<T>> loop3(Collection<Stream<T>> c3) {
		return loop3(StreamUtil.map(c3, x -> Replayer.replay(x)), Collections.emptyList());
	}

	private static <T> Stream<Collection<T>> loop5(Collection<T> left, Collection<T> right) {
		// System.out.println("left: " + left);
		// System.out.println("right: " + right);
		// if (left.isEmpty())return Stream.empty();
		if (left.isEmpty())
			return Stream.of(right);
		// Collection<T> current = union (left, right);
		// Stream<R> ret = Stream.of(f.apply(current ));
		Stream<Collection<T>> ret = Stream.empty();
		for (T t : left) {
			Collection<T> altLeft = CollectionUtil.remove(left, t);
			Collection<T> altRight = CollectionUtil.add(right, t);
			// altLeft + t + right
			ret = Stream.concat(loop5(altLeft, altRight), ret);
		}
		return Stream.concat(Stream.of(right), ret);
	}

	public static <T> Stream<Collection<T>> loop5(Collection<T> left) {
		return loop5(left, Collections.emptyList());
	}

	private static <T> Stream<Collection<T>> loop6(Collection<T> left, Collection<T> right) {
		// System.out.println("left: " + left);
		// System.out.println("right: " + right);
		// if (left.isEmpty())return Stream.empty();
		if (left.isEmpty())
			return Stream.of(right);
		// Collection<T> current = union (left, right);
		// Stream<R> ret = Stream.of(f.apply(current ));
		Stream<Collection<T>> ret = Stream.empty();
		for (T t : left) {
			Collection<T> altLeft = CollectionUtil.remove(left, t);
			Collection<T> altRight = CollectionUtil.add(right, t);
			// altLeft + t + right
			ret = Stream.concat(loop6(altLeft, altRight), ret);
			break;
			// TODO
		}
		return Stream.concat(Stream.of(right), ret);
	}

	public static <T> Stream<Collection<T>> loop6(Collection<T> left) {
		return loop6(left, Collections.emptyList());
	}

	public static void main(String... args) {
		List<String> L = Arrays.asList("A", "B", "C", "D");
		for (int i = 1; i < L.size(); i++)
			for (int j = 0; j < i; j++)
				System.out.println(Arrays.asList(L.get(i), L.get(j)));
		System.out.println("\n\n\n");
		
		// Function<Collection<Stream<String>>, Stream<String>> flattener = new
		// Flattener<String>();
		Collection<Stream<String>> c2 = Arrays.asList(Stream.of("1", "2"), Stream.of("A", "B"), Stream.of("a", "b"));
		Collection<Stream<String>> c3 = Arrays.asList(Stream.of("1", "2", "3"), Stream.of("A", "B", "C"),
				Stream.of("a", "b", "c"));
		// Stream<Collection<String>> s = //flattener.apply(
		// however(c) ;
		/*
		 * s=s.map(x -> { List<String> y = new ArrayList<>(x); Collections.sort(y);
		 * return (Collection<String>) y; })
		 */
		// Supplier<Stream<Collection<String>>> s = Replayer.replay(however(c));
		// s.get().forEach(System.out::println);
		// System.out.println(s.get().count());
		// smooth(Arrays.asList(2, 3, 5), (a, b) -> a * b).forEach(System.out::println);
		/*
		 * Stream<Collection<String>> s=smooth(c);
		 * Supplier<Stream<Collection<String>>>ss=Replayer.replay(s);
		 * ss.get().forEach(System.out::println); System.out.println(ss.get().count());
		 * System.out.println(ss.get().distinct().count());
		 */

		Collection<String> d = Arrays.asList("a", "b", "c");
		 loop(d).forEach(System.out::println);
		// loop6(d).forEach(System.out::println);
		 System.out.println("\n\n\n");

		 loop2(Arrays.asList(Stream.of("a"),Stream.of("b"),Stream.of("c"))).forEach(System.out::println);
		 System.out.println("\n\n\n");
		 
		 loop3(Arrays.asList(Stream.of("a"),Stream.of("b"),Stream.of("c"))).forEach(System.out::println);
		 System.out.println("\n\n\n");
		 
		 loop4(Arrays.asList("a","b","c")).forEach(System.out::println);
		 System.out.println("\n\n\n");
		 
		 loop5(Arrays.asList("a","b","c")).forEach(System.out::println);
		 System.out.println("\n\n\n");
		 
		// loop2(c2).forEach(System.out::println);
		// product(c2).forEach(System.out::println);

		List<String> X = Arrays.asList("1", "2", "3");
		List<String> Y = Arrays.asList("A", "B", "C", "D");
		List<String> Z = Arrays.asList("a", "b", "c");
		int I, J, K;
		I = J = K = 0;
		Collection<String> output;
		Collection<Collection<String>> outputs = new ArrayList<>(X.size() * Y.size() * Z.size());
		for (int outer = 0; outer < Math.max(Math.max(X.size(), Y.size()), Z.size()); outer++) {
			body(outputs, X, Y, Z, I, J, K);

			I++;
			J++;
			K++;
		}
		if (I < X.size() && J < Y.size() && K < Z.size()) {
			output = Arrays.asList(X.get(I), Y.get(J), Z.get(K));
			outputs.add(Collections.unmodifiableCollection(output));
		}
		for (Collection<String> o : outputs)
			System.out.println(o);
		System.out.println(outputs.size());

		Collection<Collection<String>> chk = Itertools4JImpl.INSTANCE.product(c3).collect(Collectors.toList());
		// for (Collection<String> c : chk) {
		for (Collection<String> c : outputs) {
			// Iterator<Collection<String>>i=outputs.iterator();
			Iterator<Collection<String>> i = chk.iterator();
			while (i.hasNext()) {
				Collection<String> o = i.next();
				int cnt = 0;
				for (String a : c)
					for (String b : o)
						if (a.equals(b))
							cnt++;
				if (cnt == 3)
					i.remove();
			}
		}
		// for (Collection<String> o:outputs)
		for (Collection<String> o : chk)
			System.out.println(o);
		System.out.println(chk.size());

		Itertools4JImpl.INSTANCE.product(Arrays.asList(Stream.of("1", "2", "3"), Stream.of("A", "B", "C"), Stream.of("a", "b", "c")))
				.forEach(System.out::println);
		System.out.println(
				Itertools4JImpl.INSTANCE.product(Arrays.asList(Stream.of("1", "2", "3"), Stream.of("A", "B", "C"), Stream.of("a", "b", "c")))
						.count());
		System.out.println("\n\n\n");
		
		Itertools4JImpl.INSTANCE.combinations(Arrays.asList("a", "b", "c", "d")).stream().forEach(System.out::println);
		System.out.println("\n\n\n");
		
		Itertools4JImpl2.INSTANCE.product(Arrays.asList(Arrays.asList("1", "2", "3"), Arrays.asList("A", "B", "C"), Arrays.asList("a", "b", "c")))
		.forEach(System.out::println);
	System.out.println(	Itertools4JImpl2.INSTANCE.product(Arrays.asList(Arrays.asList("1", "2", "3"), Arrays.asList("A", "B", "C"), Arrays.asList("a", "b", "c"))).size());
	System.out.println("\n\n\n");

	System.out.println("test");
	List<List<String>>tmp=Itertools4JImpl2.INSTANCE.product(Arrays.asList(Arrays.asList("1", "2", "3"), Arrays.asList("A", "B", "C"), Arrays.asList("a", "b", "c", "d"))).
	stream().map(x->{
		List<String>l=new ArrayList<>(x);
		Collections.sort(l, Comparator.naturalOrder());
		return l;
	}).collect(Collectors.toList());
	//Collections.sort(tmp, Comparator.naturalOrder());
	tmp.stream().forEach(System.out::println);
	System.out.println(tmp.size());
	
	System.out.println("verification");
	Collection<Collection<String>> chk2 = Itertools4JImpl.INSTANCE.product(
			Arrays.asList(Stream.of("1", "2", "3"), Stream.of("A", "B", "C"), Stream.of("a", "b", "c", "d"))
			).collect(Collectors.toList());
	// for (Collection<String> c : chk) {
	for (Collection<String> c : tmp) {
		// Iterator<Collection<String>>i=outputs.iterator();
		Iterator<Collection<String>> i = chk2.iterator();
		while (i.hasNext()) {
			Collection<String> o = i.next();
			int cnt = 0;
			for (String a : c)
				for (String b : o)
					if (a.equals(b))
						cnt++;
			if (cnt == 3)
				i.remove();
		}
	}
	// for (Collection<String> o:outputs)
	for (Collection<String> o : chk2)
		System.out.println(o);
	System.out.println(chk2.size());
	}

	private static <T> void body(Collection<Collection<T>> outputs, List<T> X, List<T> Y, List<T> Z, int I, int J,
			int K) {
		Collection<T> output;
		if (I < X.size() && J < Y.size() && K < Z.size()) {
			output = Arrays.asList(X.get(I), Y.get(J), Z.get(K));
			outputs.add(Collections.unmodifiableCollection(output));
		}

		boolean test = true;

		if (I + 1 < X.size()) {
			if (test) {
				Collection<T> y = Y.subList(0, Math.min(I + 1, Y.size()));
				//Collection<T> y = Y.subList(0, Math.min(I, Y.size())+1);
				Collection<T> z = Z.subList(0, Math.min(I + 1, Z.size()));
				//Collection<T> z = Z.subList(0, Math.min(I , Z.size())+1);
				Collection<Collection<T>> c = Itertools4JImpl.INSTANCE.product2(Arrays.asList(y, z), 1);
				for (Collection<T> C : c)
					C.add(X.get(I + 1));
				outputs.addAll(c);
			} else {
				for (int j = 0; j <= I && j < Y.size(); j++)
					for (int k = 0; k <= I && k < Z.size(); k++) {
						output = Arrays.asList(X.get(I + 1), Y.get(j), Z.get(k));
						if (X.get(I+1).equals("1") && Y.get(j).equals("B") && Z.get(k).equals("b"))
							System.out.println("a"+(I+1)+" " + j + " "+k);
						outputs.add(Collections.unmodifiableCollection(output));
					}
			}
		}

		if (J + 1 < Y.size()) {
			if (test) {
				Collection<T> x = X.subList(0, Math.min(J + 1, X.size()));
				//Collection<T> x = X.subList(0, Math.min(J, X.size())+1);
				Collection<T> z = Z.subList(0, Math.min(J + 1, Z.size()));
				//Collection<T> z = Z.subList(0, Math.min(J , Z.size())+1);
				Collection<Collection<T>> c = Itertools4JImpl.INSTANCE.product2(Arrays.asList(x, z), 1);
				for (Collection<T> C : c)
					C.add(Y.get(J + 1));
				outputs.addAll(c);
			} else {
				for (int i = 0; i <= J && i < X.size(); i++)
					for (int k = 0; k <= J && k < Z.size(); k++) {
						output = Arrays.asList(X.get(i), Y.get(J + 1), Z.get(k));
						if (X.get(i).equals("1") && Y.get(J+1).equals("B") && Z.get(k).equals("b"))
							System.out.println("b"+i+" " + (J+1) + " "+k);
						outputs.add(Collections.unmodifiableCollection(output));
					}
			}
		}

		if (K + 1 < Z.size()) {
			if (test) {
				Collection<T> y = Y.subList(0, Math.min(K + 1, Y.size()));
				//Collection<T> y = Y.subList(0, Math.min(K, Y.size())+1);
				Collection<T> x = X.subList(0, Math.min(K + 1, X.size()));
				//Collection<T> x = X.subList(0, Math.min(K , X.size())+1);
				Collection<Collection<T>> c = Itertools4JImpl.INSTANCE.product2(Arrays.asList(x, y), 1);
				for (Collection<T> C : c)
					C.add(Z.get(K + 1));
				outputs.addAll(c);
			} else {
				for (int i = 0; i <= K && i < X.size(); i++)
					for (int j = 0; j <= K && j < Y.size(); j++) {
						output = Arrays.asList(X.get(i), Y.get(j), Z.get(K + 1));
						if (X.get(i).equals("1") && Y.get(j).equals("B") && Z.get(K+1).equals("b"))
							System.out.println("c"+i+" " + j + " "+(K+1));
						outputs.add(Collections.unmodifiableCollection(output));
					}
			}
		}

		if (I + 1 < X.size() && J + 1 < Y.size()) {
			if (test) {
				Collection<T> z = Z.subList(0, Math.min(Math.min(I + 1, J + 1), Z.size()));
				//Collection<T> z = Z.subList(0, Math.min(Math.min(I , J ), Z.size())+1);
				Collection<Collection<T>> c = Itertools4JImpl.INSTANCE.product2(Arrays.asList(z), 1);
				for (Collection<T> C : c) {
					C.add(X.get(I + 1));
					C.add(Y.get(J + 1));
				}
				outputs.addAll(c);
			} else {
				for (int k = 0; k <= I && k <= J && k < Z.size(); k++) {
					output = Arrays.asList(X.get(I + 1), Y.get(J + 1), Z.get(k));
					if (X.get(I+1).equals("1") && Y.get(J+1).equals("B") && Z.get(k).equals("b"))
						System.out.println("d"+(I+1)+" " + (J+1) + " "+k);
					outputs.add(Collections.unmodifiableCollection(output));
				}
			}
		}

		if (I + 1 < X.size() && K + 1 < Z.size()) {
			if (test) {
				Collection<T> y = Y.subList(0, Math.min(Math.min(I + 1, K + 1), Y.size()));
				//Collection<T> y = Y.subList(0, Math.min(Math.min(I , K ), Y.size())+1);
				Collection<Collection<T>> c = Itertools4JImpl.INSTANCE.product2(Arrays.asList(y), 1);
				for (Collection<T> C : c) {
					C.add(X.get(I + 1));
					C.add(Z.get(K + 1));
				}
				outputs.addAll(c);
			} else {
				for (int j = 0; j <= I && j <= K && j < Y.size(); j++) {
					output = Arrays.asList(X.get(I + 1), Y.get(j), Z.get(K + 1));
					if (X.get(I+1).equals("1") && Y.get(j).equals("B") && Z.get(K+1).equals("b"))
						System.out.println("e"+(I+1)+" " + j + " "+(K+1));
					outputs.add(Collections.unmodifiableCollection(output));
				}
			}
		}

		if (J + 1 < Y.size() && K + 1 < Z.size()) {
			if (test) {
				Collection<T> x = X.subList(0, Math.min(Math.min(J + 1, K + 1), X.size()));
				//Collection<T> x = X.subList(0, Math.min(Math.min(J , K ), X.size())+1);
				Collection<Collection<T>> c = Itertools4JImpl.INSTANCE.product2(Arrays.asList(x), 1);
				for (Collection<T> C : c) {
					C.add(Y.get(J + 1));
					C.add(Z.get(K + 1));
				}
				outputs.addAll(c);
			} else {
				for (int i = 0; i <= J && i <= K && i < X.size(); i++) {
					output = Arrays.asList(X.get(i), Y.get(J + 1), Z.get(K + 1));
					if (X.get(i).equals("1") && Y.get(J+1).equals("B") && Z.get(K+1).equals("b"))
						System.out.println("f"+i+" " + (J+1) + " "+(K+1));
					outputs.add(Collections.unmodifiableCollection(output));
				}
			}
		}
	}

	/**
	 * def product(*args, **kwds): # product('ABCD', 'xy') --> Ax Ay Bx By Cx Cy Dx
	 * Dy # product(range(2), repeat=3) --> 000 001 010 011 100 101 110 111 pools =
	 * map(tuple, args) * kwds.get('repeat', 1) result = [[]] for pool in pools:
	 * result = [x+[y] for x in result for y in pool] for prod in result: yield
	 * tuple(prod)
	 * 
	 * @return
	 */


}
