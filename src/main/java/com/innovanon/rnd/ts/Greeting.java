/**
 * 
 */
package com.innovanon.rnd.ts;

import java.util.Objects;

/**
 * @author gouldbergstein
 *
 */
public class Greeting {
	/**
	 * 
	 */
	private String fmt;
	/**
	 * 
	 */
	private String subject;

	/**
	 * @param fmt
	 * @param subject
	 */
	public Greeting(String fmt, String subject) {
		Objects.requireNonNull(fmt);
		this.fmt = fmt;
		this.subject = subject;
	}

	/**
	 * 
	 * @param subject
	 */
	public Greeting(String subject) {
		this("Hello, %s!", subject);
	}

	/**
	 * 
	 */
	public Greeting() {
		this("World");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		assert fmt != null;
		System.out.println("fmt="+fmt);
		System.out.println("subj="+subject);
		return String.format(fmt, subject);
	}
}
