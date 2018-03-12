package com.machowina.model;

import java.time.LocalDateTime;

import javax.money.MonetaryAmount;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class ParkingTicket {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	private LocalDateTime startTime;
	private LocalDateTime stopTime;
	
	@ManyToOne
	@JoinColumn
	private ParkingZone parkingZone;
	
	@ManyToOne
	@JoinColumn
	private Car car;
	
	@ManyToOne
	@JoinColumn
	private User driver;

	private MonetaryAmount fee;
	
	private boolean isStopped;
	private boolean isPaid;
	
}	
