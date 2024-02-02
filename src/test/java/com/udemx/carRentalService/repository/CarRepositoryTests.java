package com.udemx.carRentalService.repository;

import com.udemx.carRentalService.dao.CarRepository;
import com.udemx.carRentalService.entity.Car;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CarRepositoryTests {

    @Autowired
    private CarRepository carRepository;

    @Test
    public void CarRepository_SaveAll_ReturnSavedCar() {
        Car car = new Car("testCar", "car.jpg", 1, "active");

        Car savedCar = carRepository.save(car);

        Assertions.assertThat(savedCar).isNotNull();
        Assertions.assertThat(savedCar.getId()).isGreaterThan(0);
    }

    @Test
    public void CarRepository_GetAll_ReturnMoreThanOne() {
        Car car1 = new Car("testCar", "car.jpg", 1, "active");
        Car car2 = new Car("testCar", "car.jpg", 1, "active");

        carRepository.save(car1);
        carRepository.save(car2);

        List<Car> cars = carRepository.findAll();

        Assertions.assertThat(cars).isNotNull();
        Assertions.assertThat(cars.size()).isEqualTo(2);
    }

    @Test
    public void CarRepository_FindById_ReturnCar() {
        Car car = new Car("testCar", "car.jpg", 1, "active");

        carRepository.save(car);

        Car theCar = carRepository.findById(car.getId()).get();

        Assertions.assertThat(theCar).isNotNull();
    }

    @Test
    public void CarRepository_Update_ReturnCar() {
        Car car = new Car("testCar", "car.jpg", 1, "active");

        carRepository.save(car);

        Car theCar = carRepository.findById(car.getId()).get();
        theCar.setName("test");
        theCar.setDailyPrice(2);

        carRepository.save(theCar);

        Assertions.assertThat(theCar.getName()).isNotNull();
        Assertions.assertThat(theCar.getDailyPrice()).isEqualTo(2);
    }

    @Test
    public void CarRepository_Delete_ReturnCarIsEmpty() {
        Car car = new Car("testCar", "car.jpg", 1, "active");

        carRepository.save(car);

        carRepository.deleteById(car.getId());
        Optional<Car> carReturn = carRepository.findById(car.getId());

        Assertions.assertThat(carReturn).isEmpty();
    }
}
