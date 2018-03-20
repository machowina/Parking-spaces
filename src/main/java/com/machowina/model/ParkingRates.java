package com.machowina.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class ParkingRates {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@ManyToOne
	@JoinColumn
	private ParkingZone parkingZone;
	
	private String driverType;
	
	private BigDecimal firstHourCost;
	
	private BigDecimal secondHourCost;
	
	private  BigDecimal nextHourRate;

	public ParkingRates(ParkingZone parkingZone, String driverType, BigDecimal firstHourCost, BigDecimal secondHourCost,
			BigDecimal nextHourRate) {
		super();
		this.parkingZone = parkingZone;
		this.driverType = driverType;
		this.firstHourCost = firstHourCost;
		this.secondHourCost = secondHourCost;
		this.nextHourRate = nextHourRate;
	}
	
}
