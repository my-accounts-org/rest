package com.company.ac.exceptions;

public class UnAuthorizedUserException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnAuthorizedUserException(String message) {
		super(message);
	}

}
