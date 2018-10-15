/**
 * 
 */
package com.innovanon.rnd.net.ua;

import java.util.Iterator;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

import javax.xml.bind.JAXBException;

import com.innovanon.rnd.at.Todo;
import com.innovanon.rnd.struct.bag.BagUtil;
import com.innovanon.rnd.util.StringUtil;

/**
 * @author gouldbergstein
 *
 */
public class UserAgentSupplier implements Supplier<String> {
	
	private Iterator<String> iter;

	@Todo("more reasonable resource management")
	public UserAgentSupplier(Random random) throws JAXBException {
		//Stream<String> agents = UserAgentUtil.getUserAgents("allagents.xml");
		Stream<String> agents = UserAgentUtil.getUserAgents(StringUtil.packageToPath (getClass().getPackage()) +  "allagents.xml");
		//assert !agents.isEmpty();
		Stream<String> rAgents = BagUtil.getRandomStream(agents, random);
		iter = rAgents.iterator();
		// this.userAgents = new BagImpl<>(agents,random);
		//Iterable<String> iterable = new BagImpl<>(agents, random);
		//iter = new Reiterator<>(iterable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Supplier#get()
	 */
	@Override
	public String get() {
		return iter.next();
	}
}
