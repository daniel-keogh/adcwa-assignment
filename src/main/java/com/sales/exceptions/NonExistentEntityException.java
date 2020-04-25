package com.sales.exceptions;

public class NonExistentEntityException extends Exception {
	private static final long serialVersionUID = -5855382574813849003L;

	public NonExistentEntityException() {
		super();
	}

	public NonExistentEntityException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NonExistentEntityException(String message, Throwable cause) {
		super(message, cause);
	}

	public NonExistentEntityException(String message) {
		super(message);
	}

	public NonExistentEntityException(Throwable cause) {
		super(cause);
	}
}
