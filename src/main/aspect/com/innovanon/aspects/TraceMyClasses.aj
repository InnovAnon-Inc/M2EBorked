/*

Copyright (c) Xerox Corporation 1998-2002.  All rights reserved.

Use and copying of this software and preparation of derivative works based
upon this software are permitted.  Any distribution of this software or
derivative works must comply with all applicable United States export control
laws.

This software is made available AS IS, and Xerox Corporation makes no warranty
about the software, its performance or its conformity to any specification.

|<---            this code is formatted to fit into 80 columns             --->|
|<---            this code is formatted to fit into 80 columns             --->|
|<---            this code is formatted to fit into 80 columns             --->|

*/

package com.innovanon.aspects;

import java.net.MalformedURLException;

import javax.xml.bind.JAXBException;

import com.innovanon.rnd.aspects.App;

/**
 *
 * This class concretizes the abstract crosscut in Trace, 
 * applying the trace facility to these application classes.
 *
 */
public aspect TraceMyClasses extends Trace {
    pointcut myClass(Object obj): 
	this(obj) && 
	(within(com.innovanon.rnd..*));

    /**
     * A main function for testing the trace aspect.
     * @throws JAXBException 
     * @throws MalformedURLException 
     */
    public static void main(String... args) throws MalformedURLException, JAXBException {
	Trace.TRACELEVEL = 2;
	Trace.initStream(System.err);
	App.main(args);
    }
}

