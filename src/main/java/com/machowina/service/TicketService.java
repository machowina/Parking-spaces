package com.machowina.service;

import com.machowina.model.ParkingTicket;

public interface TicketService {

	ParkingTicket generateTicketDefaultZone(Long carId);
	ParkingTicket generateTicket(Long carId, Long parkingZoneId);

	Long saveTicket(ParkingTicket ticket);

}
