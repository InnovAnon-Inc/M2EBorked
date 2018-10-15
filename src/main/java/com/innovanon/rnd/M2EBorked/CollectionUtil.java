/**
 * 
 */
package com.innovanon.rnd.M2EBorked;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.innovanon.rnd.struct.pair.ImmutablePairImpl;
import com.innovanon.rnd.struct.pair.Pair;

/**
 * @author gouldbergstein
 *
 */
public enum CollectionUtil {
	/* no instances */ ;
	
	public static <T> Pair<T, Collection<T>> remove(Collection<T> wheel) {
		// Collection<T> c = new ArrayList<>(wheel);
		// Iterator<T> i = c.iterator();
		// T next = i.next();
		// i.remove();
		List<T> c = new ArrayList<>(wheel);
		T next = c.remove(0);
		return new ImmutablePairImpl<>(next, c);
	}

	public static <T> T remove2(Collection<T> wheel) {
		return remove(wheel).getCar();
	}

	public static <K, V> K remove2(Map<K, V> wheel) {
		return remove(wheel).getKey();
	}

	public static <K, V> Pair<Map<K, V>, Entry<K, V>> remove3(Map<K, V> pM) {
		Map<K, V> m = new HashMap<>(pM.size());
		m.putAll(pM);
		Iterator<Entry<K, V>> i = m.entrySet().iterator();
		Entry<K, V> e = i.next();
		i.remove();
		return new ImmutablePairImpl<Map<K, V>, Map.Entry<K, V>>(m, e);
	}
	
	public static <K, V> Map<K, V> add(Map<K, V> left, Entry<K, V> entry) {
		Map<K, V> m = copy(left);
		m.put(entry.getKey(), entry.getValue());
		return m;
	}

	public static <K, V> Map<K, V> remove(Map<K, V> left, Entry<K, V> entry) {
		Map<K, V> m = copy(left);
		m.remove(entry.getKey());
		return m;
	}

	public static <K, V> Map<K, V> copy(Map<K, V> pLeft) {
		Map<K, V> m = new HashMap<>(pLeft.size());
		m.putAll(pLeft);
		return m;
	}
	
	public static <T> Collection<T> add(Collection<T> c, T t) {
		Collection<T> ret = new ArrayList<>(c.size() + 1);
		ret.addAll(c);
		ret.add(t);
		return ret;
	}

	public static <K, V> Map<K, V> add(Map<K, V> c, K k, V v) {
		Map<K, V> ret = new HashMap<>(c.size() + 1);
		ret.putAll(c);
		ret.put(k, v);
		return ret;
	}

	public static <K, V> Map<K, V> remove(Map<K, V> c, K k) {
		Map<K, V> ret = new HashMap<>(c.size());
		ret.putAll(c);
		ret.remove(k);
		return ret;
	}

	public static <K, V> Entry<K, V> remove(Map<K, V> c) {
		return remove2(c.entrySet());
		// Iterator<Entry<K,V>> i = c.entrySet().iterator();
	}

	public static <T> Collection<T> remove(Collection<T> c, T t) {
		Collection<T> vars2 = new ArrayList<>(c);
		vars2.remove(t);
		return vars2;
	}

	public static <T> Collection<T> union(Collection<T> a, Collection<T> b) {
		Collection<T> c = new ArrayList<>(a.size() + b.size());
		c.addAll(a);
		c.addAll(b);
		return c;
	}
	
	public static <T> Pair<T, Collection<T>> removeLast(Collection<T> cc) {
		List<T> ret = new ArrayList<>(cc);
		T rem = ret.remove(ret.size() - 1);
		return new ImmutablePairImpl<T, Collection<T>>(rem, ret);
	}
}
