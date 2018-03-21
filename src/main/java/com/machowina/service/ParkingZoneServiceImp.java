package com.machowina.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.machowina.exception.EntityNotFoundException;
import com.machowina.model.ParkingTicket;
import com.machowina.model.ParkingZone;
import com.machowina.repository.ParkingZoneRepository;
import com.machowina.repository.TicketRepository;

@Service
public class ParkingZoneServiceImp implements ParkingZoneService {
	

	private final ParkingZoneRepository zoneRepository;
	private final TicketRepository ticketRepository;

	@Autowired
	public ParkingZoneServiceImp(ParkingZoneRepository zoneRepository, TicketRepository ticketRepository) {
		super();
		this.zoneRepository = zoneRepository;
		this.ticketRepository = ticketRepository;
	}
	


	

	@Override
	public ParkingZone findOne(Long parkingZoneId) {
		ParkingZone parkingZone = zoneRepository.findOne(parkingZoneId);
		
		if (parkingZone == null) {
			throw new EntityNotFoundException("There is no parking zone with this id");
		} else {
			return parkingZone;
		}
	}


	@Override
	public BigDecimal checkIncomeForDay(String incomeDate, ParkingZone zone) {
		
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate incomeDay = LocalDate.parse(incomeDate, formatter);

		LocalDateTime startOfDay = incomeDay.atStartOfDay();
		LocalDateTime endOfDay = incomeDay.plusDays(1l).atStartOfDay().minusNanos(1l);
		
		List<ParkingTicket> ticketList = ticketRepository
				.findAllByParkingZoneAndStopTimeBetween(zone, startOfDay, endOfDay);
		
		BigDecimal income = BigDecimal.ZERO;
		for (ParkingTicket ticket : ticketList) {
			income = income.add(ticket.getFee());
		}
		
		return income;
	}

}
