package com.machowina.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.machowina.service.TicketService;

@RestController
@RequestMapping("/operator")
public class OperatorController {
	
	private final TicketService ticketService;

	@Autowired
	public OperatorController(TicketService ticketService) {
		super();
		this.ticketService = ticketService;
	}
	
	@GetMapping("checkForTicket/{carLicense}")
	@ResponseStatus(HttpStatus.OK)
	public boolean checkForTicket(@PathVariable String carLicense) {
		
		return ticketService.checkForValidTicketAnyZone(carLicense);
	}
	

}
