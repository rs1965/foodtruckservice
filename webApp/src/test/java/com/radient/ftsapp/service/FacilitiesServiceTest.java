package com.radient.ftsapp.service;

import com.radient.ftsapp.model.FoodTruck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FacilitiesServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private FacilitiesService facilitiesService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFetchFacilities() {
        FoodTruck sfTruck = new FoodTruck();
        sfTruck.setStatus("approved");

        FoodTruck nyTruck = new FoodTruck();
        nyTruck.setStatus("approved");

        when(restTemplate.getForObject("https://data.sfgov.org/resource/rqzj-sfat.json", FoodTruck[].class))
                .thenReturn(new FoodTruck[]{sfTruck});
        when(restTemplate.getForObject("https://data.cityofnewyork.us/resource/9w7m-hzhe.json", FoodTruck[].class))
                .thenReturn(new FoodTruck[]{nyTruck});

        List<FoodTruck> result = facilitiesService.fetchFacilities();
        assertEquals(2, result.size());
        verify(restTemplate, times(1)).getForObject("https://data.sfgov.org/resource/rqzj-sfat.json", FoodTruck[].class);
        verify(restTemplate, times(1)).getForObject("https://data.cityofnewyork.us/resource/9w7m-hzhe.json", FoodTruck[].class);
    }
}

