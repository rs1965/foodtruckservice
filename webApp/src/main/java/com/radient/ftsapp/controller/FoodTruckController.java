package com.radient.ftsapp.controller;

import com.radient.ftsapp.model.FoodTruck;
import com.radient.ftsapp.service.FoodTruckService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FoodTruckController {

    private final FoodTruckService foodTruckService;
    @Getter
    private double aDouble;

    @Autowired
    public FoodTruckController(FoodTruckService foodTruckService) {
        this.foodTruckService = foodTruckService;
    }

    @GetMapping("/getAllLocationDetails")
    public List<FoodTruck> getAllFoodTrucks(@RequestParam(value = "city", required = false) String city,@RequestParam(value = "status", required = false) boolean status) {
        if ("ny".equalsIgnoreCase(city)) {
            return foodTruckService.fetchNYFoodTrucks(status);
        } else {
            return foodTruckService.fetchSFFoodTrucks(status); // Default to SF if no city or unknown city is specified
        }
    }

    @GetMapping("/getFoodTruckByLocation")
    public FoodTruck getFoodTruckByLocation(@RequestParam("latitude") double latitude,
                                            @RequestParam("longitude") double longitude,
                                            @RequestParam(value = "radius", defaultValue = "5") double radius,
                                            @RequestParam(value = "city", required = false) String city,@RequestParam(value = "status", required = false) boolean status) {
        this.aDouble = radius;
        List<FoodTruck> foodTrucks;
        if ("ny".equalsIgnoreCase(city)) {
            foodTrucks = foodTruckService.fetchNYFoodTrucks(status);
        } else {
            foodTrucks = foodTruckService.fetchSFFoodTrucks(status);
        }

        FoodTruck nearestFoodTruck = null;
        double nearestDistance = Double.MAX_VALUE;

        for (FoodTruck foodTruck : foodTrucks) {
            double foodTruckLat = foodTruck.getLatitude();
            double foodTruckLng = foodTruck.getLongitude();
            double distance = calculateDistance(latitude, longitude, foodTruckLat, foodTruckLng);

            if (distance < nearestDistance) {
                nearestDistance = distance;
                nearestFoodTruck = foodTruck;
            }
        }

        return nearestFoodTruck;
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