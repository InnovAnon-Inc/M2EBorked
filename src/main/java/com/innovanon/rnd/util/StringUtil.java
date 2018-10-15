/**
 * 
 */
package com.innovanon.rnd.util;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Function;
import java.util.regex.Pattern;

import com.innovanon.rnd.struct.stream.StreamUtil;

/**
 * @author gouldbergstein
 *
 */
public enum StringUtil {
	/* no instances */;
	
	/**
	 * @param elements the elements to be joined
	 * @param mapper the toString function
	 * @param delimiter the separator between the elements
	 * @return a string containing a delimiter-separated list of elements
	 */
	public static<T> String join (Collection<T> elements, Function<T, String> mapper, String delimiter) {
		return String.join(delimiter, StreamUtil.map(elements, mapper));
	}
	
	/**
	 * https://stackoverflow.com/questions/19198048/how-to-repeat-string-n-times-in-java
	 * 
	 * @param str
	 * @param n
	 * @return
	 */
	public static String repeatv1(String str, int n) {
		return String.valueOf(new char[n]).replace("\0", str);
	}

	/**
	 * @param str
	 * @param n
	 * @return
	 */
	public static String repeatv2(String str, int n) {
		CharSequence delimiter = "";
		Iterable<CharSequence> elements = Collections.nCopies(n, str);
		return String.join(delimiter, elements);
	}

	/**
	 * just use serialization ffs
	 * 
	 * @param object
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	@Deprecated
	public static String toString(Object object) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		if (object == null)
			return null;
		if (object instanceof String)
			return object.toString();
		Class<?> cls = object.getClass();
		if (cls.isArray()) {
			Class<?> ct = cls.getComponentType();
			if (ct.equals(int.class))
				return Arrays.toString((int[]) object);
			if (ct.equals(short.class))
				return Arrays.toString((short[]) object);
			if (ct.equals(long.class))
				return Arrays.toString((long[]) object);
			if (ct.equals(double.class))
				return Arrays.toString((double[]) object);
			if (ct.equals(float.class))
				return Arrays.toString((float[]) object);
			if (ct.equals(boolean.class))
				return Arrays.toString((boolean[]) object);
			if (ct.equals(byte.class))
				return Arrays.toString((byte[]) object);
			if (ct.equals(char.class))
				return Arrays.toString((char[]) object);
			// TODO
			return Arrays.deepToString((Object[]) object);
		}
		return toString(object, new Method[0]);
	}

	@Deprecated
	public static String toString(Object object, Method[] accessors) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		if (object == null)
			return null;
		StringBuilder ret = new StringBuilder();

		Class<?> cls = object.getClass();
		ret.append(cls.getName());
		ret.append('[');
		ret.append(invocationToString("toString", object));
		for (int index = 0; index < accessors.length; index++) {
			Method accessor = accessors[index];
			// if (index > 0)
			ret.append(", ");
			ret.append(invocationToString(accessor, object));
		}
		ret.append(']');
		return ret.toString();
	}

	@Deprecated
	public static String invocationToString(Method accessor, Object object)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		StringBuilder ret = new StringBuilder();
		Object returnValue;
		try {
			returnValue = accessor.invoke(object);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// throw new MethodInvocationError(e);
			throw e;
		}
		// if (index > 0)
		// ret.append(", ");
		ret.append(accessor.getName());
		ret.append('=');
		String returnValueStr = toString(returnValue);
		ret.append(returnValueStr);
		return ret.toString();
	}

	@Deprecated
	public static String invocationToString(String accessorName, Object object) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Class<?> cls = object.getClass();
		Method accessor;
		try {
			accessor = cls.getMethod(accessorName);
		} catch (NoSuchMethodException | SecurityException e) {
			// throw new GetMethodError(e);
			throw e;
		}
		return invocationToString(accessor, object);
	}
	
	@Deprecated
	public static String toString2(Object object) {
		if (object == null)
			return null;
		Class<?> cls = object.getClass();
		if (cls.isArray()) {
			Class<?> ct = cls.getComponentType();
			if (ct.equals(int.class))
				return Arrays.toString((int[]) object);
			if (ct.equals(short.class))
				return Arrays.toString((short[]) object);
			if (ct.equals(long.class))
				return Arrays.toString((long[]) object);
			if (ct.equals(double.class))
				return Arrays.toString((double[]) object);
			if (ct.equals(float.class))
				return Arrays.toString((float[]) object);
			if (ct.equals(boolean.class))
				return Arrays.toString((boolean[]) object);
			if (ct.equals(byte.class))
				return Arrays.toString((byte[]) object);
			if (ct.equals(char.class))
				return Arrays.toString((char[]) object);
			//return Arrays.deepToString((Object[]) object);
			return arrayToString ((Object[])object, StringUtil::toString2);
		}
		return object.toString();
	}
	@Deprecated
	public static <T> String arrayToString(T[] array, Function<T,String> toString) {
		StringBuilder ret = new StringBuilder();
		ret.append('[');
		boolean flag = true;
		for (T elem : array) {
			if (flag) {
				ret.append(", ");
				flag = false;
			}
			String append = toString.apply (elem);
			ret.append(append);
		}
		ret.append(']');
		return ret.toString();
	}
	
	public static char[] trim(char[] buf) {
		return trim(buf, 0, buf.length - 1);
	}

	public static char[] trim(char[] buf, int off, int len) {
		// System.out.println(off);
		while (off < len && (Character.isWhitespace(buf[off]) /*|| buf[off] == '\0'||buf[off]==0*/))
			off++;
		while (len > off && (Character.isWhitespace(buf[len]) /*|| buf[len] == '\0'||buf[len]==0*/))
			len--;
		assert buf[len]!='\0';
		assert buf[len]!=0;
		assert len >= 0;
		assert off <= buf.length;
		assert off <= len;
		char[] ret = new char[CountingUtil.inclusiveRange(off, len)];
		assert ret.length <= buf.length;
		assert ret.length == len - off + 1;
		// System.out.println(Arrays.toString(buf));
		// System.out.println(buf.length);
		// System.out.println(off);
		// System.out.println(len);
		// System.out.println(ret.length);
		System.arraycopy(buf, off, ret, 0, len + 1);
		//for (int i = 0; i < ret.length; i++) 
		//System.out.println((int)ret[i]);
		return ret;
	}

	public static String packageToPath(Package package1) {
		String name = package1.getName();
		if (! name.isEmpty())
			name += ".";
		String regex = Pattern.quote(".");
		return name.replaceAll(regex , File.separator);
	}
}
