package com.radient.ftsapp.service;

import com.radient.ftsapp.model.FoodTruckDTO;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
@Service
public class FoodTruckService {

    private static final String API_URL = "https://data.sfgov.org/resource/rqzj-sfat.json";

    private final RestTemplate restTemplate;

    @Autowired
    public FoodTruckService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<FoodTruckDTO> getFoodTrucksWithinRadius(double latitude, double longitude, double radius) {
        // Build the URL with query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(API_URL)
                .queryParam("$where", String.format("within_circle(location,%f,%f,%f)", latitude, longitude, radius));

        // Make the HTTP GET request to the API
        ResponseEntity<FoodTruckDTO[]> responseEntity = restTemplate.getForEntity(builder.toUriString(), FoodTruckDTO[].class);

        // Check if the response is successful
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            FoodTruckDTO[] foodTrucks = responseEntity.getBody();
            if (foodTrucks != null) {
                return Arrays.asList(foodTrucks);
            }
        }

        // Return an empty list if no data or error
        return Collections.emptyList();
    }
}
