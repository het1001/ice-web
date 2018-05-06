package com.het.ice.service.exception;

public class BizException extends RuntimeException {

	private ResultCodeEnum resultCode;

	/**
	 *
	 */
	private static final long serialVersionUID = 7461413948897566392L;

	public BizException() {
		super();
	}

	public BizException(String message) {
		super(message);
	}

	public BizException(ResultCodeEnum resultCode) {
		super("业务错误");
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		if (resultCode != null) {
			return resultCode.getCode();
		} else {
			return this.getMessage();
		}

	}
}
