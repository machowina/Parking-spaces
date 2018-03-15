package com.machowina.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.machowina.service.TicketService;

@RestController
@RequestMapping("/driver")
public class DriverController {
	
	
	private final TicketService ticketService;
	
	@Autowired
	public DriverController(TicketService ticketService) {
		super();
		this.ticketService = ticketService;
	}


	@PostMapping("/{carId}")
	@ResponseStatus(HttpStatus.OK)
	public Long generateTicket(@PathVariable Long carId) {
		
		Long newTicketId = ticketService.generateTicketDefaultZone(carId);
		
		return newTicketId;
	}

}
