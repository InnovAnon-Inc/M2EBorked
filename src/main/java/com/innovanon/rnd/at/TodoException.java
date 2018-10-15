/**
 * 
 */
package com.innovanon.rnd.at;

/**
 * a.k.a., IncompleteImplementationException
 * @author gouldbergstein
 *
 */
public class TodoException extends UnsupportedOperationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5532049179230906119L;

	/**
	 * @param message
	 * @param cause
	 */
	public TodoException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public TodoException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public TodoException(Throwable cause) {
		super(cause);
	}

	/**
	 * 
	 * @param messageSource
	 * @param parameters
	 * @return
	 */
	public static TodoException factory(Object messageSource, Object parameters) {
		String message = ErrorMessageUtil.getMessage(messageSource, parameters);
		return new TodoException(message);
	}
}
