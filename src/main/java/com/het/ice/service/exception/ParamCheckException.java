package com.het.ice.service.exception;

public class ParamCheckException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7461413948897566392L;

	public ParamCheckException() {
		super();
	}

	public ParamCheckException(String message) {
		super(message);
	}
}
