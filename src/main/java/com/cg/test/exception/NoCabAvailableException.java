package com.cg.test.exception;

public class NoCabAvailableException extends RuntimeException {

	private static final long serialVersionUID = -5788530688711832380L;

	public NoCabAvailableException() {
		super();
	}
	public NoCabAvailableException(String string) {
		super(string);
	}
	public NoCabAvailableException(String string, Throwable cause) {
		super(string, cause);
	}
}