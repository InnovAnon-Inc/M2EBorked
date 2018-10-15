/**
 * 
 */
package com.innovanon.rnd.io;

import java.io.IOException;
import java.io.Reader;

/**
 * @author gouldbergstein
 *
 */
public enum Restart {
	/* no instances */ ;

	/**
	 * http://www.cs.utsa.edu/~srobbins/web/usp/programs/chapter04/r_read.c
	 * 
	 * @param input
	 * @param cbuf
	 * @param off
	 * @return
	 * @throws IOException
	 */
	public static int r_read(Reader input, char[] cbuf, int off) throws IOException {
		int len = cbuf.length;
		int rd;
		do {
			rd = input.read(cbuf, off, len);
			if (rd <= 0)
				break;
			off += rd;
			len -= rd;
		} while (true);
		return off;
	}

	public static int r_read(Reader input, char[] cbuf) throws IOException {
		return r_read(input, cbuf, 0);
	}
}
