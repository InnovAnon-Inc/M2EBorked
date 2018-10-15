/**
 * 
 */
package com.innovanon.rnd.at;

import static java.lang.annotation.RetentionPolicy.SOURCE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

/**
 * @author gouldbergstein
 *
 */
@Documented
@Retention(SOURCE)
public @interface Todos {
	Todo[] value();
}
