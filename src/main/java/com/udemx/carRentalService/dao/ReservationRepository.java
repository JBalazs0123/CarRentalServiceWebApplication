package com.udemx.carRentalService.dao;

import com.udemx.carRentalService.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
