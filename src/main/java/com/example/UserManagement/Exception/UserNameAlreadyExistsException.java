package com.example.UserManagement.Exception;

public class UserNameAlreadyExistsException extends Exception{

	public UserNameAlreadyExistsException(String message) {
		super(message);
	}

	public UserNameAlreadyExistsException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserNameAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UserNameAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserNameAlreadyExistsException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

}
