/**
 * Om
 * Krim Krim Krim Hum Hum Hrim Hrim Daksine Kalike
 * Krim Krim Krim Hum Hum Hrim Hrim Svaha
 * 
 * InnovAnon, Inc. Proprietary
 * 
 * NOTICE:
 * All reproductions, total or partial, of this work must retain this notice.
 * 
 * Possession or use of this technology implies consent to our Terms of Service.
 * 
 * Owner and management NOT responsible for injury or death resulting from the use of this equipment.
 * 
 * If any clause in this notice is invalid or unenforceable in your jurisdiction, that does NOT necessarily invalidate any other clause in this notice.
 * 
 * Om
 * Krim Krim Krim Hum Hum Hrim Hrim Daksine Kalike
 * Krim Krim Krim Hum Hum Hrim Hrim Svaha
 */
package com.innovanon.rnd.struct.bag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.innovanon.rnd.at.Todo;

/**
 * @author gouldbergstein
 * @see java.util.ArrayList
 * @param <E> type of element contained within the bag
 */
public class BagImpl<E> implements Bag<E> {
	/**
	 * the underlying data structure: an ordered set, so that we might randomize that order
	 */
	private List<E> data;
	/**
	 * whether the queue has been randomized
	 */
	private boolean isShuffled;
	/**
	 * the source of entropy
	 */
	private Random random;

	/**
	 * @param initialCapacity the initial capacity for the underlying data structure
	 * @param random the random to set
	 * @see java.util.ArrayList
	 */
	public BagImpl(int initialCapacity, Random random) {
		data = new ArrayList<>(initialCapacity);
		this.random = random;
		this.isShuffled = true;
	}

	/**
	 * convenience constructor
	 * @param copy the data structure to copy
	 * @param random the random to set
	 * @see #BagImpl(int, Random)
	 * @see #addAll(Collection)
	 */
	public BagImpl(Collection<? extends E> copy, Random random) {
		this(copy.size(), random);
		addAll(copy);
	}
	
	/**
	 * convenience constructor
	 * @param copy the data structure to copy
	 * @param random the random to set
	 * @see #BagImpl(Collection, Random)
	 */
	@SafeVarargs
	public BagImpl(Random random, E...copy) {
		this(Arrays.asList(copy),random);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#size()
	 */
	@Override
	public int size() {
		return data.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return data.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object o) {
		return data.contains(o);
	}

	/**
	 * shuffles the queue if un-shuffled;
	 * performs the read operation;
	 * marks the queue as un-shuffled
	 * @see #isShuffled
	 * @see #random
	 * @see java.util.Collections#shuffle(List, Random)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#iterator()
	 */
	@Override
	public Iterator<E> iterator() {
		if (!isShuffled)
			Collections.shuffle(data, random);
		isShuffled = false;
		return data.iterator();
	}

	/**
	 * shuffles the queue if un-shuffled;
	 * performs the read operation;
	 * marks the queue as un-shuffled
	 * @see #isShuffled
	 * @see #random
	 * @see java.util.Collections#shuffle(List, Random)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#toArray()
	 */
	@Override
	public Object[] toArray() {
		if (!isShuffled)
			Collections.shuffle(data, random);
		isShuffled = false;
		return data.toArray();
	}

	/**
	 * shuffles the queue if un-shuffled;
	 * performs the read operation;
	 * marks the queue as un-shuffled
	 * @see #isShuffled
	 * @see #random
	 * @see java.util.Collections#shuffle(List, Random)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#toArray(java.lang.Object[])
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		if (!isShuffled)
			Collections.shuffle(data, random);
		isShuffled = false;
		return data.toArray(a);
	}

	/**
	 * performs the write operation;
	 * marks the queue as un-shuffled
	 * @see #isShuffled
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#add(java.lang.Object)
	 */
	@Override
	public boolean add(E e) {
		isShuffled = false;
		return data.add(e);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object o) {
		return data.remove(o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		return data.containsAll(c);
	}

	/**
	 * performs the write operation;
	 * marks the queue as un-shuffled
	 * @see #isShuffled
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends E> c) {
		isShuffled = false;
		return data.addAll(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		return data.removeAll(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		return data.retainAll(c);
	}

	/**
	 * performs the write operation;
	 * marks the queue as shuffled
	 * @see #isShuffled
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#clear()
	 */
	@Override
	public void clear() {
		data.clear();
		isShuffled = true;
	}

	/**
	 * @see #add(Object)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Queue#offer(java.lang.Object)
	 */
	@Todo("check capacity of underlying data structure and return true iff the element can be added without resizing")
	@Override
	public boolean offer(E e) {
		// TODO can't check capacity of arraylist
		return add(e);
	}

	/**
	 * shuffles the list if it is un-shuffled
	 * @return the last index in the list
	 * @see #data
	 * @see #isShuffled
	 * @see #random
	 * @see java.util.Collections#shuffle(List, Random)
	 */
	private int elementHelper() {
		if (!isShuffled)
			Collections.shuffle(data, random);
		return data.size() - 1;
	}

	/**
	 * @see #elementHelper()
	 * @see java.util.List#remove(int)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Queue#remove()
	 */
	@Override
	public E remove() {
		int index = elementHelper();
		return data.remove(index);
	}

	/**
	 * @see #remove()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Queue#poll()
	 */
	@Override
	public E poll() {
		if (isEmpty())
			return null;
		return remove();
	}

	/**
	 * @see #elementHelper()
	 * @see java.util.List#get(int)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Queue#element()
	 */
	@Override
	public E element() {
		int index = elementHelper();
		return data.get(index);
	}

	/**
	 * @see #element()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Queue#peek()
	 */
	@Override
	public E peek() {
		if (isEmpty())
			return null;
		return element();
	}

	/**
	 * @see #elementHelper()
	 * @see java.util.List#remove(int)
	 * @see java.util.List#add(Object)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.innovanon.simon.Simon.Bag#bulkRemove(int)
	 */
	@Override
	public Collection<E> bulkRemove(int amount) {
		int index = elementHelper();
		Collection<E> ret = new ArrayList<>(amount);
		for (int cnt = 1; cnt <= amount; cnt++, index--) {
			E elem = data.remove(index);
			ret.add(elem);
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.innovanon.simon.Simon.Bag#bulkRemove()
	 */
	@Override
	public Collection<E> bulkRemove() {
		int amount = random.nextInt(data.size());
		return bulkRemove(amount);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("BagImpl [isShuffled=%s, random=%s, data=%s]", isShuffled, random, data);
	}
}