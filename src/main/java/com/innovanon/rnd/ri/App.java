package com.innovanon.rnd.ri;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.innovanon.rnd.ri.consumers.Initializer;
import com.innovanon.rnd.ri.consumers.ObjectInitializer;
import com.innovanon.rnd.ri.functions.ObjectInstantiator;
import com.innovanon.rnd.ri.functions.YInstantiator;

/**
 * Hello world!
 *
 */
public enum App {
	/* no instances */ ;
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		Random random = new Random();
		ObjectInstantiator objects = new ObjectInstantiator(random);
		// TODO
		Supplier<Class<?>> classes = new Supplier<Class<?>>() {

			@Override
			public Class<?> get() {
				int bound = 2;
				switch (random.nextInt(bound)) {
				case 0:
					return int[].class;
				case 1:
					return double[].class;
				}
				throw new Error("oops");
			}

		};
		Initializer<Class<?>,Object> initializer = new ObjectInitializer();
		initializer.setDelegate(objects);
		YInstantiator<Class<?>,Object> objects2 = new InitializedObjectInstantiator(objects, initializer);
		//YInstantiator<Class<?>,Object> objects3 = new ObjectInstantiator2(random, objects2);
		//Initializer<Object> initializer2 = new ObjectInitializer(objects3);
		//YInstantiator<Class<?>,Object> objects4 =new InitializedObjectInstantiator(objects3, initializer2);
		//objects3.setDelegate(objects4);
		
		long maxSize = 10;
		Consumer<? super Object> action=new Consumer<Object>() {

			@Override
			public void accept(Object object) {
				// TODO Auto-generated method stub
				System.out.println(Array.getLength(object));
				initializer.accept(object);
				Class<?> type = object.getClass();
				if (type.equals(int[].class))
					System.out.println(Arrays.toString((int[]) object));
				if (type.equals(double[].class))
					System.out.println(Arrays.toString((double[]) object));
			}
			
		};
		Stream.generate(classes).limit(maxSize ).map(objects2::apply).forEach(action);
		/*
		for (int k = 0; k <= 10; k++) {
			Object object = objects2.apply(classes.get());
			System.out.println(Array.getLength(object));
			initializer.accept(object);
			Class<?> type = object.getClass();
			if (type.equals(int[].class))
				System.out.println(Arrays.toString((int[]) object));
			if (type.equals(double[].class))
				System.out.println(Arrays.toString((double[]) object));
		}
		*/
	}
}
