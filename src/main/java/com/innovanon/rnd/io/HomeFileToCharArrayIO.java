/**
 * 
 */
package com.innovanon.rnd.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import com.innovanon.rnd.at.Todo;
import com.innovanon.rnd.util.StringUtil;

/**
 * @author gouldbergstein
 *
 */
public class HomeFileToCharArrayIO extends IO2<String,char[]>{
	
	private static IO<String,char[],char[]> instance;
	
	public static IO<String,char[],char[]>getInstance(){
		if (instance==null)instance = new HomeFileToCharArrayIO();
		return instance;
	}
	
	private HomeFileToCharArrayIO() {}
	
	@Todo("clean this up")
	@Todo("specialized errors")
	private static char[] getDataHelper(File file) {
		FileReader fis;
		try {
			fis = new FileReader(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new Error(e);
		}
		BufferedReader input = null;

		long length = file.length();
		assert length <= Integer.MAX_VALUE;
		// cbuf = CharBuffer.allocate((int) length);
		int len = (int) length;
		int off;
		char[] cbuf = new char[len];

		try {
			input = new BufferedReader(fis);
			off = Restart.r_read(input, cbuf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new Error(e);
		} finally {
			if (input != null)
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}
		}
		// if (off != 0 && Character.isWhitespace(cbuf[off-1]))
		// off--;
		// char[] ret = new char[off];
		// System.arraycopy(cbuf, 0, ret, 0, off);
		//// assert !String.valueOf(ret).contains("\n");
		//// assert !String.valueOf(ret).contains("\0");
		// System.out.println((int)(ret[off-1]));
		// return ret;
		return StringUtil.trim(cbuf, 0, off - 1);
	}


	@Override
	protected Optional<char[]> outputHelper(Optional<char[]> input) {
		if (!input.isPresent())return input.empty();
		char[] ret =input.get().clone();
		return Optional.of(ret);
	}

	@Override
	protected Optional<char[]> inputHelper(String input) {
		return Optional.of(getDataHelper(new File(System.getProperty("user.home"),input)));
	}
}
