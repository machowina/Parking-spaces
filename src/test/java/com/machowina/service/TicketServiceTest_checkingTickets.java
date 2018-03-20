package com.machowina.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import com.machowina.repository.TicketRepository;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceTest_checkingTickets {

	private TicketService ticketService;

	@Mock
	private CarService carService;
	@Mock
	private ParkingZoneService zoneService;
	@Mock
	private UserService userService;
	@Mock
	private TicketRepository ticketRepository;
	@Mock
	private ParkingRatesService parkingRatesService;

	private Car car1;
	private Car car2;
	private User driver1;
	private User driver2;
	private ParkingZone zone;

	@Before
	public void setUp() throws Exception {
		ticketService = new TicketServiceImp(carService, zoneService, ticketRepository, userService, parkingRatesService);

		driver1 = new User("kowalski", "pass", "regular");
		driver2 = new User("nowak", "pass", "regular");
		car1 = new Car("WI99021", driver1);
		car2 = new Car("WI99021", driver2);
		zone = new ParkingZone("Warszawa", "Strefa płatnego parkowania");

	}

	@Test
	public void testCheckForValidTicketAnyZone_with_valid_ticket() {
		// given
		ParkingTicket ticket = new ParkingTicket(LocalDateTime.now(), zone, car1, driver1);
		Mockito.when(ticketRepository.findByCarLicenseAndIsStoppedFalse("WI99021"))
		.thenReturn(Arrays.asList(ticket));
		// when
		boolean thereIsAValidTicket = ticketService.checkForValidTicketAnyZone("WI99021");
		// then
		Assert.assertTrue(thereIsAValidTicket);

	}
	
	@Test
	public void testCheckForValidTicketAnyZone_with_two_tickets() {
		// given
		ParkingTicket ticket1 = new ParkingTicket(LocalDateTime.now(), zone, car1, driver1);
		ParkingTicket ticket2 = new ParkingTicket(LocalDateTime.now(), zone, car2, driver2);
		Mockito.when(ticketRepository.findByCarLicenseAndIsStoppedFalse("WI99021"))
		.thenReturn(Arrays.asList(ticket1, ticket2));
		// when
		boolean thereIsAValidTicket = ticketService.checkForValidTicketAnyZone("WI99021");
		// then
		Assert.assertTrue(thereIsAValidTicket);
	}
	
	@Test
	public void testCheckForValidTicketAnyZone_with_no_ticket() {
		// given
		List <ParkingTicket> emptyList = new ArrayList<>();
		Mockito.when(ticketRepository.findByCarLicenseAndIsStoppedFalse("WI99021"))
		.thenReturn(emptyList);
		// when
		boolean thereIsAValidTicket = ticketService.checkForValidTicketAnyZone("WI99021");
		// then
		Assert.assertFalse(thereIsAValidTicket);
	}

	@Test
	public void testCheckForValidTicket() {

	}

}
