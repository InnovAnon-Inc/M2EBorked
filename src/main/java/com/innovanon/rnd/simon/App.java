package com.innovanon.rnd.simon;

import java.awt.FlowLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.xml.bind.JAXBException;

import com.innovanon.rnd.net.ua.UserAgentSupplier;
import com.innovanon.rnd.simon.pixabay.PixabayImageURLSupplier;

public enum App {
	/* no instances */ ; 
	
	public static void main(String...args) throws JAXBException, IOException {
		Random random=new Random();
		
		
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
		Supplier<String> userAgents = new UserAgentSupplier(random);
		Supplier<Optional<Stream<URL>>> urls = new PixabayImageURLSupplier(urls0, userAgents);
		
		
		Supplier<Optional<URL>> connections = () -> {
			Optional<Stream<URL>> a = urls.get();
			if (!a.isPresent())return Optional.empty();
			return Optional.of(a.get().findFirst().get());
		};
		//Supplier<Image>images = new URLConnectionImageSupplier(random);
		Supplier<Optional<Image>>images = new URLConnectionImageSupplier(connections , new UserAgentSupplier(random));
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		frame.setVisible(true);
		Stream.generate(images).filter(o->o.isPresent()).map(o->o.get()).limit(2).forEach(i-> {
			int width=100;
			int height=100;
			JLabel picLabel=new JLabel(new ImageIcon(i.getScaledInstance(width, height, Image.SCALE_FAST)));
			frame.getContentPane().add(picLabel);
			frame.pack();
			System.out.println(i);
		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
