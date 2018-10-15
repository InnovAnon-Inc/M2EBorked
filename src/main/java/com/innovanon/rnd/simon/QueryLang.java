/**
 * 
 */
package com.innovanon.rnd.simon;

import java.util.Locale;

import org.apache.http.NameValuePair;

/**
 * @author gouldbergstein
 *
 */
public enum QueryLang implements NameValuePair{
	CS("cs"), DA("da"), DE("de"), EN("en"), ES("es"), FR("fr"), ID("id"), IT("it"), HU("hu"), NL("nl"), NO("no"),
	PL("pl"), PT("pt"), RO("ro"), SK("sk"), FI("fi"), SV("sv"), TR("tr"), VI("vi"), TH("th"), BG("bg"), RU("ru"),
	EL("el"), JA("ja"), KO("ko"), ZH("zh");
	/**
	 * 
	 */
	private String value;
	
	private Locale locale;

	/**
	 * @param value
	 */
	QueryLang(String value) {
		this.value = value;
		this.locale = Locale.forLanguageTag(value);
	}

	public String getValue() {
		return value;
	}
	
	public String getName() { return "lang";}

	public Locale getLocale() {
		return locale;
	}
}
