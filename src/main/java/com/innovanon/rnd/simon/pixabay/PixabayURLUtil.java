/**
 * 
 */
package com.innovanon.rnd.simon.pixabay;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.codehaus.plexus.util.IOUtil;
import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.javasync.streams.Replayer;

import com.innovanon.rnd.at.Todo;
import com.innovanon.rnd.io.IOStream;
import com.innovanon.rnd.struct.memo.Memoizer;
import com.innovanon.rnd.struct.pair.ImmutablePairImpl;
import com.innovanon.rnd.struct.pair.Pair;

/**
 * @author gouldbergstein
 *
 */
public class PixabayURLUtil extends IOStream<URLConnection, URL>{
	
	private static IOStream<URLConnection,URL>instance;
	public static IOStream<URLConnection,URL> getInstance () {
		if(instance==null)instance = new PixabayURLUtil();
		return instance;
	}
	
	private PixabayURLUtil () {
		
	}
	
	
	/* no instances */ ;

	/**
	 * 
	 */
	/*
	@Todo("specialized errors")
	private static Function<Pair<URL,String>, Supplier<Stream<URL>>> userAgents = Memoizer.memoize(uax -> {
		try {
			return Replayer.replay(getUserAgentsHelper (uax));
		} catch (JAXBException | IOException e) {
			// TODO Auto-generated catch block
			throw new Error (e);
		}
	});
	*/
	/*
	public static Stream<URL> getImageURLs (URL url, String userAgent) {
		return userAgents.apply(new ImmutablePairImpl<URL, String>(url,userAgent)).get();
	}
*/
	/**
	 * 
	 * @return
	 * @throws JAXBException
	 * @throws IOException 
	 */
	@Todo("this shouldn't need rate limiting. something is wrong")
	/*
	private static Stream<URL> getUserAgentsHelper(Pair<URL,String> p) throws JAXBException, IOException {
		URL url = p.getCar();
		String userAgent = p.getCdr();
		
		Map<String, Object> properties = new HashMap<String, Object>(2);
        properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
        properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
        //properties.put(JAXBContextProperties.JSON_ATTRIBUTE_PREFIX, "@");
        JAXBContext jc = JAXBContext.newInstance(new Class[] {PixabayResult.class}, properties);

        Unmarshaller unmarshaller = jc.createUnmarshaller();
       URLConnection c = url.openConnection();
		c.setRequestProperty("User-Agent", userAgent);
       //c.setRequestProperty("User-Agent", "firefux");
		InputStream is0 = c.getInputStream();
		
		InputStream is1 = new InputStream() {

			@Override
			public int read() throws IOException {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return is0.read();
			}
			
		};
		
		InputStream is = new BufferedInputStream(is1);
		String sss = IOUtil.toString(is);
		
		System.out.println("sss: "+sss);
		//StreamSource ss = new StreamSource(c.getInputStream());
		StreamSource ss = new StreamSource(new StringReader(sss));
        PixabayResult ua = (PixabayResult) unmarshaller.unmarshal(ss,PixabayResult.class).getValue();
		
		
		
		//JAXBContext jaxbContext = JAXBContext.newInstance(PixabayResult.class);
		//Unmarshaller jaxbMarshaller = jaxbContext.createUnmarshaller();

		//jaxbMarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
        //jaxbMarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, false);
		
		//URLConnection c = url.openConnection();
		//c.setRequestProperty("User-Agent", userAgent);
		
		//PixabayResult ua = (PixabayResult) jaxbMarshaller.unmarshal(c.getInputStream());
		if (ua.total == 0)return null;
		List<Hit> list = ua.hits;
		assert list != null;
		//List<String> temp = list.stream().map(u -> u.string).collect(Collectors.toList());
		//userAgents = Collections.unmodifiableList(temp);
		//return userAgents;
		//Stream<URL> s = list.stream().map(u -> u.largeImageURL);
		Stream<URL> s = list.stream().map(u -> u.largeImageURL).map(u -> {
			try {
				return new URL(u);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				throw new Error(e);
			}
		});
		//return Replayer.replay(s);
		return s;
	}
*/
	@Override
	protected Optional<Stream<URL>> helper(URLConnection input) {
		Map<String, Object> properties = new HashMap<String, Object>(2);
        properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
        properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
        //properties.put(JAXBContextProperties.JSON_ATTRIBUTE_PREFIX, "@");
        JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(new Class[] {PixabayResult.class}, properties);
		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			throw new Error(e1);
		}

        Unmarshaller unmarshaller;
		try {
			unmarshaller = jc.createUnmarshaller();
		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			throw new Error(e1);
		}
       //URLConnection c = url.openConnection();
		//c.setRequestProperty("User-Agent", userAgent);
       //c.setRequestProperty("User-Agent", "firefux");
		InputStream is0;
		try {
			is0 = input.getInputStream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			throw new Error(e1);
		}
		
		InputStream is1 = new InputStream() {

			@Override
			public int read() throws IOException {
				try {
					Thread.sleep(0);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return is0.read();
			}
			
		};
		
		InputStream is = new BufferedInputStream(is0);
		String sss;
		try {
			sss = IOUtil.toString(is);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			throw new Error(e1);
		}
		
		//System.out.println("sss: "+sss);
		//StreamSource ss = new StreamSource(c.getInputStream());
		StreamSource ss = new StreamSource(new StringReader(sss));
        PixabayResult ua;
		try {
			ua = (PixabayResult) unmarshaller.unmarshal(ss,PixabayResult.class).getValue();
		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			throw new Error(e1);
		}
		
		
		
		//JAXBContext jaxbContext = JAXBContext.newInstance(PixabayResult.class);
		//Unmarshaller jaxbMarshaller = jaxbContext.createUnmarshaller();

		//jaxbMarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
        //jaxbMarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, false);
		
		//URLConnection c = url.openConnection();
		//c.setRequestProperty("User-Agent", userAgent);
		
		//PixabayResult ua = (PixabayResult) jaxbMarshaller.unmarshal(c.getInputStream());
		if (ua.total == 0)return null;
		List<Hit> list = ua.hits;
		if (list.isEmpty())
			return Optional.empty();
		assert list != null;
		//List<String> temp = list.stream().map(u -> u.string).collect(Collectors.toList());
		//userAgents = Collections.unmodifiableList(temp);
		//return userAgents;
		//Stream<URL> s = list.stream().map(u -> u.largeImageURL);
		Stream<URL> s = list.stream().map(u -> u.largeImageURL).map(u -> {
			try {
				return new URL(u);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				throw new Error(e);
			}
		});
		//s.forEach(System.out::println);
		//return Replayer.replay(s);
		return Optional.of(s);
	}

	
}
