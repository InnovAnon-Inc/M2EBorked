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
 * @param <CPR> Contents of the Prefix part of Register number
 * @param <CTR> Contents of the Tag part of Register number
 */
public class RegisterNumberImpl<CAR, CDR, CPR, CTR> extends ImmutablePairImpl<Pair<CAR, CDR>, Pair<CPR, CTR>>
		implements RegisterNumber<CAR, CDR, CPR, CTR> {
	/**
	 * @param car the car to set
	 * @param cdr the cdr to set
	 * @see ImmutablePairImpl#ImmutablePairImpl(Object, Object)
	 */
	public RegisterNumberImpl(Pair<CAR, CDR> car, Pair<CPR, CTR> cdr) {
		super(car, cdr);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.innovanon.struct.Structs.pair.RegisterNumber#getCaar()
	 */
	@Override
	public CAR getCaar() {
		return getCar().getCar();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.innovanon.struct.Structs.pair.RegisterNumber#getCadr()
	 */
	@Override
	public CDR getCadr() {
		return getCar().getCdr();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.innovanon.struct.Structs.pair.RegisterNumber#getCdar()
	 */
	@Override
	public CPR getCdar() {
		return getCdr().getCar();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.innovanon.struct.Structs.pair.RegisterNumber#getCddr()
	 */
	@Override
	public CTR getCddr() {
		return getCdr().getCdr();
	}
}
