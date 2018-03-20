package com.machowina.service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import com.machowina.model.ParkingRates;
import com.machowina.model.ParkingTicket;
import com.machowina.model.ParkingZone;

public interface TicketService {

	Long generateNewTicket(Long carId, Long zoneId);
	
	ParkingTicket createTicket(Long carId, Long parkingZoneId);
	
	void checkForDuplicatingTicket(Long carId);

	Long saveTicket(ParkingTicket ticket);
	
	void stopTicket(Long ticketId);
	
	ParkingTicket findById(Long ticketId);

	boolean checkForValidTicket(String carLicense, Long parkingZoneId);

	BigDecimal calculateTicketFee(Long ticketId);

	BigDecimal calculateFeeForGivenDuration(Duration parkingTime, ParkingRates rates);

	List<ParkingTicket> findAllForDayAndZone(LocalDate incomeDay, ParkingZone zone);

	

}
