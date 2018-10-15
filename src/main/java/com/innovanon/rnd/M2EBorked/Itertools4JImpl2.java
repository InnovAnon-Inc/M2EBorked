/**
 * 
 */
package com.innovanon.rnd.M2EBorked;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.innovanon.rnd.struct.pair.ImmutablePairImpl;
import com.innovanon.rnd.struct.pair.Pair;


/**
 * @author gouldbergstein
 *
 */
public enum Itertools4JImpl2 {
	INSTANCE;
	
	public<T> int size (Collection<List<T>> c) {
		return c.stream().map(x -> x.size()).reduce((a, b)->a+b).get();
	}
	
	public <T> int maxSize (Collection<List<T>> c) {
		return c.stream().map(x->x.size()).max(Comparator.naturalOrder()).get();
	}
	
	public <K,V> Map<K,V> zip (Collection<K>c,Collection<V> i) {
		Map<K,V>m = new HashMap<K,V>(c.size());
		assert c.size()==i.size();
		Iterator<K>ci = c.iterator();
		Iterator<V>ii = i.iterator();
		for (int j = 0; j < c.size(); j++) {
			K key = ci.next();
			V value = ii.next();
			m.put(key, value);
		}
		return m;
	}
	
	public void increment (int[] a) {
		for (int i = 0; i < a.length; i++)
			a[i]++;
	}
	
	public <T> Collection<Collection<T>> product (Collection<List<T>> c) {
		int[] IJK = new int[c.size()];
		//Arrays.fill(IJK, 0);

		Collection<T> output;
		Collection<Collection<T>> outputs = new ArrayList<>(size(c));
		int maxSize = maxSize(c);
		
		//Collection<T> output;
		{
		Map<List<T>, Integer>m=zip (c, toCollection(IJK));
		if (lessThan(m)) {
			output = get(m);
			outputs.add(Collections.unmodifiableCollection(output));
		}
		}
		
		for (int outer = 0; outer < maxSize; outer++) {
			Map<List<T>, Integer>m=zip (c, toCollection(IJK));
	if (m.isEmpty())throw new Error();	
			body(outputs, m);

			increment(IJK);
		}
		
		Map<List<T>, Integer>m=zip (c, toCollection(IJK));
		if (lessThan(m)) {
			output = get(m);
			outputs.add(Collections.unmodifiableCollection(output));
		}
		
		return outputs;
	}
	
	public<T> boolean lessThan (Map<List<T>, Integer>m) {
		return m.entrySet().stream().allMatch(x -> x.getValue()<x.getKey().size());
	}
	
	public <T> void body(Collection<Collection<T>> outputs,Map<List<T>, Integer>m) {
		Collection<T> output;
		/*
		if (lessThan(m)) {
			output = get(m);
			outputs.add(Collections.unmodifiableCollection(output));
		}
*/
		Collection<Pair<Collection<Entry<List<T>, Integer>>,Collection<Entry<List<T>, Integer>>>> combos = 
				combinations(m.entrySet());
		for (Pair<Collection<Entry<List<T>, Integer>>, Collection<Entry<List<T>, Integer>>> combo : combos) {
			Collection<Entry<List<T>, Integer>> fixed = combo.getCar();
			Collection<Entry<List<T>, Integer>> variable = combo.getCdr();
			if (! lessThan2 (fixed)) continue;
			Collection<Collection<T>> sublists = sublists (combo);
			Collection<Collection<T>> c = Itertools4JImpl.INSTANCE.product2(sublists);
			for (Collection<T> C : c)
				for (Entry<List<T>, Integer> f:fixed)
					C.add(f.getKey().get(f.getValue() + 1));
			outputs.addAll(c);
		}
		
		/*
		if (I + 1 < X.size()) {
				Collection<T> y = Y.subList(0, Math.min(I + 1, Y.size()));
				//Collection<T> y = Y.subList(0, Math.min(I, Y.size())+1);
				Collection<T> z = Z.subList(0, Math.min(I + 1, Z.size()));
				//Collection<T> z = Z.subList(0, Math.min(I , Z.size())+1);
				Collection<Collection<T>> c = Itertools4JImpl.INSTANCE.product2(Arrays.asList(y, z), 1);
				for (Collection<T> C : c)
					C.add(X.get(I + 1));
				outputs.addAll(c);
		}

		if (J + 1 < Y.size()) {
				Collection<T> x = X.subList(0, Math.min(J + 1, X.size()));
				//Collection<T> x = X.subList(0, Math.min(J, X.size())+1);
				Collection<T> z = Z.subList(0, Math.min(J + 1, Z.size()));
				//Collection<T> z = Z.subList(0, Math.min(J , Z.size())+1);
				Collection<Collection<T>> c = Itertools4JImpl.INSTANCE.product2(Arrays.asList(x, z), 1);
				for (Collection<T> C : c)
					C.add(Y.get(J + 1));
				outputs.addAll(c);
		}

		if (K + 1 < Z.size()) {
				Collection<T> y = Y.subList(0, Math.min(K + 1, Y.size()));
				//Collection<T> y = Y.subList(0, Math.min(K, Y.size())+1);
				Collection<T> x = X.subList(0, Math.min(K + 1, X.size()));
				//Collection<T> x = X.subList(0, Math.min(K , X.size())+1);
				Collection<Collection<T>> c = Itertools4JImpl.INSTANCE.product2(Arrays.asList(x, y), 1);
				for (Collection<T> C : c)
					C.add(Z.get(K + 1));
				outputs.addAll(c);
		}

		if (I + 1 < X.size() && J + 1 < Y.size()) {
				Collection<T> z = Z.subList(0, Math.min(Math.min(I + 1, J + 1), Z.size()));
				//Collection<T> z = Z.subList(0, Math.min(Math.min(I , J ), Z.size())+1);
				Collection<Collection<T>> c = Itertools4JImpl.INSTANCE.product2(Arrays.asList(z), 1);
				for (Collection<T> C : c) {
					C.add(X.get(I + 1));
					C.add(Y.get(J + 1));
				}
				outputs.addAll(c);
		}

		if (I + 1 < X.size() && K + 1 < Z.size()) {
				Collection<T> y = Y.subList(0, Math.min(Math.min(I + 1, K + 1), Y.size()));
				//Collection<T> y = Y.subList(0, Math.min(Math.min(I , K ), Y.size())+1);
				Collection<Collection<T>> c = Itertools4JImpl.INSTANCE.product2(Arrays.asList(y), 1);
				for (Collection<T> C : c) {
					C.add(X.get(I + 1));
					C.add(Z.get(K + 1));
				}
				outputs.addAll(c);
		}

		if (J + 1 < Y.size() && K + 1 < Z.size()) {
				Collection<T> x = X.subList(0, Math.min(Math.min(J + 1, K + 1), X.size()));
				//Collection<T> x = X.subList(0, Math.min(Math.min(J , K ), X.size())+1);
				Collection<Collection<T>> c = Itertools4JImpl.INSTANCE.product2(Arrays.asList(x), 1);
				for (Collection<T> C : c) {
					C.add(Y.get(J + 1));
					C.add(Z.get(K + 1));
				}
				outputs.addAll(c);
		}
		*/
	}
	
