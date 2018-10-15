/**
 * 
 */
package com.innovanon.rnd.ts;

import com.innovanon.rnd.struct.pair.ImmutablePairImpl;
import com.innovanon.rnd.struct.pair.Pair;

/**
 * A Boobs is-a Pair of Boob Objects
 * 
 * @author gouldbergstein
 *
 */
public class Boobs implements Pair<Boob, Boob> {

	/**
	 * 
	 */
	private static Boobs instance = new Boobs();

	/**
	 * 
	 * @return
	 */
	public static Boobs getBoob() {
		return instance;
	}

	/**
	 * 
	 */
	private Pair<Boob, Boob> delegate;

	/**
	 * 
	 * @param left
	 * @param right
	 */
	public Boobs(Boob left, Boob right) {
		delegate = new ImmutablePairImpl<Boob, Boob>(left, right);
	}

	/**
	 * 
	 */
	public Boobs() {
		this(Boob.getBoob(), Boob.getBoob());
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return String.format("%s%s", getCar(), getCdr());
	}

	/**
	 * @return
	 * @see com.innovanon.rnd.struct.pair.Pair#getCar()
	 */
	public Boob getCar() {
		return delegate.getCar();
	}

	/**
	 * @return
	 * @see com.innovanon.rnd.struct.pair.Pair#getCdr()
	 */
	public Boob getCdr() {
		return delegate.getCdr();
	}
}
