package com.machowina.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return zoneRepository.findFirstBy();
	}

	@Override
	public ParkingZone findOne(Long parkingZoneId) {
		return zoneRepository.findOne(parkingZoneId);
	}

}
