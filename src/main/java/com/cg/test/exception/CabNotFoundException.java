package com.cg.test.exception;

public class CabNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5788530688711832380L;
	
	public CabNotFoundException() {
		super();
	}
	public CabNotFoundException(String string) {
		super(string);
	}
	public CabNotFoundException(String string, Throwable cause) {
		super(string, cause);
	}
}
