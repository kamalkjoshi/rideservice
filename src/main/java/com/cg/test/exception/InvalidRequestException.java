package com.cg.test.exception;

public class InvalidRequestException extends RuntimeException {
	private static final long serialVersionUID = -8164252625775436820L;
	public InvalidRequestException() {
		super();
	}
	public InvalidRequestException(String string) {
		super(string);
	}
	public InvalidRequestException(String string, Throwable cause) {
		super(string, cause);
	}
}