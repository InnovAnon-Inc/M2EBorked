/**
 * 
 */
package com.innovanon.rnd.ts;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import com.innovanon.rnd.struct.bag.Bag;
import com.innovanon.rnd.struct.bag.BagImpl;

/**
 * A BagOfDicks is-a Bag of Dick Objects
 * 
 * @author gouldbergstein
 *
 */
public class BagOfDicks implements Bag<Dick> {
	/**
	 * 
	 */
	private Bag<Dick> delegate;

	/**
	 * 
	 * @param copy
	 * @param random
	 */
	public BagOfDicks(Collection<Dick> copy, Random random) {
		delegate = new BagImpl<>(copy, random);
	}

	/**
	 * @param amount
	 * @return
	 * @see com.innovanon.rnd.struct.bag.Bag#bulkRemove(int)
	 */
	public Collection<Dick> bulkRemove(int amount) {
		return delegate.bulkRemove(amount);
	}

	/**
	 * @return
	 * @see com.innovanon.rnd.struct.bag.Bag#bulkRemove()
	 */
	public Collection<Dick> bulkRemove() {
		return delegate.bulkRemove();
	}

	/**
	 * @param e
	 * @return
	 * @see java.util.Queue#offer(java.lang.Object)
	 */
	public boolean offer(Dick e) {
		return delegate.offer(e);
	}

	/**
	 * @return
	 * @see java.util.Collection#size()
	 */
	public int size() {
		return delegate.size();
	}

	/**
	 * @return
	 * @see java.util.Collection#isEmpty()
	 */
	public boolean isEmpty() {
		return delegate.isEmpty();
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.Collection#contains(java.lang.Object)
	 */
	public boolean contains(Object o) {
		return delegate.contains(o);
	}

	/**
	 * @return
	 * @see java.util.Queue#remove()
	 */
	public Dick remove() {
		return delegate.remove();
	}

	/**
	 * @return
	 * @see java.util.Queue#poll()
	 */
	public Dick poll() {
		return delegate.poll();
	}

	/**
	 * @return
	 * @see java.util.Queue#element()
	 */
	public Dick element() {
		return delegate.element();
	}

	/**
	 * @return
	 * @see java.util.Collection#iterator()
	 */
	public Iterator<Dick> iterator() {
		return delegate.iterator();
	}

	/**
	 * @return
	 * @see java.util.Queue#peek()
	 */
	public Dick peek() {
		return delegate.peek();
	}

	/**
	 * @return
	 * @see java.util.Collection#toArray()
	 */
	public Object[] toArray() {
		return delegate.toArray();
	}

	/**
	 * @param a
	 * @return
	 * @see java.util.Collection#toArray(java.lang.Object[])
	 */
	public <T> T[] toArray(T[] a) {
		return delegate.toArray(a);
	}

	/**
	 * @param e
	 * @return
	 * @see java.util.Collection#add(java.lang.Object)
	 */
	public boolean add(Dick e) {
		return delegate.add(e);
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.Collection#remove(java.lang.Object)
	 */
	public boolean remove(Object o) {
		return delegate.remove(o);
	}

	/**
	 * @param c
	 * @return
	 * @see java.util.Collection#containsAll(java.util.Collection)
	 */
	public boolean containsAll(Collection<?> c) {
		return delegate.containsAll(c);
	}

	/**
	 * @param c
	 * @return
	 * @see java.util.Collection#addAll(java.util.Collection)
	 */
	public boolean addAll(Collection<? extends Dick> c) {
		return delegate.addAll(c);
	}

	/**
	 * @param c
	 * @return
	 * @see java.util.Collection#removeAll(java.util.Collection)
	 */
	public boolean removeAll(Collection<?> c) {
		return delegate.removeAll(c);
	}

	/**
	 * @param c
	 * @return
	 * @see java.util.Collection#retainAll(java.util.Collection)
	 */
	public boolean retainAll(Collection<?> c) {
		return delegate.retainAll(c);
	}

	/**
	 * 
	 * @see java.util.Collection#clear()
	 */
	public void clear() {
		delegate.clear();
	}
}
