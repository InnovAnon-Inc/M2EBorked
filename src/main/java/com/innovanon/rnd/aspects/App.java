package com.innovanon.rnd.aspects;

import java.io.PrintStream;
import java.net.MalformedURLException;

import javax.xml.bind.JAXBException;

import com.innovanon.rnd.ts.HelloWorld;

/**
 * Hello world!
 *
 */
public enum App implements Runnable {
	/**
	 * 
	 */
	INSTANCE(System.out, "hello", "world", false);

	/**
	 * 
	 */
	private PrintStream out;
	/**
	 * 
	 */
	private HelloWorld hw;

	/**
	 * 
	 * @param out
	 * @param hello
	 * @param world
	 * @param isProperNoun
	 */
	App(PrintStream out, String hello, String world, boolean isProperNoun) {
		this.out = out;
		this.hw = new HelloWorld(hello, world, isProperNoun);
	}

	/**
	 * 
	 */
	@Override
	public void run() {
		out.println(hw);
	}

	/**
	 * 
	 * @param args
	 * @throws JAXBException 
	 * @throws MalformedURLException 
	 */
	public static void main(String... args) throws MalformedURLException, JAXBException {
		//App.INSTANCE.run();
		com.innovanon.rnd.simon.pixabay.App.main(args);
	}
}
