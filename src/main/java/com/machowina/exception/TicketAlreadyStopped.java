package com.machowina.exception;

public class TicketAlreadyStopped extends RuntimeException {

	private static final long serialVersionUID = -2675912738745591834L;

	public TicketAlreadyStopped() {
		super();
	}

	public TicketAlreadyStopped(String message) {
		super(message);
	}
	
	

}
