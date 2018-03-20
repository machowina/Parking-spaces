package com.machowina.service;

import java.math.BigDecimal;

import com.machowina.model.ParkingZone;

public interface ParkingZoneService {

	/**use when there is only one parking zone
	 * 
	 * @return first found zone
	 */
	ParkingZone findDeafultZone();

	ParkingZone findOne(Long parkingZoneId);

	BigDecimal checkIncomeForDay(String incomeDate, ParkingZone zone);

}
