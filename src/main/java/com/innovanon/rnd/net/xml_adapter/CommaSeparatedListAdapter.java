/**
 * 
 */
package com.innovanon.rnd.net.xml_adapter;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * https://stackoverflow.com/questions/3936811/can-jaxb-output-an-arraylist-as-comma-separated-values
 * 
 * @author gouldbergstein
 *
 */
public class CommaSeparatedListAdapter extends XmlAdapter<String, List<String>> {

    @Override
    public List<String> unmarshal(final String string) {
		String[] tokens = string.split(Pattern.quote(","));
		return Stream.of (tokens).map(s -> s.trim ()).filter(t -> ! t.isEmpty()).collect(Collectors.toList());
    	/*
        final List<String> strings = new ArrayList<String>();

        for (final String s : string.split(",")) {
            final String trimmed = s.trim();

            if (trimmed.length() > 0) {
                strings.add(trimmed);
            }
        }

        return strings;
        */
    }

    @Override
    public String marshal(final List<String> strings) {
   Iterable <String> list=strings.stream().map (s->s.trim()).filter (s -> ! s.isEmpty()).collect (Collectors.toList());
	return 	String.join(",", list);
    /*
    final StringBuilder sb = new StringBuilder();

        for (final String string : strings) {
            if (sb.length() > 0) {
                sb.append(", ");
            }

            sb.append(string);
        }

        return sb.toString();
        */
    }
}