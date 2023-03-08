package com.learn.user.exception;

public class ResourceNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException() {
		super("Resource Not Found Exception");
	}

	public ResourceNotFoundException(String string) {
		super(string);
	}
	

}
