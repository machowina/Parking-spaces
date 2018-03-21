package com.machowina.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
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
