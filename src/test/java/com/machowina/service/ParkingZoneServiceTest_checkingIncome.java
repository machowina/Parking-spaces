package com.machowina.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.machowina.model.Car;
import com.machowina.model.ParkingTicket;
import com.machowina.model.ParkingZone;
import com.machowina.model.User;
import com.machowina.repository.ParkingZoneRepository;
import com.machowina.repository.TicketRepository;

@RunWith(MockitoJUnitRunner.class)
public class ParkingZoneServiceTest_checkingIncome {

	private ParkingZoneService zoneService;
	
	@Mock
	private TicketRepository ticketRepository;
	@Mock
	private ParkingZoneRepository zoneRepository;
	
	private ParkingZone zone;
	private Car car;
	private User driver;
	private ParkingTicket ticket1;
	private ParkingTicket ticket2;
	
	@Before
	public void setUp() throws Exception {
		zoneService = new ParkingZoneServiceImp(zoneRepository, ticketRepository);
		zone = new ParkingZone("Warszawa", "Strefa płatnego parkowania");
		driver = new User("kowalski", "pass", "regular");
		car = new Car("WI99021", driver);
		ticket1 = new ParkingTicket
				(LocalDateTime.of(2018, 03, 11, 23, 30), zone, car, driver);
		ticket2 = new ParkingTicket
				(LocalDateTime.of(2018, 03, 12, 12, 45), zone, car, driver);
	}

	@Test
	public void testCheckIncomeForDay() {
		// given
		ticket1.setStopTime(LocalDateTime.of(2018, 03, 12, 00, 30));
		ticket2.setStopTime(LocalDateTime.of(2018, 03, 12, 14, 45));
		ticket1.setFee(new BigDecimal("1.00"));
		ticket2.setFee(new BigDecimal("3.00"));
		List<ParkingTicket> ticketList = Arrays.asList(ticket1, ticket2);
		Mockito.when(ticketRepository.findAllByParkingZoneAndStopTimeBetween
				(zone, LocalDateTime.of(2018, 03, 12, 0, 0), LocalDateTime.of(2018, 03, 13, 0, 0).minusNanos(1l)))
				.thenReturn(ticketList);
		//when
		BigDecimal income = zoneService.checkIncomeForDay("2018-03-12", zone);
		//then
		Assert.assertEquals(new BigDecimal("4.00"), income);
	}
	
	@Test(expected = DateTimeParseException.class)
	public void testCheckIncomeForDay_bad_string_data() {
		// given
		//when
		BigDecimal income = zoneService.checkIncomeForDay("2018-03-1", zone);
		//then catch exception
		
	}

}
