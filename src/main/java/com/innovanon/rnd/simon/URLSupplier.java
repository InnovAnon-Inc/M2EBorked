/**
 * 
 */
package com.innovanon.rnd.simon;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;

import com.innovanon.rnd.net.nv.NameValuePairImpl;
import com.innovanon.rnd.struct.pair.ImmutablePairImpl;
import com.innovanon.rnd.struct.pair.Pair;
import com.innovanon.rnd.struct.stream.StreamUtil;
import com.innovanon.rnd.struct.subset.SubsetSupplier;

/**
 * @author gouldbergstein
 *
 */
public class URLSupplier implements Supplier<URL> {
	
	private String scheme, host, path;

	private Collection<?extends Supplier<NameValuePair>> nonOptionalParameters;

	private String qName;
	private Supplier<Pair<NameValuePair, String>> queries;

	private Supplier<Collection<Supplier<NameValuePair>>> optionalParameters;

	public URLSupplier(String scheme, String host, String path,
			Collection< Supplier<NameValuePair>> nonOptionalParameters,
			String qName,Supplier<Pair<NameValuePair, String>> queries,
			Collection<Supplier<NameValuePair>> optionalParameters,
			IntSupplier ns, Random random) {
		this.scheme=scheme;this.host=host;this.path=path;
		this.nonOptionalParameters = nonOptionalParameters;
		this.qName = qName;
		this.queries = StreamUtil.map(queries,
				s -> new ImmutablePairImpl<NameValuePair, String>(s.getCar(), String.join(" ", s.getCdr())));
		this.optionalParameters = new SubsetSupplier< Supplier<NameValuePair>>(optionalParameters, ns, random);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Supplier#get()
	 */
	@Override
	public URL get() {
		try {
			//System.out.println("URLSupplier.get()");
			// String key = String.format("%s=%s", "key", this.key);

			// Pair<QueryLang, Collection<String>> k = queries.get();
			Pair<NameValuePair, String> k = queries.get();
			// String q = String.format("%s=%s", "q",k.getCdr());
			// String lang = String.format("%s=%s", "lang", k.getCar().getValue());

			NameValuePair lang = k.getCar();
			NameValuePair query = new NameValuePairImpl(qName, k.getCdr());
			
			//System.out.println(query.getName());
			
			Collection<Supplier<NameValuePair>> ss = this.optionalParameters.get();
			List<NameValuePair> ss1 = new ArrayList<>(StreamUtil.map(ss, s -> s.get()));
			
			List<NameValuePair> ss2 = new ArrayList<>(StreamUtil.map(nonOptionalParameters, s -> s.get()));
			
			List<NameValuePair> nvps = new ArrayList<>(2 + ss1.size() + ss2.size());
			nvps.addAll(ss1);
			nvps.add(query);
			nvps.add(lang);
			nvps.addAll(ss2);

			URIBuilder builder = new URIBuilder();
			builder.setCharset(StandardCharsets.UTF_8);
			builder.setScheme(scheme);
			builder.setHost(host);
			builder.setPath(path);
			builder.setParameters(nvps);
			//builder.setParameter("key", nonOptionalParameters);
			//builder.setParameter("q", k.getCdr());
			//builder.setParameter("lang", k.getCar().getValue());
			URL ret = builder.build().toURL();
			return ret;
		} catch (MalformedURLException | URISyntaxException e) {
			// TODO Auto-generated catch block
			throw new Error(e);
		}
	}
}
