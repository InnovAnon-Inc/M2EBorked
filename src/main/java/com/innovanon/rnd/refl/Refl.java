/**
 * 
 */
package com.innovanon.rnd.refl;

import java.util.LinkedList;
import java.util.List;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeElementsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

/**
 * @author gouldbergstein
 */
public enum Refl {
	INSTANCE;

	// TODO
	/**
	 * @return false
	 */
	private static boolean isProduction() {
		return false;
	}

	private Reflections reflections;
	/**
	 * @see Reflections#getAllTypes()
	 */
	//private Collection<String> types;

	/**
	 * @see #initReflections()
	 */
	Refl() {
		initReflections();
		//types = reflections.getAllTypes();
		// types = reflections.getSubTypesOf(Object.class);
	}

	/**
	 * @see #reflections
	 * @see #initReflectionsHelper()
	 */
	private void initReflections() {
		Reflections reflections = initReflectionsHelper();
		reflections.expandSuperTypes();
		this.reflections = reflections;
	}

	/**
	 * invokes Reflections.collect() if in production; otherwise it gets complicated
	 * 
	 * @return a reflections object
	 * @see #isProduction()
	 * @see Reflections#collect()
	 * @see Reflections#Reflections(org.reflections.Configuration)
	 */
	private Reflections initReflectionsHelper() {
		if (isProduction())
			return Reflections.collect();
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.useParallelExecutor(Runtime.getRuntime().availableProcessors());

		cb.setScanners(new SubTypesScanner(false), new ResourcesScanner(), new TypeElementsScanner());
		//cb.addScanners(new FieldAnnotationsScanner(), new MethodAnnotationsScanner(), new TypeAnnotationsScanner());
		//cb.addScanners(new MemberUsageScanner(), new MethodParameterNamesScanner(), new MethodParameterScanner());
		//
		// cb.addScanners(new SubTypesScanner(false), new ResourcesScanner(), new
		// TypeElementsScanner());
		// cb.setScanners(new SubTypesScanner(false));

		List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
		classLoadersList.add(ClasspathHelper.contextClassLoader());
		classLoadersList.add(ClasspathHelper.staticClassLoader());

		ClassLoader[] classLoaders = classLoadersList.toArray(new ClassLoader[classLoadersList.size()]);
		// cb.setClassLoaders(classLoadersList.toArray(new
		// ClassLoader[classLoadersList.size()]));
		cb.addClassLoaders(classLoaders);

		// cb.addUrls(ClasspathHelper.forClass(Object.class, classLoaders));
		cb.setUrls(ClasspathHelper.forJavaClassPath());

		cb.setExpandSuperTypes(true);

		return new Reflections(cb);
	}

	/**
	 * @return the reflections
	 */
	public Reflections getReflections() {
		return reflections;
	}

	/**
	 * @return the types
	 * @see #types
	 */
	//public Collection<String> getTypes() {
	//	return types;
	//}
}
