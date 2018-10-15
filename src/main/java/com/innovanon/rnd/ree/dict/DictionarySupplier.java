/**
 * 
 */
package com.innovanon.rnd.ree.dict;

import java.util.Iterator;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.innovanon.rnd.io.DictionaryResourceIO;
import com.innovanon.rnd.struct.bag.BagUtil;
import com.innovanon.rnd.util.StringUtil;

/**
 * @author gouldbergstein
 *
 */
public class DictionarySupplier implements Supplier<Optional<String>> {
	/**
	 * 
	 */
	private Iterator<String> dict;

	/**
	 * 
	 * @param random
	 */
	public DictionarySupplier(Random random) {
		Optional<Stream<String>> list = DictionaryResourceIO.getInstance().apply(StringUtil.packageToPath(getClass().getPackage())+"all");
		Stream<String>rList;
		if (list.isPresent())
//	list=	list.limit(10);
		 rList= BagUtil.getRandomStream(list.get(), random);
		else rList = null;
		//Iterable<String> iter = new BagImpl<>(list, random);
		//dict = new Reiterator<>(iter);
		if (rList !=null)
		dict = rList.iterator();
		else dict =null;
	}

	/**
	 * 
	 */
	@Override
	public Optional<String> get() {
		if (dict==null)return Optional.empty();
		return Optional.of(dict.next());
	}
}
