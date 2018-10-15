/**
 * 
 */
package com.innovanon.rnd.simon.pixabay.query;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

import org.apache.http.NameValuePair;

import com.innovanon.rnd.at.Todo;
import com.innovanon.rnd.io.HomeFileToCharArrayIO;
import com.innovanon.rnd.net.nv.NameValuePairImpl;
import com.innovanon.rnd.ri.suppliers.special.EnumSubsetSupplier;
import com.innovanon.rnd.ri.suppliers.special.EnumSupplier;
import com.innovanon.rnd.ri.suppliers.special.RangedIntSupplier;
import com.innovanon.rnd.simon.QueryLang;
import com.innovanon.rnd.simon.QuerySupplier;
import com.innovanon.rnd.simon.URLSupplier;
import com.innovanon.rnd.struct.pair.ImmutablePairImpl;
import com.innovanon.rnd.struct.pair.Pair;
import com.innovanon.rnd.struct.stream.StreamUtil;
import com.innovanon.rnd.util.StringUtil;



enum QueryType  implements NameValuePair{
	ALL("all"),PHOTO("photo"),ILLUSTRATION("illustration"),VECTOR("vector");
	private String value;
	QueryType(String value){this.value=value;}

	public String getValue() {
		return value;
	}	public String getName (){ return "image_type";}}

enum QueryOrientation  implements NameValuePair{
	ALL("all"),HORIZONTAL("horizontal"),VERTICAL("vertical");
	private String value;
	QueryOrientation(String value){this.value=value;}

	public String getValue() {
		return value;
	}	public String getName() { return "orientation";}}

enum QueryCategory  implements NameValuePair{
	FASHION("fashion"),NATURE("nature"),BACKGROUNDS("backgrounds"),
	SCIENCE("scince"),EDUCATION("education"),PEOPLE("people"),FEELINGS("feelings"),RELIGION("religion"),
	HEALTH("health"),PLACES("places"),ANIMALS("animals"),INDUSTRY("industry"),FOOD("food"), 
	COMPUTER("computer"),SPORTS("sports"),TRANSPORTATION("transportation"),TRAVEL("travel"),
	BUILDINGS("buildings"),BUSINESS("business"),MUSIC("music");
	private String value;
	QueryCategory(String value){this.value=value;}

	public String getValue() {
		return value;
	}
	public String getName() {return "category";}}

enum QueryColor {
	GRAYSCALE("grayscale"), TRANSPARENT("transparent"), RED("red"), ORANGE("orange"), YELLOW("yellow"), 
	GREEN("green"), TURQUOISE("turquoise"), BLUE("blue"), LILAC("lilac"),
	PINK("pink"),WHITE( "white"), GRAY("gray"), BLACK("black"), BROWN("brown") 
	;private String value;
	QueryColor(String value){this.value=value;}

	public String getValue() {
		return value;
	}
}

enum QueryChoice  implements NameValuePair{
	TRUE("true"),FALSE("false");
	;private String value;
	QueryChoice(String value){this.value=value;}

	public String getValue() {
		return value;
	}
	
	public String getName () {return  "editors_choice";}
}

enum QuerySafe  implements NameValuePair{
	TRUE("true"),FALSE("false");
	;private String value;
	QuerySafe(String value){this.value=value;}

	public String getValue() {
		return value;
	}
	public String getName () { return "safesearch";}
}

enum QueryOrder  implements NameValuePair{POPULAR("popular"),LATEST("latest");
	;private String value;
	QueryOrder(String value){this.value=value;}

	public String getValue() {
		return value;
	}
	public String getName() {return "order";}
}

/**
 * @author gouldbergstein
 *
 */
public class PixabayURLSupplier extends URLSupplier {

	@Todo("don't store passwords in Strings")
	private static String getKey() throws FileNotFoundException, IOException {
		return String.valueOf(HomeFileToCharArrayIO.getInstance().apply(".pixabay"));
	}
	
