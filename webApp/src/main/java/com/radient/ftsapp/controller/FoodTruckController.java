package com.radient.ftsapp.controller;
import com.radient.ftsapp.model.FoodTruck;
import com.radient.ftsapp.service.FoodTruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FoodTruckController {

    private final FoodTruckService foodTruckService;

    @Autowired
    public FoodTruckController(FoodTruckService foodTruckService) {
        this.foodTruckService = foodTruckService;
    }

    // Define the endpoint method for "/"
    @GetMapping("/getAllLocationDetails")
    public List<FoodTruck> getFoodTrucks() {
        // Fetch the list of FoodTruck objects from the service
        // Return the list as a JSON response
        return foodTruckService.fetchFoodTrucks();
    }

    @GetMapping("/getFoodTruckByLocation")
    public FoodTruck getFoodTruckByLocation(@RequestParam("latitude") double latitude,
                                            @RequestParam("longitude") double longitude,
                                            @RequestParam(value = "radius", defaultValue = "5") double radius,
                                            @RequestParam(value = "city", required = false) String city,
                                            @RequestParam(value = "state", required = false) String state,
                                            @RequestParam(value = "zipcode", required = false) String zipcode){
        // Fetch the list of FoodTruck objects from the service
        List<FoodTruck> foodTrucks = foodTruckService.fetchFoodTrucks();

        // Initialize variables to find the nearest food truck
        FoodTruck nearestFoodTruck = null;
        double nearestDistance = Double.MAX_VALUE;

        // Loop through the list of food trucks and find the nearest one
        for (FoodTruck foodTruck : foodTrucks) {
            double foodTruckLat = foodTruck.getLatitude();
            double foodTruckLng = foodTruck.getLongitude();

            // Calculate the distance from the provided location to the food truck's location
            double distance = calculateDistance(latitude, longitude, foodTruckLat, foodTruckLng);

            // Update the nearest food truck if the current distance is smaller
            if (distance < nearestDistance) {
                nearestDistance = distance;
                nearestFoodTruck = foodTruck;
            }
        }

        // Return the nearest food truck as a JSON response
        return nearestFoodTruck;
    }
    
        // Radius of the Earth in miles
        private final double EARTH_RADIUS_MILES = 3958.8;

        // Other methods remain unchanged

        private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
            // Convert latitude and longitude from degrees to radians
            double lat1Rad = Math.toRadians(lat1);
            double lon1Rad = Math.toRadians(lon1);
            double lat2Rad = Math.toRadians(lat2);
            double lon2Rad = Math.toRadians(lon2);

            // Calculate the differences in latitude and longitude
            double latDiff = lat2Rad - lat1Rad;
            double lonDiff = lon2Rad - lon1Rad;

            // Apply the Haversine formula
            double a = Math.sin(latDiff / 2) * Math.sin(latDiff / 2)
                    + Math.cos(lat1Rad) * Math.cos(lat2Rad)
                    * Math.sin(lonDiff / 2) * Math.sin(lonDiff / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

            // Calculate the distance in miles
            return EARTH_RADIUS_MILES * c;
        }
    }
