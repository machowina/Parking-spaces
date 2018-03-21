package com.machowina.service;

import com.machowina.model.ParkingRates;
import com.machowina.model.ParkingZone;

public interface ParkingRatesService {
	
	ParkingRates findOneByDriverTypeAndParkingZone(String driverType, ParkingZone zone);


}
