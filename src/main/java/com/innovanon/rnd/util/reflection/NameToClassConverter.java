/**
 * 
 */
package com.innovanon.rnd.util.reflection;

/**
 * @author gouldbergstein
 */
@Deprecated
public enum NameToClassConverter /*implements Function<String, Class<?>>*/ {
	/**
	 * 
	 */
	INSTANCE;

	/**
	 * @throws Error
	 * @see java.lang.ClassNotFoundException
	 * @see Class#forName(String)
	 */
	/* (non-Javadoc)
	 * @see java.util.function.Function#apply(java.lang.Object)
	 */
	/*
	@Override
	public Class<?> apply(String className) throws ClassNotFoundError {
		//Objects.requireNonNull(className);
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundError(className, e);
		}
	}
	*/
}
