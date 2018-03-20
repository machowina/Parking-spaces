package com.machowina.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.machowina.model.ParkingZone;
import com.machowina.service.ParkingZoneService;

@RestController
@RequestMapping("/owners")
public class OwnerController {
	
	private final ParkingZoneService zoneService;
	
	@Autowired
	public OwnerController(ParkingZoneService zoneService) {
		super();
		this.zoneService = zoneService;
	}



	@GetMapping("/zones/{zoneId}/income/day/{yyyy-MM-dd}")
	@ResponseStatus(HttpStatus.OK)
	public BigDecimal checkIncomeFromDay(@PathVariable Long zoneId, @PathVariable String incomeDate) {
		
		ParkingZone zone = zoneService.findOne(zoneId);
		//TODO: check if logged user is an owner of this zone
		
		return zoneService.checkIncomeForDay(incomeDate, zone);
	}

}
