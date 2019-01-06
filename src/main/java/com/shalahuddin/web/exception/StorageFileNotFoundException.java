package com.shalahuddin.web.exception;

public class StorageFileNotFoundException extends StorageException {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1569334310535699537L;

	public StorageFileNotFoundException(String message) {
		super(message);
	}

	public StorageFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}