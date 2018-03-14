package com.machowina.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.machowina.exception.EntityNotFoundException;
import com.machowina.exception.TicketAlreadyRunning;
import com.machowina.model.Car;
import com.machowina.model.ParkingTicket;
import com.machowina.model.ParkingZone;
import com.machowina.repository.TicketRepository;

@Service
public class TicketServiceImp implements TicketService {
	
	private final CarService carService;
	private final ParkingZoneService zoneService;
	private final UserService userService;
	
	private final TicketRepository ticketRepository;
	

	public TicketServiceImp(CarService carService, ParkingZoneService zoneService,
			TicketRepository ticketRepository, UserService userService) {
		super();
		this.carService = carService;
		this.zoneService = zoneService;
		this.ticketRepository = ticketRepository;
		this.userService = userService;
	}

	@Override
	public ParkingTicket generateTicketDefaultZone (Long carId) {

		//assuming there is only one zone in database - returns first found zone
		ParkingZone defaultZone = zoneService.findDeafultZone();
		
		return generateTicket(carId, defaultZone.getId());
		
	}
	
	@Override
	public ParkingTicket generateTicket(Long carId, Long parkingZoneId) {

		checkForDuplicatingTicket(carId);
		
		ParkingTicket newTicket = new ParkingTicket();
		
		newTicket.setCar(carService.findById(carId));
		
		newTicket.setDriver(userService.findUserForCar(carId));
		newTicket.setParkingZone(zoneService.findOne(parkingZoneId));
		
		newTicket.setStartTime(LocalDateTime.now());
		newTicket.setPaid(false);
		newTicket.setStopped(false);
		
		return newTicket;
		
	}

	@Override
	public void checkForDuplicatingTicket(Long carId) {
		ParkingTicket ticket = ticketRepository.findOneByCarIdAndIsStoppedFalse(carId);
		if (ticket != null) {
			throw new TicketAlreadyRunning();
		}
	}
	

	@Override
	public Long saveTicket(ParkingTicket ticket) {
		ticketRepository.save(ticket);
		return ticket.getId();
	}
	
	

}
