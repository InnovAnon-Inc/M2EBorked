/**
 * 
 */
package com.innovanon.rnd.net.url_conn;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.function.Supplier;

import com.innovanon.rnd.at.Todo;

/**
 * @author gouldbergstein
 *
 */
public class URLConnectionSupplier implements Supplier<URL> {

	/**
	 * 
	 */
	private Supplier<URL> urls;
	/**
	 * 
	 */
	private Supplier<String> userAgents;

	/**
	 * @param urlspecs
	 * @param userAgents
	 */
	public URLConnectionSupplier(Supplier<URL> urlspecs, Supplier<String> userAgents) {
		this.urls = urlspecs;
		this.userAgents = userAgents;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Supplier#get()
	 */
	@Override
	@Todo("specialized errors")
	public URL get() {
		/*String urlspec = urlspecs.get();
		URL url;
		try {
			url = new URL(urlspec);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			throw new Error(e);
		}*/
		URL url = urls.get();
		URLConnection connection;
		try {
			connection = url.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new Error(e);
		}
		String userAgent = userAgents.get();
		connection.setRequestProperty("User-Agent", userAgent);
		return url;
	}
}