	private <T> Collection<Collection<T>> sublists(
			Pair<Collection<Entry<List<T>, Integer>>, Collection<Entry<List<T>, Integer>>> combo) {
		// Collection<T> y = Y.subList(0, Math.min(I + 1, Y.size()));
		// Collection<T> z = Z.subList(0, Math.min(I + 1, Z.size()));
		Collection<Entry<List<T>, Integer>> fixed = combo.getCar();
		Collection<Entry<List<T>, Integer>> variable = combo.getCdr();
		Collection<Collection<T>>ret = new ArrayList<>(variable.size());
		int min = min (fixed);
		for (Entry<List<T>,Integer>var:variable) {
			List<T>v = var.getKey();
			List<T>V = new ArrayList<>(v.subList(0, Math.min(min, v.size())));
			ret.add(V);
		}
		return ret;
	}

	private<T> int min(Collection<Entry<List<T>, Integer>> fixed) {
		return fixed.stream().map(x->x.getValue()).min(Comparator.naturalOrder()).get()+1;
	}

	private <T>Collection<Pair<Collection<Entry<List<T>, Integer>>, Collection<Entry<List<T>, Integer>>>> combinations(
			Set<Entry<List<T>, Integer>> entrySet) {
		List<Entry<List<T>,Integer>> es = new ArrayList<>(entrySet.size());
		es.addAll(entrySet);
		Collection<Collection<Entry<List<T>, Integer>>>S = Itertools4JImpl.INSTANCE.combinations(es);
		Collection<Pair<Collection<Entry<List<T>, Integer>>, Collection<Entry<List<T>, Integer>>>>ret = new ArrayList<>(S.size());
		for (Collection<Entry<List<T>,Integer>> SS:S) {
		Set<Entry<List<T>,Integer>>s = new HashSet<Entry<List<T>,Integer>>(entrySet.size());
		s.addAll(entrySet);
		s.removeAll(SS);
		Pair<Collection<Entry<List<T>, Integer>>, Collection<Entry<List<T>, Integer>>> p=new ImmutablePairImpl<>(SS,s);
		ret.add(p);
		}
		return ret;
	}

	private<T> boolean lessThan2(Collection<Entry<List<T>, Integer>> combo) {
		return combo.stream().allMatch(x -> x.getValue()+1<x.getKey().size());
	}

	public <T>Collection<T> get(Map<List<T>,Integer>m){
		Collection<T>ret = new ArrayList<>(m.size());
		for (Entry<List<T>,Integer>M:m.entrySet()) {
			Integer key = M.getValue();
			List<T> value = M.getKey();
			T e = value.get(key);
			ret.add(e );
		}
		return ret;
	}

	public Collection<Integer> toCollection(int[] IJK) {
		Collection<Integer>c = new ArrayList<>(IJK.length);
		for (int i : IJK)
			c.add(i);
		return c;
	}
}
