package com.machowina.exception;

public class TicketNotStoppedException extends RuntimeException {

	private static final long serialVersionUID = 5058211773026907707L;

	public TicketNotStoppedException() {
		super();
	}

	public TicketNotStoppedException(String message) {
		super(message);
	}

}
