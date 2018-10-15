package com.innovanon.rnd.simon.pixabay;

import java.io.StringReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.persistence.jaxb.JAXBContextProperties;

import com.innovanon.rnd.net.ua.UserAgentSupplier;

public enum App {
	/* no instances */ ;
	public static void main(String... args) throws MalformedURLException, JAXBException {
		/*
		Map<String, Object> properties = new HashMap<String, Object>(2);
		properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
		properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
		properties.put(JAXBContextProperties.JSON_ATTRIBUTE_PREFIX, "@");
		JAXBContext jc = JAXBContext.newInstance(new Class[] { PixabayResult.class }, properties);
		Marshaller jaxbMarshaller = jc.createMarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		PixabayResult employee = new PixabayResult();
		employee.totalHits = 1;
		Hit hit = new Hit();
		hit.largeImageURL = "some url";
		employee.hits = Arrays.asList(hit);
		employee.total = 2;

		// Print JSON String to Console
		StringWriter sw = new StringWriter();
		jaxbMarshaller.marshal(employee, sw);
		System.out.println(sw.toString());

		Unmarshaller unmarshaller = jc.createUnmarshaller();
		StringReader sr = new StringReader(sw.toString());
		StreamSource ss = new StreamSource(sr);
		employee = (PixabayResult) unmarshaller.unmarshal(ss, PixabayResult.class).getValue();
		System.out.println(employee);
		 */
		
		String url0 = "https://pixabay.com/api/?q=test&lang=en&key=10271514-8569b7fee57ad9ffa63e21335";
		String url1 = "https://pixabay.com/api/?key=10271514-8569b7fee57ad9ffa63e21335&q=anemom%C3%A9trica&lang=es";
		Stream<String> urlStrings = Stream.of(url0, url1);
		Stream<URL> urls1 = urlStrings.map(x -> {
			try {
				return new URL(x);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new Error(e);
			}
		});
		Iterator<URL> urls1iter = urls1.iterator();
		Supplier<URL> urls0 = () -> urls1iter.next();

		URL url = new URL(url0);
		URL url2 = new URL(url1);
		Random random = new Random();
		Supplier<String> userAgents = new UserAgentSupplier(random);
		Supplier<Optional<Stream<URL>>> urls = new PixabayImageURLSupplier(urls0, userAgents);
		long maxSize = 10;
		Stream.generate(urls).filter(o->o.isPresent()).map(o->o.get()).limit (2).reduce((a, b) -> Stream.concat(a, b)).get()
				.forEach(System.out::println);
	}
}
