/**
 * 
 */
package com.innovanon.rnd.ts;

import java.util.Collection;
import java.util.Iterator;

/**
 * A Harem is-a Collection of Sex Objects
 * 
 * @author gouldbergstein
 *
 */
public class Harem implements Collection<Sex> {

	/**
	 * 
	 */
	private Collection<Sex> delegate;

	/**
	 * @param delegate
	 */
	public Harem(Collection<Sex> delegate) {
		this.delegate = delegate;
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
	 * @see java.util.Collection#iterator()
	 */
	public Iterator<Sex> iterator() {
		return delegate.iterator();
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
	public boolean add(Sex e) {
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
	public boolean addAll(Collection<? extends Sex> c) {
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
