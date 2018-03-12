package com.machowina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.machowina.model.ParkingRates;

@Repository
public interface ParkingRatesRepository extends JpaRepository<ParkingRates, Long> {

}
