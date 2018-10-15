/**
 * 
 */
package com.innovanon.rnd.net.ua;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

import javax.xml.bind.JAXBException;

/**
 * @author gouldbergstein
 *
 */
public enum App {
	/* no instances */ ;
	
	/**
	 * 
	 * @param args
	 * @throws JAXBException 
	 */
	public static void main (String... args) throws JAXBException {
		Random random=new Random();
		Supplier<String>userAgents = new UserAgentSupplier(random);
		long maxSize = 10;
		Stream.generate(userAgents).limit(maxSize).forEach(System.out::println);
	}
}
