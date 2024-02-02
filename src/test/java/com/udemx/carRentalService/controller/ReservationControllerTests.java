package com.udemx.carRentalService.controller;

import com.udemx.carRentalService.entity.Car;
import com.udemx.carRentalService.entity.Reservation;
import com.udemx.carRentalService.service.CarService;
import com.udemx.carRentalService.service.ReservationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
public class ReservationControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @MockBean
    private ReservationService reservationService;

    @Test
    public void ReservationController_saveReservation() throws Exception {
        Car car = new Car("testCar", "car.jpg", 1, "active");

        when(carService.findById(anyInt())).thenReturn(car);

       MvcResult mvcResult = mockMvc.perform(post("/rent/save")
                .param("carId", "1")
                .param("reservationStartDate", "2024-01-01")
                .param("reservationEndDate", "2024-01-02")
                .param("name", "testName")
                .param("email", "testEmail")
                .param("address", "testAddress")
                .param("phoneNumber", "0123456789")
                .param("total_amount", "100"))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String redirectedUrl = mvcResult.getResponse().getHeader("Location");

        assertEquals(302, status); //302: Found
        assertEquals("/cars/list", redirectedUrl);
        verify(reservationService, times(1)).save(any(Reservation.class));
    }
}
