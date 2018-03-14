package com.machowina.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.machowina.exception.EntityNotFoundException;
import com.machowina.model.ParkingTicket;
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
	ResponseEntity generateTicket(@PathVariable Long carId) {
		
		Long newTicketId = ticketService.generateTicketDefaultZone(carId);
		
		return ResponseEntity.ok(newTicketId);
	}

}
