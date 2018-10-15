/**
 * 
 */
package com.innovanon.rnd.net.ua;

import java.net.URL;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.javasync.streams.Replayer;

import com.innovanon.rnd.at.Todo;
import com.innovanon.rnd.struct.memo.Memoizer;

/**
 * @author gouldbergstein
 *
 */
public enum UserAgentUtil {
	/* no instances */ ;

	/**
	 * 
	 */
	@Todo("specialized errors")
	private static Function<String, Supplier<Stream<String>>> userAgents = Memoizer.memoize(uax -> {
		try {
			return Replayer.replay(getUserAgentsHelper (uax));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			throw new Error (e);
		}
	});
	
	public static Stream<String> getUserAgents (String userAgentsXml) {
		return userAgents.apply(userAgentsXml).get();
	}

	/**
	 * 
	 * @return
	 * @throws JAXBException
	 */
	private static Stream<String> getUserAgentsHelper(String userAgentsXml) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(UserAgents.class);
		Unmarshaller jaxbMarshaller = jaxbContext.createUnmarshaller();

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//System.out.println(userAgentsXml);
		URL url = classloader.getResource(userAgentsXml);
//System.out.println(url);
		UserAgents ua = (UserAgents) jaxbMarshaller.unmarshal(url);
		List<UserAgent> list = ua.userAgents;
		assert list != null;
		//List<String> temp = list.stream().map(u -> u.string).collect(Collectors.toList());
		//userAgents = Collections.unmodifiableList(temp);
		//return userAgents;
		Stream<String> s = list.stream().map(u -> u.string);
		//return Replayer.replay(s);
		return s;
	}
}
