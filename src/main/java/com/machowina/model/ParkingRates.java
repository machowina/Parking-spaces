package com.machowina.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class ParkingRates {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@ManyToOne
	@JoinColumn
	private ParkingZone parkingZone;
	
	private String driverType;
	
	private Long firstHourCostInPenniesPLN;
	
	private Long secondHourCostInPenniesPLN;
	
	private Double nextHourRate;
}
