package com.sp.app.exception;

public class BusinessException extends RuntimeException{
	private static final long serialVersionUID = -7496574093153571861L;

	public BusinessException(String message) {
		super(message);
	}
}
