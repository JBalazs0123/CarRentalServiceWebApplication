package com.udemx.carRentalService.service;

import com.udemx.carRentalService.entity.Car;
import com.udemx.carRentalService.entity.DateFilter;

import java.text.ParseException;
import java.util.List;

public interface CarService {
    List<Car> findAll();

    Car findById(int theId);

    List<Car> findAllByDateFilter(DateFilter dateFilter) throws ParseException;

    Car save(Car theCar);
}
