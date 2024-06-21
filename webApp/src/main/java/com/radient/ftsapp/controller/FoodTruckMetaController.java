package com.radient.ftsapp.controller;

import com.radient.ftsapp.model.MetaData;
import com.radient.ftsapp.service.FoodTruckMetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FoodTruckMetaController {

    private final FoodTruckMetaService foodTruckService;

    @Autowired
    public FoodTruckMetaController(FoodTruckMetaService foodTruckService) {
        this.foodTruckService = foodTruckService;
    }

    @GetMapping("/getFoodTruckMeta")
    public List<MetaData> getFoodTruckMeta(@RequestParam(value = "city") String city,
                                           @RequestParam(value = "status") boolean status) {
        return foodTruckService.fetchFoodTruckData(city,status);
    }
}
