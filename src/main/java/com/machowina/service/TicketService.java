package com.machowina.service;

import com.machowina.model.ParkingTicket;

public interface TicketService {

	ParkingTicket generateTicketDefaultZone(Long carId);
	ParkingTicket generateTicket(Long carId, Long parkingZoneId);
	void checkForDuplicatingTicket(Long carId);

	Long saveTicket(ParkingTicket ticket);

}
