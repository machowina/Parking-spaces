package com.machowina.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.money.MonetaryAmount;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Required;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class ParkingTicket {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@NotNull
	private LocalDateTime startTime;
	private LocalDateTime stopTime;
	
	@ManyToOne
	@JoinColumn
	@NotNull
	private ParkingZone parkingZone;
	
	@ManyToOne
	@JoinColumn
	@NotNull
	private Car car;
	
	@ManyToOne
	@JoinColumn
	@NotNull
	private User driver;

	private BigDecimal fee;
	
	private boolean isStopped;
	private boolean isPaid;
	
	public ParkingTicket(LocalDateTime startTime, ParkingZone parkingZone, Car car, User driver) {
		super();
		this.startTime = startTime;
		this.parkingZone = parkingZone;
		this.car = car;
		this.driver = driver;
	}
	
	
	
}	
