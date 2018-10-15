/**
 * 
 */
package com.innovanon.rnd.simon.pixabay;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

import javax.xml.bind.JAXBException;

import com.innovanon.rnd.net.ua.UserAgentSupplier;
import com.innovanon.rnd.simon.pixabay.query.PixabayURLSupplier;

/**
 * @author gouldbergstein
 *
 */
public class PixabayImageURLSupplier implements Supplier<Optional<Stream<URL>>> {

	private Supplier<URL> urls;
	private Supplier<String> userAgents;

	
	/**
	 * @param urls
	 */
	public PixabayImageURLSupplier(Supplier<URL> urls, Supplier<String>userAgents) {
		this.urls = urls;
		this.userAgents=userAgents;
	}
	

	public PixabayImageURLSupplier(Random random) throws IOException, JAXBException {
		this(new PixabayURLSupplier(random), new UserAgentSupplier(random) );
	}

	@Override
	public Optional<Stream<URL>> get() {
		Optional<Stream<URL>> ret;
		//do {
			URL url = urls.get();
//System.out.println("url: "+url);
			String userAgent = userAgents.get();
			URLConnection c;
			try {
				c = url.openConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
throw new Error(e);
			}
			c.setRequestProperty("User-Agent", userAgent);
			ret = PixabayURLUtil.getInstance().apply(c);
		//} while (ret == null);
		return ret;
	}
}
