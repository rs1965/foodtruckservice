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

    private static final String SF_API_URL = "https://data.sfgov.org/resource/rqzj-sfat.json";
    private static final String NY_API_URL = "https://data.cityofnewyork.us/resource/9w7m-hzhe.json";

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
        cachedResponse = new ArrayList<>();
        cachedResponse.addAll(fetchFacilitiesFromAPI(SF_API_URL));
        cachedResponse.addAll(fetchFacilitiesFromAPI(NY_API_URL));
    }

    private List<FoodTruck> fetchFacilitiesFromAPI(String apiUrl) {
        try {
            FoodTruck[] foodTruckArray = restTemplate.getForObject(apiUrl, FoodTruck[].class);
            if (foodTruckArray != null) {
                return List.of(foodTruckArray);
            } else {
                System.err.println("Received null response from API");
            }
        } catch (HttpClientErrorException | ResourceAccessException e) {
            System.err.println("Error fetching data from API: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
