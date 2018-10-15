/**
 * 
 */
package com.innovanon.rnd.struct.pe;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author gouldbergstein
 */
@Deprecated
public class PluggableEngine<T> implements Collection<T> {
	/**
	 * 
	 */
	private List<Set<T>> plugins;
	
	

	/**
	 * @param plugins
	 */
	public PluggableEngine(List<Set<T>> plugins) {
		this.plugins = plugins;
	}

	/**
	 * @return
	 * @see java.util.Collection#stream()
	 */
	@Override
	public Stream<T> stream() {
		
		return plugins.stream().map(new Function<Set<T>, Stream<T>>() {
			@Override
			public Stream<T> apply(Set<T> t) {
				// TODO randomize
				return t.stream();
			}
		}).reduce(Stream.empty(), Stream::concat);
		/*
		return plugins.stream().map(new Function<Set<T>, Stream<T>>() {
			@Override
			public Stream<T> apply(Set<T> t) {
				// TODO randomize
				return t.stream();
			}
		}).reduce(Stream.empty(), new BinaryOperator<Stream<T>>() {

			@Override
			public Stream<T> apply(Stream<T> t, Stream<T> u) {
				return Stream.concat(t,u);
			}
			
		});
		*/
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#size()
	 */
	@Override
	public int size() {
		// return stream().mapToInt(new ToIntFunction<Stream<T>>() {
		// @Override
		// public int applyAsInt(Stream<T> value) {
		// // TODO Auto-generated method stub
		// return 0;
		// }
		// }).sum();
		throw new Error();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		throw new Error();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		throw new Error();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#iterator()
	 */
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		throw new Error();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#toArray()
	 */
	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		throw new Error();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#toArray(java.lang.Object[])
	 */
	@Override
	public <E> E[] toArray(E[] a) {
		// TODO Auto-generated method stub
		throw new Error();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#add(java.lang.Object)
	 */
	@Override
	public boolean add(T e) {
		// TODO Auto-generated method stub
		throw new Error();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		throw new Error();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		throw new Error();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		throw new Error();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		throw new Error();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		throw new Error();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#clear()
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		throw new Error();
	}
}
