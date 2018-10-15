package com.innovanon.rnd.net.ua;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author gouldbergstein
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="user-agents")
 class UserAgents {
	//@XmlList
	@XmlElement(name="user-agent",type=UserAgent.class)
	List<UserAgent> userAgents;
}