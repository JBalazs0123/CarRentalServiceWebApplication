package com.udemx.carRentalService.controller;

import com.udemx.carRentalService.entity.Car;
import com.udemx.carRentalService.entity.Reservation;
import com.udemx.carRentalService.service.CarService;
import com.udemx.carRentalService.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private CarService carService;

    private ReservationService reservationService;

    @Autowired
    public AdminController(CarService carService, ReservationService reservationService) {
        this.carService=carService;
        this.reservationService=reservationService;
    }

    @GetMapping("/list-reservations")
    public String listReservation(Model theModel) {

        List<Reservation> reservations = reservationService.findAll();

        theModel.addAttribute("reservations", reservations);

        return "admin/list-reservations";
    }

    @GetMapping("/delete")
    public String deleteReservation(@RequestParam("id") int theId) {
        reservationService.deleteById(theId);

        return "redirect:/admin/list-reservations";
    }

    @GetMapping("/list-cars")
    public String listCars(Model theModel) {

        List<Car> cars = carService.findAll();

        theModel.addAttribute("cars", cars);

        return "admin/list-crud-cars";
    }

    @GetMapping("/formForAdd")
    public String addNewCar(Model theModel) {

        Car car = new Car();
        car.setStatus("active");
        theModel.addAttribute("car", car);

        return "admin/create-car";
    }

    @GetMapping("/formForUpdate")
    public String updateCar(@RequestParam("id") int theId, Model theModel) {

        Car car = carService.findById(theId);

        theModel.addAttribute("car", car);

        return "admin/create-car";
    }

    @GetMapping("/deactivate")
    public String deactivate(@RequestParam("id") int theId){
        Car car = carService.findById(theId);
        car.setStatus("deactivate");
        carService.save(car);
        return "redirect:/admin/list-cars";
    }

    @PostMapping("/save")
    public String saveCar(@ModelAttribute("car") Car car) {
        carService.save(car);

        return "redirect:/admin/list-cars";
    }
}
