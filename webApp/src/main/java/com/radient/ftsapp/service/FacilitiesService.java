package com.radient.ftsapp.service;

import com.radient.ftsapp.model.FoodTruck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacilitiesService {

    private static final String API_URL = "https://data.sfgov.org/resource/rqzj-sfat.json";

    private final RestTemplate restTemplate;
    private List<FoodTruck> cachedResponse; // In-memory cache

    @Autowired
    public FacilitiesService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.cachedResponse = new ArrayList<>(); // Initialize as empty list
    }

    public List<FoodTruck> fetchFacilities() {
        if (cachedResponse.isEmpty()) {
            fetchFacilitiesFromAPI(); // Fetch data from API and populate cache
        }
        return cachedResponse; // Return cached response
    }

    private void fetchFacilitiesFromAPI() {
        try {
            // Fetch data from API and convert it to a list of FoodTruck objects
            FoodTruck[] foodTruckArray = restTemplate.getForObject(API_URL, FoodTruck[].class);
            if (foodTruckArray != null) {
                cachedResponse = List.of(foodTruckArray);
            } else {
                System.err.println("Received null response from API");
            }
        } catch (HttpClientErrorException | ResourceAccessException e) {
            // Handle exceptions related to API call
            System.err.println("Error fetching data from API: " + e.getMessage());
        }
    }
}
