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
package com.innovanon.rnd.struct.pair;

/**
 * @author gouldbergstein
 * 
 * @param <CAR> Contents of the Address part of Register number
 * @param <CDR> Contents of the Decrement part of Register number
 */
public class ImmutablePairImpl<CAR, CDR> extends PairImpl<CAR, CDR> implements ImmutablePair<CAR, CDR> {
	/**
	 * the car
	 * @see #CAR
	 */
	private CAR car;
	/**
	 * the cdr
	 * @see #CDR
	 */
	private CDR cdr;
	
	/**
	 * @param car the car to set
	 * @param cdr the cdr to set
	 */
	public ImmutablePairImpl(CAR car, CDR cdr) {
		this.car = car;
		this.cdr = cdr;
	}
	
	/**
	 * @return the the {@link #car}
	 * @see #CAR
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.innovanon.simon.struct.pair.Pair#getCAR()
	 */
	@Override
	public CAR getCar() {
		return car;
	}

	/**
	 * @return the {@link #cdr}
	 * @see #CDR
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.innovanon.simon.struct.pair.Pair#getCDR()
	 */
	@Override
	public CDR getCdr() {
		return cdr;
	}
}
