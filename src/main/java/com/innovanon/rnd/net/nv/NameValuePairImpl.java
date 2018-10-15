/**
 * 
 */
package com.innovanon.rnd.net.nv;

import org.apache.http.NameValuePair;

/**
 * @author gouldbergstein
 *
 */
public class NameValuePairImpl implements NameValuePair {

	private String name;
	private String value;

	/**
	 * @param name
	 * @param value
	 */
	public NameValuePairImpl(String name, String value) {
		this.name = name;
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.http.NameValuePair#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.http.NameValuePair#getValue()
	 */
	@Override
	public String getValue() {
		return value;
	}

}