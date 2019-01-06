/**
 *
 */
package com.shalahuddin.web.exception;

import org.springframework.security.core.AuthenticationException;

public class ConcurrentLoginException extends AuthenticationException {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1349227788523484359L;

	public ConcurrentLoginException(String msg) {
		super(msg);
	}

	public ConcurrentLoginException(String msg, Throwable t) {
		super(msg, t);
	}
}
