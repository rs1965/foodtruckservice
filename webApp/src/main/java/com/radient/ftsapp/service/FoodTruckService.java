package com.radient.ftsapp.service;
import com.radient.ftsapp.model.FoodTruck;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

@Service
public class FoodTruckService {
    private static final String API_URL = "https://data.sfgov.org/resource/rqzj-sfat.json";

    @Autowired
    private RestTemplate restTemplate;

    public List<FoodTruck> fetchFoodTrucks() {
        FoodTruck[] foodTrucks = restTemplate.getForObject(API_URL, FoodTruck[].class);
        return Arrays.asList(foodTrucks);
    }
}
