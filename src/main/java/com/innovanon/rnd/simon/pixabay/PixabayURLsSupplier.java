/**
 * 
 */
package com.innovanon.rnd.simon.pixabay;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

import javax.xml.bind.JAXBException;

import com.innovanon.rnd.at.Todo;
import com.innovanon.rnd.net.ua.UserAgentSupplier;
import com.innovanon.rnd.simon.pixabay.query.PixabayURLSupplier;

/**
 * @author gouldbergstein
 *
 */
public class PixabayURLsSupplier implements Supplier<Optional<URL>> {

	private Supplier<Optional<Stream<URL>>> urls;

	/**
	 * @param urls0 
	 * @param userAgents 
	 * @param urls
	 */
	public PixabayURLsSupplier(Supplier<URL> urls0, Supplier<String> userAgents) {
		this.urls = new PixabayImageURLSupplier(urls0, userAgents);
	}
	
	public PixabayURLsSupplier(Random random) throws IOException, JAXBException {
this (new PixabayURLSupplier(random), new UserAgentSupplier(random));
	}

	@Override
	@Todo("maybe be less wasteful")
	public Optional<URL> get() {
		Optional<Stream<URL>> sss = urls.get();
		if ( !sss.isPresent())return Optional.empty();
		return Optional.of(sss.get().findFirst().get());
	}

}
