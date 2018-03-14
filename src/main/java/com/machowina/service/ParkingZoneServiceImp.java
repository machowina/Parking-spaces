package com.machowina.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.machowina.exception.EntityNotFoundException;
import com.machowina.model.ParkingZone;
import com.machowina.repository.ParkingZoneRepository;

@Service
public class ParkingZoneServiceImp implements ParkingZoneService {
	

	private final ParkingZoneRepository zoneRepository;

	@Autowired
	public ParkingZoneServiceImp(ParkingZoneRepository zoneRepository) {
		super();
		this.zoneRepository = zoneRepository;
	}
	

	@Override
	public ParkingZone findDeafultZone() {
		ParkingZone defaultZone = zoneRepository.findFirstBy();
		if (defaultZone == null) {
			throw new EntityNotFoundException("There is no parking zone available");
		} else {
			return defaultZone;
		}
	}

	@Override
	public ParkingZone findOne(Long parkingZoneId) {
		ParkingZone parkingZone = zoneRepository.findOne(parkingZoneId);
		
		if (parkingZone == null) {
			throw new EntityNotFoundException("There is no parking zone with this id");
		} else {
			return parkingZone;
		}
	}

}
