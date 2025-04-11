package com.radient.ftsapp.controller;

import com.radient.ftsapp.model.FoodItem;
import com.radient.ftsapp.model.User;
import com.radient.ftsapp.service.FoodItemService;
import com.radient.ftsapp.service.UserService;
import com.radient.ftsapp.utils.ResponseObject;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FoodItemController {

    private final FoodItemService foodItemService;

    @Autowired
    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @PostMapping("/item/addItem")
    public ResponseEntity<ResponseObject<Integer>> createItem(@Valid @RequestBody FoodItem foodItem) {
        ResponseObject<Integer> responseObject = foodItemService.insertItem(foodItem);
        if (responseObject.isSuccess()) {
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }
}
