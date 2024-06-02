package com.radient.ftsapp.controller;

import com.radient.ftsapp.model.FoodTruck;
import com.radient.ftsapp.service.FoodTruckService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FoodTruckController {

    private final FoodTruckService foodTruckService;
    @Setter
    @Getter
    private String city;


    @Autowired
    public FoodTruckController(FoodTruckService foodTruckService) {
        this.foodTruckService = foodTruckService;
    }

    @GetMapping("/foodtrucks")
    public List<FoodTruck> fetchFoodTrucks() {
        return foodTruckService.fetchAllFoodTrucks();
    }

    @GetMapping("/getFoodTruckByLocation")
    public FoodTruck getFoodTruckByLocation(@RequestParam("latitude") double latitude,
                                            @RequestParam("longitude") double longitude,
                                            @RequestParam(value = "city", required = false) String city)
 {
        this.city = city;
        return foodTruckService.findNearestFoodTruck(latitude, longitude);
    }

}
