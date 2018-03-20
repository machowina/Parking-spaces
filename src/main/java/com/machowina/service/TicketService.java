package com.machowina.service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import com.machowina.model.ParkingRates;
import com.machowina.model.ParkingTicket;
import com.machowina.model.ParkingZone;

public interface TicketService {

	/**creates and saves ticket
	 * use when there is only one parking zone
	 * 
	 * @param carId
	 * @return newTicketId
	 */
	Long generateTicketDefaultZone(Long carId);
	
	ParkingTicket createTicket(Long carId, Long parkingZoneId);
	
	void checkForDuplicatingTicket(Long carId);

	Long saveTicket(ParkingTicket ticket);
	
	void stopTicket(Long ticketId);
	
	ParkingTicket findById(Long ticketId);

	boolean checkForValidTicket(String carLicense, Long parkingZoneId);

	boolean checkForValidTicketAnyZone(String carLicense);

	BigDecimal calculateTicketFee(Long ticketId);

	BigDecimal calculateFeeForGivenDuration(Duration parkingTime, ParkingRates rates);

	List<ParkingTicket> findAllForDayAndZone(LocalDate incomeDay, ParkingZone zone);

}
