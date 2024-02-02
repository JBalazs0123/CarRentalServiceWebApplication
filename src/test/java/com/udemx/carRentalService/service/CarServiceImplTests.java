package com.udemx.carRentalService.service;

import com.udemx.carRentalService.dao.CarRepository;
import com.udemx.carRentalService.dao.ReservationRepository;
import com.udemx.carRentalService.entity.Car;
import com.udemx.carRentalService.entity.DateFilter;
import com.udemx.carRentalService.entity.Reservation;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceImplTests {

    @InjectMocks
    private CarServiceImpl carService;

    @Mock
    private CarRepository carRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @Test
    public void CarServiceImpl_findAllByDateFilter() throws ParseException {

        DateFilter dateFilter = new DateFilter("2024-01-05", "2024-01-08");
        List<Car> cars = Arrays.asList(
                new Car(1,"testCar1", "testcar.jpg", 100, "active"),
                new Car(2, "testCar2", "testcar.jpg", 100, "active")
        );
        List<Reservation> reservations = Arrays.asList(
                new Reservation(1, "2024-01-06", "2024-01-07", "testName", "test@email.com", "test", "1234", 200),
                new Reservation(2, "2024-01-03", "2024-01-04", "testName", "test@email.com", "test", "1234", 200),
                new Reservation(2, "2024-01-09", "2024-01-10", "testName", "test@email.com", "test", "1234", 200)
        );
        when(carRepository.findById(eq(1))).thenReturn(Optional.of(cars.get(0)));
        when(carRepository.findById(eq(2))).thenReturn(Optional.of(cars.get(1)));

        when(carRepository.findAll()).thenReturn(new ArrayList<>(cars));
        when(reservationRepository.findAll()).thenReturn(reservations);

        List<Car> result = carService.findAllByDateFilter(dateFilter);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.size()).isEqualTo(1);

    }
}
