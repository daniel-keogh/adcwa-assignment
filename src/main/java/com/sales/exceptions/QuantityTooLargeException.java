package com.sales.exceptions;

public class QuantityTooLargeException extends Exception {
	private static final long serialVersionUID = -9157505828005045344L;

	public QuantityTooLargeException() {
		super();
	}

	public QuantityTooLargeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public QuantityTooLargeException(String message, Throwable cause) {
		super(message, cause);
	}

	public QuantityTooLargeException(String message) {
		super(message);
	}

	public QuantityTooLargeException(Throwable cause) {
		super(cause);
	}
}
