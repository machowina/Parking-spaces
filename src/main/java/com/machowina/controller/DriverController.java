package com.machowina.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.machowina.service.TicketService;

@RestController
@RequestMapping("/drivers")
public class DriverController {
	
	
	private final TicketService ticketService;
	
	@Autowired
	public DriverController(TicketService ticketService) {
		super();
		this.ticketService = ticketService;
	}
	

	@PostMapping("/cars/{carId}/zones/{zoneId}/newTicket")
	@ResponseStatus(HttpStatus.CREATED)
	public Long generateTicket(@PathVariable Long carId, @PathVariable Long zoneId) {
		
		Long newTicketId = ticketService.generateNewTicket(carId, zoneId);
		
		return newTicketId;
	}
	
	@PatchMapping("/tickets/{ticketId}/stop")
	@ResponseStatus(HttpStatus.OK)
	public void stopTicketTime(@PathVariable Long ticketId) {
		
		ticketService.stopTicket(ticketId);
	}
	
	@PatchMapping("/tickets/{ticketId}/calculateFee")
	@ResponseStatus(HttpStatus.OK)
	public BigDecimal calculateTicketFee(@PathVariable Long ticketId) {
		
		return ticketService.calculateTicketFee(ticketId);
	}

}
