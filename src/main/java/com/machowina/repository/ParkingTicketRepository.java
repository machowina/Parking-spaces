package com.machowina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.machowina.model.ParkingTicket;

@Repository
public interface ParkingTicketRepository extends JpaRepository<ParkingTicket, Long> {

}
