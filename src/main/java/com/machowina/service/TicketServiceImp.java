package com.machowina.service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.machowina.exception.EntityNotFoundException;
import com.machowina.exception.TicketAlreadyRunning;
import com.machowina.exception.TicketAlreadyStopped;
import com.machowina.exception.TicketNotStoppedException;
import com.machowina.model.Car;
import com.machowina.model.ParkingRates;
import com.machowina.model.ParkingTicket;
import com.machowina.model.ParkingZone;
import com.machowina.model.User;
import com.machowina.repository.TicketRepository;

@Service
public class TicketServiceImp implements TicketService {
	
	private final CarService carService;
	private final ParkingZoneService zoneService;
	private final UserService userService;
	private final ParkingRatesService parkingRatesService;
	
	private final TicketRepository ticketRepository;
	

	public TicketServiceImp(CarService carService, ParkingZoneService zoneService,
			TicketRepository ticketRepository, UserService userService, 
			ParkingRatesService parkingRatesService) {
		super();
		this.carService = carService;
		this.zoneService = zoneService;
		this.ticketRepository = ticketRepository;
		this.userService = userService;
		this.parkingRatesService = parkingRatesService;
	}

	@Override
	@Transactional
	public Long generateTicketDefaultZone(Long carId) {
		
		checkForDuplicatingTicket(carId);
		
		//assuming there is only one zone in database - returns first found zone
		ParkingZone defaultZone = zoneService.findDeafultZone();
		
		ParkingTicket newTicket = createTicket(carId, defaultZone.getId());
		
		return saveTicket(newTicket);
	
	}
	

	@Override
	public ParkingTicket createTicket(Long carId, Long parkingZoneId) {
		
		Car car = carService.findById(carId);
		User driver = userService.findUserForCar(carId);
		ParkingZone zone = zoneService.findOne(parkingZoneId);
		LocalDateTime startTime = LocalDateTime.now();
		
		ParkingTicket newTicket = new ParkingTicket(startTime, zone, car, driver);
		
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
	@Transactional
	public Long saveTicket(ParkingTicket ticket) {
		ParkingTicket savedTicket = ticketRepository.save(ticket);
		return savedTicket.getId();
	}

	@Override
	@Transactional
	public void stopTicket(Long ticketId) {
		ParkingTicket ticket = findById(ticketId);
		if (!ticket.isStopped()) {
			ticket.setStopTime(LocalDateTime.now());
			ticket.setStopped(true);
			saveTicket(ticket);
		} else {
			throw new TicketAlreadyStopped();
		}
	
	
	}

	@Override
	public ParkingTicket findById(Long ticketId) {
		ParkingTicket ticket = ticketRepository.findOne(ticketId);
		if (ticket == null) {
			throw new EntityNotFoundException("There is no ticket with this ID");
		} else {
			return ticket;
		}
	}

	
	@Override
	public boolean checkForValidTicketAnyZone(String carLicense) {
		List <ParkingTicket> validTickets = ticketRepository
				.findByCarLicenseAndIsStoppedFalse(carLicense);
		
		return (!validTickets.isEmpty());
	}

	@Override
	public boolean checkForValidTicket(String carLicense, Long parkingZoneId) {
		List <ParkingTicket> validTickets = ticketRepository
				.findByCarLicenseAndParkingZoneIdAndIsStoppedFalse(carLicense, parkingZoneId);
		
		return (!validTickets.isEmpty());
	}

	

	@Override
	@Transactional
	public BigDecimal calculateTicketFee(Long ticketId) {
		ParkingTicket ticket = findById(ticketId);
		if ((!ticket.isStopped())||(ticket.getStopTime()==null)) {
			throw new TicketNotStoppedException();
		}
		ParkingRates rates = parkingRatesService.findOneByDriverTypeAndParkingZone
				(ticket.getDriver().getDriverType(), ticket.getParkingZone());
		
		Duration parkingTime = Duration.between(ticket.getStartTime(), ticket.getStopTime());
		BigDecimal fee = calculateFeeForGivenDuration(parkingTime, rates);
		
		ticket.setFee(fee);
		saveTicket(ticket);
		return fee;
	}

	@Override
	public BigDecimal calculateFeeForGivenDuration(Duration parkingTime, ParkingRates rates) {
		
		Long parkingHours = parkingTime.toHours();
		Long incompleteHourMinutes = parkingTime.toMinutes() - parkingHours*60;
		BigDecimal fee = BigDecimal.ZERO;
		BigDecimal thisHourFee = BigDecimal.ZERO;
		BigDecimal incompleteHourRate = BigDecimal.ZERO;
		
		for (int i = 0; i <= parkingHours; i++) {
			switch (i) {
			case 0:
				incompleteHourRate = rates.getFirstHourCost();
				break;
			case 1:
				thisHourFee = rates.getFirstHourCost();
				incompleteHourRate = rates.getSecondHourCost();
				break;
			case 2:
				thisHourFee = rates.getSecondHourCost();
				incompleteHourRate = thisHourFee.multiply(rates.getNextHourRate());
				break;
			default:
				thisHourFee = thisHourFee.multiply(rates.getNextHourRate());
				incompleteHourRate = thisHourFee.multiply(rates.getNextHourRate());
				break;
			}
			fee = fee.add(thisHourFee);
		}
		
		BigDecimal incompleteHourMinuteFee = incompleteHourRate.divide(new BigDecimal("60"), 4, BigDecimal.ROUND_HALF_DOWN);
		BigDecimal incompleteHourFee = incompleteHourMinuteFee.multiply(new BigDecimal(incompleteHourMinutes));
	
		fee = fee.add(incompleteHourFee);
		fee = fee.setScale(2, BigDecimal.ROUND_HALF_DOWN);
		return fee;
	}

	@Override
	public List<ParkingTicket> findAllForDayAndZone(LocalDate incomeDay, ParkingZone zone) {
		LocalDateTime startOfDay = incomeDay.atStartOfDay();
		LocalDateTime endOfDay = incomeDay.plusDays(1l).atStartOfDay().minusNanos(1l);
		
		List<ParkingTicket> ticketList = ticketRepository
				.findAllByParkingZoneAndStopTimeBetween(zone, startOfDay, endOfDay);
		
		return ticketList;
	}
	
	

}
