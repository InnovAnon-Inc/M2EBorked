/**
 * 
 */
package com.innovanon.rnd.net.ua;

import java.net.URL;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
class UserAgent {
	@XmlElement(name="ID")
	String ID;
	@XmlElement(name="String")
	String string;
	@XmlElement(name="Description")
	String description;
	@XmlElement(name="Type")
	String type;
	@XmlElement(name="Link1")
	URL link1;
	@XmlElement(name="Link2")
	URL link2;

}
