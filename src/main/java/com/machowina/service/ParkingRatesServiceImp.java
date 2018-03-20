package com.machowina.service;

import org.springframework.stereotype.Service;

import com.machowina.exception.EntityNotFoundException;
import com.machowina.model.ParkingRates;
import com.machowina.model.ParkingZone;
import com.machowina.repository.ParkingRatesRepository;

@Service
public class ParkingRatesServiceImp implements ParkingRatesService {

	private final ParkingRatesRepository parkingRatesRepository;
	
	
	
	public ParkingRatesServiceImp(ParkingRatesRepository parkingRatesRepository) {
		super();
		this.parkingRatesRepository = parkingRatesRepository;
	}



	@Override
	public ParkingRates findOneByDriverTypeAndParkingZone(String driverType, ParkingZone zone) {
		ParkingRates rates = parkingRatesRepository.findOneByDriverTypeAndParkingZone(driverType, zone);
		if (rates == null) {
			throw new EntityNotFoundException("There are no parking rates for that driver type and parking zone");
		} else {
			return rates;
		}
	}

}
