package com.udemx.carRentalService.controller;

import com.udemx.carRentalService.entity.Car;
import com.udemx.carRentalService.service.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @Test
    public void CarController_listCars() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/cars/list"))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
        assertEquals("cars/list-cars", mvcResult.getModelAndView().getViewName());
        assertNotNull(mvcResult.getModelAndView().getModel().get("cars"));
        assertNotNull(mvcResult.getModelAndView().getModel().get("dateFilter"));
    }

    @Test
    public void CarController_listCarsFiltered() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/cars/list-filter"))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
        assertEquals("cars/list-filter-cars", mvcResult.getModelAndView().getViewName());
        assertNotNull(mvcResult.getModelAndView().getModel().get("cars"));
        assertNotNull(mvcResult.getModelAndView().getModel().get("dateFilter"));
    }

    @Test
    public void CarController_ShowFormForRent() throws Exception {
        int carId = 1;
        String startDate = "2024-01-01";
        String endDate = "2024-01-02";
        Car car = new Car(1, "testCar", "test.jpg", 100, "active");

        when(carService.findById(carId)).thenReturn(car);

        MvcResult mvcResult = mockMvc.perform(get("/cars/showFormForRent")
                .param("carId", String.valueOf(carId))
                .param("startDate", startDate)
                .param("endDate", endDate))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
        assertEquals("rent/rent-car", mvcResult.getModelAndView().getViewName());
        assertNotNull(mvcResult.getModelAndView().getModel().get("car"));
        assertNotNull(mvcResult.getModelAndView().getModel().get("reservation"));
    }
}
