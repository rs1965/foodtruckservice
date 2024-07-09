package com.radient.ftsapp.controller;

import com.radient.ftsapp.model.FoodTruck;
import com.radient.ftsapp.service.FoodTruckService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class FoodTruckControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FoodTruckService foodTruckService;

    @Test
    public void testGetFoodTrucks() throws Exception {
        FoodTruck sfTruck = new FoodTruck();
        sfTruck.setStatus("approved");

        FoodTruck nyTruck = new FoodTruck();
        nyTruck.setStatus("approved");

        Mockito.when(foodTruckService.fetchSFFoodTrucks(true)).thenReturn(Arrays.asList(sfTruck, nyTruck));

        mockMvc.perform(MockMvcRequestBuilders.get("/getAllLocationDetails"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }
}
