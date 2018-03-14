package com.machowina.service;

import com.machowina.model.ParkingTicket;

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

}
