package com.udemx.carRentalService.dao;

import com.udemx.carRentalService.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {
}
