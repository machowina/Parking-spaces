package com.machowina.exception;

public class TicketAlreadyRunning extends RuntimeException {

	private static final long serialVersionUID = -4764824935163175072L;

	public TicketAlreadyRunning() {
		super();
	}

	public TicketAlreadyRunning(String message) {
		super(message);
	}
	

}
