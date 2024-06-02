package com.radient.ftsapp.service;

import com.radient.ftsapp.model.FoodTruck;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodTruckService {
    private static final String SF_API_URL = "https://data.sfgov.org/resource/rqzj-sfat.json";
    private static final String NY_API_URL = "https://data.cityofnewyork.us/resource/9w7m-hzhe.json";

    private final RestTemplate restTemplate;

    public FoodTruckService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<FoodTruck> fetchAllFoodTrucks() {
        List<FoodTruck> sfFoodTrucks = fetchFoodTrucksFromAPI(SF_API_URL);
        List<FoodTruck> nyFoodTrucks = fetchFoodTrucksFromAPI(NY_API_URL);

        sfFoodTrucks.addAll(nyFoodTrucks);
        return sfFoodTrucks;
    }

    public FoodTruck findNearestFoodTruck(double latitude, double longitude) {
        List<FoodTruck> foodTrucks = fetchAllFoodTrucks();
        return foodTrucks.stream()
                .min(Comparator.comparingDouble(ft -> calculateDistance(latitude, longitude, ft.getLatitude(), ft.getLongitude())))
                .orElse(null);
    }

    private List<FoodTruck> fetchFoodTrucksFromAPI(String apiUrl) {
        FoodTruck[] foodTrucks = restTemplate.getForObject(apiUrl, FoodTruck[].class);
        assert foodTrucks != null;
        return Arrays.stream(foodTrucks)
                .filter(foodTruck -> !"expired".equalsIgnoreCase(foodTruck.getStatus()))
                .collect(Collectors.toList());
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);
        double latDiff = lat2Rad - lat1Rad;
        double lonDiff = lon2Rad - lon1Rad;
        double a = Math.sin(latDiff / 2) * Math.sin(latDiff / 2)
                + Math.cos(lat1Rad) * Math.cos(lat2Rad)
                * Math.sin(lonDiff / 2) * Math.sin(lonDiff / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double EARTH_RADIUS_MILES = 3958.8;
        return EARTH_RADIUS_MILES * c;
    }
}
