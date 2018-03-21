package com.machowina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.machowina.model.ParkingRates;
import com.machowina.model.ParkingZone;

@Repository
public interface ParkingRatesRepository extends JpaRepository<ParkingRates, Long> {
	ParkingRates findOneByDriverTypeAndParkingZone(String driverType, ParkingZone zone);
}
