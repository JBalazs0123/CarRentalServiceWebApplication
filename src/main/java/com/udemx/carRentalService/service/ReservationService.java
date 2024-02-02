package com.udemx.carRentalService.service;

import com.udemx.carRentalService.entity.Reservation;

import java.util.List;

public interface ReservationService {

    Reservation save(Reservation reservation);

    List<Reservation> findAll();

    void deleteById(int theId);
}
