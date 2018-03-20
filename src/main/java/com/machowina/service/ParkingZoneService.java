package com.machowina.service;

import java.math.BigDecimal;

import com.machowina.model.ParkingZone;

public interface ParkingZoneService {

	

	ParkingZone findOne(Long parkingZoneId);

	BigDecimal checkIncomeForDay(String incomeDate, ParkingZone zone);

}
