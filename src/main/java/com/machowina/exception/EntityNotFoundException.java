package com.machowina.exception;

public class EntityNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 4681194719623412061L;
	
	public EntityNotFoundException() {
		super();
	}
	public EntityNotFoundException(String message) {
		super(message);
	}

}
