package com.radient.ftsapp.service;

import com.radient.ftsapp.model.FoodTruck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

import java.util.Arrays;
import java.util.List;

@Service
public class FacilitiesService {

    private static final String API_URL = "https://data.sfgov.org/resource/rqzj-sfat.json";

    private final RestTemplate restTemplate;

    @Autowired
    public FacilitiesService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<FoodTruck> fetchFacilities() {
        try {
            // Fetch data from API and convert it to a list of Facilities objects
            FoodTruck[] foodTruckArray = restTemplate.getForObject(API_URL, FoodTruck[].class);
            return Arrays.asList(foodTruckArray);
        } catch (HttpClientErrorException | ResourceAccessException e) {
            // Handle exceptions related to API call
            System.err.println("Error fetching data from API: " + e.getMessage());
            // Return an empty list or handle as per your application's requirements
            return List.of();
        }
    }
}
