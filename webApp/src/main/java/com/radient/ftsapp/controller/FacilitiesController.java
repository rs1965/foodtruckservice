package com.radient.ftsapp.controller;

import com.radient.ftsapp.model.FoodTruck;
import com.radient.ftsapp.service.FoodTruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FacilitiesController {

    private final FoodTruckService foodTruckService;

    @Autowired
    public FacilitiesController(FoodTruckService foodTruckService) {
        this.foodTruckService = foodTruckService;
    }

    @GetMapping("/getAllFoodTrucks")
    public List<FoodTruck> getAllFoodTrucks(@RequestParam(value = "city", required = false) String city,@RequestParam(value = "status", required = false) boolean status) {
        if ("ny".equalsIgnoreCase(city)) {
            return foodTruckService.fetchNYFoodTrucks(status);
        } else {
            return foodTruckService.fetchSFFoodTrucks(status); // Default to SF if no city or unknown city is specified
        }
    }
}
