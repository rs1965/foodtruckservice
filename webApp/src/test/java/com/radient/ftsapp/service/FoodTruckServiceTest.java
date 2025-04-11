package com.radient.ftsapp.service;

import com.radient.ftsapp.model.FoodTruck;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.List;



public class FoodTruckServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private FoodTruckService foodTruckService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFetchFoodTrucks() {
        FoodTruck sfTruck = new FoodTruck();
        sfTruck.setStatus("approved");

        FoodTruck nyTruck = new FoodTruck();
        nyTruck.setStatus("approved");

        Mockito.when(restTemplate.getForObject("https://data.sfgov.org/resource/rqzj-sfat.json", FoodTruck[].class))
                .thenReturn(new FoodTruck[]{sfTruck});
        Mockito.when(restTemplate.getForObject("https://data.cityofnewyork.us/resource/9w7m-hzhe.json", FoodTruck[].class))
                .thenReturn(new FoodTruck[]{nyTruck});

//        List<FoodTruck> result = foodTruckService.fetchAllFoodTrucks();
//        Assertions.assertEquals(2, result.size());
//        Mockito.verify(restTemplate, Mockito.times(1)).getForObject("https://data.sfgov.org/resource/rqzj-sfat.json", FoodTruck[].class);
//        Mockito.verify(restTemplate, Mockito.times(1)).getForObject("https://data.cityofnewyork.us/resource/9w7m-hzhe.json", FoodTruck[].class);
    }
}
