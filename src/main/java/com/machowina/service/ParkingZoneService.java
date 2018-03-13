package com.machowina.service;

import com.machowina.model.ParkingZone;

public interface ParkingZoneService {

	
	ParkingZone findDeafultZone();

	ParkingZone findOne(Long parkingZoneId);

}
