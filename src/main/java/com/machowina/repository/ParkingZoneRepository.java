package com.machowina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.machowina.model.ParkingZone;

@Repository
public interface ParkingZoneRepository extends JpaRepository<ParkingZone, Long> {

}
