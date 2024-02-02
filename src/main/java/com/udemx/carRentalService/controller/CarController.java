package com.udemx.carRentalService.controller;

import com.udemx.carRentalService.entity.Car;
import com.udemx.carRentalService.entity.DateFilter;
import com.udemx.carRentalService.entity.Reservation;
import com.udemx.carRentalService.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    private CarService carService;

    public CarController(CarService carService){
        this.carService=carService;
    }

    @GetMapping("/list-filter")
    public String listCarsFiltered(@ModelAttribute("dateFilter") DateFilter dateFilter, Model theModel){
        List<Car> cars;

        try {
            cars = carService.findAllByDateFilter(dateFilter);
        } catch (ParseException e){
            cars = null;
        }
        theModel.addAttribute("cars", cars);
        theModel.addAttribute("dateFilter", dateFilter);

        return "cars/list-filter-cars";
    }

    @GetMapping("/list")
    public String listCars(Model theModel){
        List<Car> cars = carService.findAll();

        theModel.addAttribute("cars", cars);

        DateFilter dateFilter = new DateFilter();
        theModel.addAttribute("dateFilter", dateFilter);

        return "cars/list-cars";
    }


    @GetMapping("/showFormForRent")
    public String showFormForRent(@RequestParam("carId") int carId, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, Model theModel) {

        Car car = carService.findById(carId);
        theModel.addAttribute("car", car);

        Reservation reservation = new Reservation();
        reservation.setCarId(carId);
        reservation.setReservationStartdate(startDate);
        reservation.setReservationEnddate(endDate);
        reservation.setTotalAmount( (new DateFilter().getDayDifferences(startDate, endDate)) * car.getDailyPrice());

        theModel.addAttribute("reservation", reservation);


        return "rent/rent-car";
    }

}
