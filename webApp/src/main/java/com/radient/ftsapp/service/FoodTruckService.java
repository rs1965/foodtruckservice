package com.radient.ftsapp.service;

import com.radient.ftsapp.model.FoodTruck;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodTruckService {
    private static final String API_URL = "https://data.sfgov.org/resource/rqzj-sfat.json";

    private final RestTemplate restTemplate;

    public FoodTruckService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<FoodTruck> fetchFoodTrucks() {
        FoodTruck[] foodTrucks = restTemplate.getForObject(API_URL, FoodTruck[].class);
        assert foodTrucks != null;
        return Arrays.stream(foodTrucks)
                .filter(foodTruck -> !"expired".equalsIgnoreCase(foodTruck.getStatus()))
                .collect(Collectors.toList());
    }
}
