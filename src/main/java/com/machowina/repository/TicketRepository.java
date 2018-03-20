package com.machowina.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.machowina.model.ParkingTicket;
import com.machowina.model.ParkingZone;

@Repository
public interface TicketRepository extends JpaRepository<ParkingTicket, Long> {
	
	ParkingTicket findOneByCarIdAndIsStoppedFalse(Long carId);
	List<ParkingTicket> findByCarLicenseAndParkingZoneIdAndIsStoppedFalse(String carLicense, Long zoneId);
	List<ParkingTicket> findByCarLicenseAndIsStoppedFalse(String carLicense);
	List<ParkingTicket> findAllByParkingZoneAndStopTimeBetween(ParkingZone zone, LocalDateTime startTime,
			LocalDateTime endTime);

}
