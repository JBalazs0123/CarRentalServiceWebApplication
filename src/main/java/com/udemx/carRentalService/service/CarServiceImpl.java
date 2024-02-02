package com.udemx.carRentalService.service;

import com.udemx.carRentalService.dao.CarRepository;
import com.udemx.carRentalService.dao.ReservationRepository;
import com.udemx.carRentalService.entity.Car;
import com.udemx.carRentalService.entity.DateFilter;
import com.udemx.carRentalService.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService{

    private CarRepository carRepository;

    private ReservationRepository reservationRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ReservationRepository reservationRepository) {
        this.carRepository=carRepository;
        this.reservationRepository=reservationRepository;
    }

    @Override
    public List<Car> findAll() {
        List<Car> cars = carRepository.findAll();

        cars.removeIf(car -> !(car.getStatus().equals("active")));

        return cars;
    }

    @Override
    public Car findById(int theId) {
        Optional<Car> result = carRepository.findById(theId);

        Car theCar = null;

        if(result.isPresent()){
            theCar=result.get();
        } else {
            throw new RuntimeException("Did not find car id: " + theId);
        }

        return theCar;
    }

    @Override
    public List<Car> findAllByDateFilter(DateFilter dateFilter) {
        List<Car> cars = findAll();
        List<Reservation> reservations = reservationRepository.findAll();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date reservationStart;
        Date reservationEnd;
        Date carStart;
        Date carEnd;

        try{
            reservationStart = dateFormat.parse(dateFilter.getStartDate());
            reservationEnd = dateFormat.parse(dateFilter.getEndDate());
        } catch (ParseException e) {
            return null;
        }

        for(Reservation reservation : reservations) {
            boolean isFree = true;

            try {
                carStart = dateFormat.parse(reservation.getReservationStartdate());
                carEnd = dateFormat.parse(reservation.getReservationEnddate());
            } catch (ParseException e) {
                return null;
            }

            if ((reservationStart.after(carStart) && reservationStart.before(carEnd)) ||
                    (reservationStart.equals(carStart) || reservationStart.equals(carEnd))) {
                isFree=false;
            }
            if ((reservationEnd.after(carStart) && reservationEnd.before(carEnd)) ||
                    (reservationEnd.equals(carStart) || reservationEnd.equals(carEnd))) {
                isFree=false;
            }
            if ((carStart.after(reservationStart) && carStart.before(reservationEnd)) ||
                    (carStart.equals(reservationStart) || carStart.equals(reservationEnd)) ||
                    (carEnd.after(reservationStart) && carEnd.before(reservationEnd)) ||
                    (carEnd.equals(reservationStart) || carEnd.equals(reservationEnd))) {
                isFree=false;
            }

            if (!isFree) {
                cars.remove(findById(reservation.getCarId()));
            }
        }

        return cars;
    }

    @Override
    public Car save(Car theCar) {
        return carRepository.save(theCar);
    }
}
