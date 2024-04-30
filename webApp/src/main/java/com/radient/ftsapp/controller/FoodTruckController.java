package com.radient.ftsapp.controller;

import com.radient.ftsapp.model.FoodTruckDTO;
import com.radient.ftsapp.service.FoodTruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping("/foodtrucks")
public class FoodTruckController {

    private final FoodTruckService foodTruckService;

    @Autowired
    public FoodTruckController(FoodTruckService foodTruckService) {
        this.foodTruckService = foodTruckService;
    }

    @GetMapping
    public ResponseEntity<Object> getFoodTrucks(@RequestParam("latitude") double latitude,
                                                @RequestParam("longitude") double longitude,
                                                @RequestParam(value = "radius", defaultValue = "1") double radius) {
        List<FoodTruckDTO> foodTrucks = foodTruckService.getFoodTrucksWithinRadius(latitude, longitude, radius);
        return ResponseEntity.ok(foodTrucks);
    }

    // Implement PUT and POST methods as required
}
