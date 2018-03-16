package com.machowina.service;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.machowina.exception.EntityNotFoundException;
import com.machowina.exception.TicketAlreadyRunning;
import com.machowina.exception.TicketAlreadyStopped;
import com.machowina.model.Car;
import com.machowina.model.ParkingTicket;
import com.machowina.model.ParkingZone;
import com.machowina.model.User;
import com.machowina.repository.TicketRepository;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceTest {
	
	private TicketService ticketService;
	
	@Mock
	private CarService carService;
	@Mock
	private ParkingZoneService zoneService;
	@Mock
	private UserService userService;
	@Mock
	private TicketRepository ticketRepository;
	
	
	Car car;
	User driver;
	ParkingZone zone;

	@Before
	public void setUp() throws Exception {
		
		ticketService = new TicketServiceImp(carService, zoneService, ticketRepository, userService);
	
		car = new Car("WI99021");
		driver = new User("kowalski","pass","regular");
		zone = new ParkingZone("Warszawa","Strefa płatnego parkowania");
	}
	
	
	
	@Test
	public void testStopTicket() {
		//given
		ParkingTicket ticket  = new ParkingTicket(LocalDateTime.now(), zone, car, driver);
		Mockito.when(ticketRepository.findOne(1l)).thenReturn(ticket);
		//when
		ticketService.stopTicket(1l);
		//then
		Assert.assertTrue(ticket.isStopped());
		Assert.assertNotNull(ticket.getStopTime());
	}
	
	@Test(expected = TicketAlreadyStopped.class)
	public void testStopTicket_already_stopped() {
		//given
		ParkingTicket ticket  = new ParkingTicket(LocalDateTime.now(), zone, car, driver);
		ticket.setStopped(true);
		Mockito.when(ticketRepository.findOne(1l)).thenReturn(ticket);
		//when
		ticketService.stopTicket(1l);
		//then catch exception
	}
	
	@Test
	public void testFindById() {
		//given
		ParkingTicket ticket  = new ParkingTicket(LocalDateTime.now(), zone, car, driver);
		Mockito.when(ticketRepository.findOne(1l)).thenReturn(ticket);
		//when
		ParkingTicket foundTicket = ticketService.findById(1l);
		//then
		Assert.assertEquals(car, foundTicket.getCar());
		Assert.assertEquals(driver, foundTicket.getDriver());
		Assert.assertEquals(zone, foundTicket.getParkingZone());
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testFindById_not_found() {
		//given
		Mockito.when(ticketRepository.findOne(1l)).thenReturn(null);
		//when
		ParkingTicket foundTicket = ticketService.findById(1l);
		//then catch exception
	}
	
	@Test
	public void testCreateTicket() {
		//given
		Mockito.when(carService.findById(1l)).thenReturn(car);
		Mockito.when(userService.findUserForCar(1l)).thenReturn(driver);
		Mockito.when(zoneService.findOne(1l)).thenReturn(zone);
		//when
		ParkingTicket newTicket = ticketService.createTicket(1l, 1l);
		//then
		Assert.assertEquals(car, newTicket.getCar());
		Assert.assertEquals(driver, newTicket.getDriver());
		Assert.assertEquals(zone, newTicket.getParkingZone());
		Assert.assertTrue(LocalDateTime.now().plusSeconds(1l).isAfter(newTicket.getStartTime()));
		Assert.assertEquals(LocalDateTime.now().getMinute(), newTicket.getStartTime().getMinute());
		Assert.assertFalse(newTicket.isStopped());
		Assert.assertFalse(newTicket.isPaid());
		Assert.assertNull(newTicket.getStopTime());
		

	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testCreateTicket_with_no_car() {
		//given
		Mockito.when(carService.findById(1l)).thenThrow(EntityNotFoundException.class);
		Mockito.when(userService.findUserForCar(1l)).thenReturn(driver);
		Mockito.when(zoneService.findOne(1l)).thenReturn(zone);
		//when
		ParkingTicket newTicket = ticketService.createTicket(1l, 1l);
		//then
		
	}

	@Test(expected = TicketAlreadyRunning.class)
	public void testCheckForDuplicatingTicket_when_duplicated() {
		//given
		ParkingTicket ticket  = new ParkingTicket(LocalDateTime.now(), zone, car, driver);
		Mockito.when(ticketRepository.findOneByCarIdAndIsStoppedFalse(17l)).thenReturn(ticket);
		//when
		ticketService.checkForDuplicatingTicket(17l);
		//then catch exception
	}
	
	@Test
	public void testCheckForDuplicatingTicket_when_ok() {
		//given
		Mockito.when(ticketRepository.findOneByCarIdAndIsStoppedFalse(17l)).thenReturn(null);
		//when
		ticketService.checkForDuplicatingTicket(17l);
		//then

	}

	@Test
	public void testSaveTicket() {
		//given
		ParkingTicket ticket  = new ParkingTicket(LocalDateTime.now(), zone, car, driver);
		ticket.setId(3l);
		Mockito.when(ticketRepository.save(ticket)).thenReturn(ticket);
		//when
		Long savedTicketId = ticketService.saveTicket(ticket);
		//then
		Assert.assertEquals(ticket.getId(), savedTicketId);
	}
	@Test(expected = NullPointerException.class)
	public void testSaveTicket_when_null_ticket() {
		//given
		ParkingTicket ticket  = null;
		Mockito.when(ticketRepository.save(ticket)).thenReturn(ticket);
		//when
		Long savedTicketId = ticketService.saveTicket(ticket);
		//then catch exception
		
	} 

}
