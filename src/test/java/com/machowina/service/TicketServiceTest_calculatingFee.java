package com.machowina.service;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.machowina.exception.TicketNotStoppedException;
import com.machowina.model.ParkingRates;
import com.machowina.model.ParkingTicket;
import com.machowina.model.ParkingZone;
import com.machowina.model.User;
import com.machowina.repository.TicketRepository;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceTest_calculatingFee {
	
	private TicketService ticketService;

	@Mock
	private CarService carService;
	@Mock
	private ParkingZoneService zoneService;
	@Mock
	private TicketRepository ticketRepository;
	@Mock
	private ParkingRatesService parkingRatesService;
	
	private ParkingZone zone;
	private ParkingRates rates;
	private ParkingRates vipRates;
	
	@Before
	public void setUp() throws Exception {
		ticketService = new TicketServiceImp(carService, zoneService, ticketRepository, parkingRatesService);

		rates = new ParkingRates(zone, "regular", new BigDecimal("1.00"), new BigDecimal("2.00"), new BigDecimal("2.00"));
		vipRates = new ParkingRates(zone, "vip", BigDecimal.ZERO, new BigDecimal("2.00"), new BigDecimal("1.50"));
		}

	@Test
	public void calculateFeeForGivenDuration_3_hours() {
		//given
		Duration duration = Duration.ofHours(3l);
		//when
		BigDecimal fee = ticketService.calculateFeeForGivenDuration(duration, rates);
		//then
		Assert.assertEquals(new BigDecimal("7.00"), fee);
	}
	
	@Test
	public void calculateFeeForGivenDuration_12_minutes() {
		//given
		Duration duration = Duration.ofMinutes(12l);
		//when
		BigDecimal fee = ticketService.calculateFeeForGivenDuration(duration, rates);
		//then
		Assert.assertEquals(new BigDecimal("0.20"), fee);
	}
	
	@Test
	public void calculateFeeForGivenDuration_5_hours_30_minutes() {
		//given
		Duration duration = Duration.ofMinutes(330l);
		//when
		BigDecimal fee = ticketService.calculateFeeForGivenDuration(duration, rates);
		//then
		Assert.assertEquals(new BigDecimal("47.00"), fee);
	}
	
	@Test
	public void calculateFeeForGivenDuration_VIP_20_minutes() {
		//given
		Duration duration = Duration.ofMinutes(20l);
		//when
		BigDecimal fee = ticketService.calculateFeeForGivenDuration(duration, vipRates);
		//then
		Assert.assertEquals(new BigDecimal("0.00"), fee);
	}
	
	@Test
	public void calculateFeeForGivenDuration_VIP_3_hours() {
		//given
		Duration duration = Duration.ofHours(3l);
		//when
		BigDecimal fee = ticketService.calculateFeeForGivenDuration(duration, vipRates);
		//then
		Assert.assertEquals(new BigDecimal("5.00"), fee);
	}
	
	@Test
	public void calculateTicketFee_3_hours() {
		//given
		User driver = new User("kowalski","pass","regular");
		ParkingTicket ticket  = new ParkingTicket(LocalDateTime.now(), zone, null, driver);
		ticket.setStopTime(LocalDateTime.now().plusHours(3l));
		ticket.setStopped(true);
		ticket.setId(1l);
		Mockito.when(ticketRepository.findOne(1l)).thenReturn(ticket);
		Mockito.when(parkingRatesService.findOneByDriverTypeAndParkingZone("regular", zone)).thenReturn(rates);
		Mockito.when(ticketRepository.save(ticket)).thenReturn(ticket);
		
		//when
		BigDecimal fee = ticketService.calculateTicketFee(1l);
		//then
		Assert.assertEquals(new BigDecimal("7.00"), fee);
		Assert.assertEquals(ticket.getFee(), fee);
	}
	@Test(expected = TicketNotStoppedException.class)
	public void calculateTicketFee_not_stopped() {
		//given
		User driver = new User("kowalski","pass","regular");
		ParkingTicket ticket  = new ParkingTicket(LocalDateTime.now(), zone, null, driver);
		ticket.setStopTime(LocalDateTime.now().plusHours(3l));
		ticket.setId(1l);
		Mockito.when(ticketRepository.findOne(1l)).thenReturn(ticket);
		Mockito.when(parkingRatesService.findOneByDriverTypeAndParkingZone("regular", zone)).thenReturn(rates);
		Mockito.when(ticketRepository.save(ticket)).thenReturn(ticket);
		
		//when
		BigDecimal fee = ticketService.calculateTicketFee(1l);
		//then catch exception
		Assert.assertTrue(ticket.getFee()==null);
	}

}
