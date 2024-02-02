package com.udemx.carRentalService.controller;

import com.udemx.carRentalService.entity.Car;
import com.udemx.carRentalService.entity.Reservation;
import com.udemx.carRentalService.service.CarService;
import com.udemx.carRentalService.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rent")
public class ReservationController {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    private CarService carService;

    private ReservationService reservationService;

    @Autowired
    public ReservationController(CarService carService, ReservationService reservationService) {
        this.carService=carService;
        this.reservationService=reservationService;
    }

    @PostMapping("/save")
    public String saveReservation(@Valid @ModelAttribute("reservation") Reservation reservation, BindingResult bindingResult, Model theModel) {

        if(bindingResult.hasErrors()){
            Car car = carService.findById(reservation.getCarId());
            theModel.addAttribute("car", car);
            theModel.addAttribute("reservation", reservation);
            return "rent/rent-car";
        } else {
            reservationService.save(reservation);
            return "redirect:/cars/list";
        }
    }
}