	private static Collection< Supplier< NameValuePair>> getNonOptionalParameters() {
		return Arrays.asList(() -> {
			try {
				return new NameValuePairImpl("key", getKey());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new Error(e);
			}
		});
	}
	
	private static Collection< Supplier< NameValuePair>> getOptionalParameters(Supplier<QueryType> types,
			Supplier<QueryOrientation> orientations, Supplier<QueryCategory> categories, IntSupplier minWidths,
			IntSupplier minHeights, Supplier<Collection<QueryColor>> colors, Supplier<QueryChoice> choices,
			Supplier<QuerySafe> safes, Supplier<QueryOrder> orders, IntSupplier pages, IntSupplier perPages) {
		// TODO minWidths, minHeights, pages, perPages
		return Arrays.asList(
				()->types.get(),
				()->orientations.get(),
				()->categories.get(),
				()->choices.get(), 
				()->safes.get(),
				()->orders.get(),
				
				() -> new NameValuePairImpl("min_width", String.valueOf(minWidths.getAsInt())),
				() -> new NameValuePairImpl( "min_height", String.valueOf(minHeights.getAsInt())),
				() -> new NameValuePairImpl( "colors",StringUtil.join(colors.get(), x -> x.getValue(), ",") )
			);
	}
	
	private static	Supplier<Pair<NameValuePair, String>> getQueries (Supplier<Pair<QueryLang,Collection<String>> >queries) {
		Function<Pair<QueryLang,Collection<String>>, Pair<NameValuePair,String>> mapper=
				s -> new ImmutablePairImpl<NameValuePair,String>(s.getCar(),String.join(" ", s.getCdr()));
		return StreamUtil.map(queries,
				mapper);
	}
	
	public PixabayURLSupplier(Supplier<Pair<QueryLang,Collection<String>> >queries,  Supplier<QueryType> types,
			Supplier<QueryOrientation> orientations, Supplier<QueryCategory> categories, IntSupplier minWidths,
			IntSupplier minHeights, Supplier<Collection<QueryColor>> colors, Supplier<QueryChoice> choices,
			Supplier<QuerySafe> safes, Supplier<QueryOrder> orders, IntSupplier pages, IntSupplier perPages, IntSupplier ns, Random random)
			throws FileNotFoundException, IOException {
		super (
				"https", "pixabay.com", "api",
				getNonOptionalParameters(),
				"q",
				getQueries(queries),
				getOptionalParameters(
						types, orientations, categories,
						minWidths, minHeights, colors, choices, safes, orders, pages, perPages),
				ns,
				 random
		);
	}
	
	public PixabayURLSupplier(Supplier<Pair<QueryLang,Collection<String>>> queries,  IntSupplier minWidths,
			IntSupplier minHeights, IntSupplier pages, IntSupplier perPages, Random random) throws FileNotFoundException, IOException {
		this(queries, new EnumSupplier<QueryType>(QueryType.class, random),
				new EnumSupplier<QueryOrientation>(QueryOrientation.class, random),
				new EnumSupplier<QueryCategory>(QueryCategory.class, random), minWidths, minHeights,
				new EnumSubsetSupplier<QueryColor>(QueryColor.class, random),
				new EnumSupplier<QueryChoice>(QueryChoice.class, random),
				new EnumSupplier<QuerySafe>(QuerySafe.class, random),
				new EnumSupplier<QueryOrder>(QueryOrder.class, random),
				pages, perPages, new RangedIntSupplier(0, 5, random), random);
	}
	
	public PixabayURLSupplier(Random random) throws IOException{
		this(
				new QuerySupplier(random),
				new RangedIntSupplier(random, 0, 100),
				new RangedIntSupplier(random, 0, 100),
				new RangedIntSupplier(random, 0, 10),
				 new RangedIntSupplier(random, 3, 200), 
				 random);
	}
}
