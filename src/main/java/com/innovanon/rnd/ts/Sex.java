/**
 * 
 */
package com.innovanon.rnd.ts;

/**
 * A Sex Object
 * 
 * @author gouldbergstein
 *
 */
public class Sex {
	/**
	 * A Sex has-a discrete quantity;
	 */
	private int quantity;

	/**
	 * A Sex has-a type
	 */
	private Class<?> type;

	/**
	 * @param quantity
	 * @param type
	 */
	public Sex(int quantity, Class<?> type) {
		this.quantity = quantity;
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Sex [quantity=%s, type=%s]", quantity, type != null ? type.getSimpleName() : null);
	}
}
