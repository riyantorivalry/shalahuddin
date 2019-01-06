package com.shalahuddin.web.exception;

public class StorageException extends RuntimeException {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 482941498444893978L;

	public StorageException(String message) {
		super(message);
	}

	public StorageException(String message, Throwable cause) {
		super(message, cause);
	}
}
