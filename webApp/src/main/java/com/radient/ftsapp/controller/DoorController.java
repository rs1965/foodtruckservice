package com.radient.ftsapp.controller;

import com.radient.ftsapp.model.OrderPayload;
import com.radient.ftsapp.model.OrderRequest;
import com.radient.ftsapp.service.DoorDashService;
import com.radient.ftsapp.utils.ResponseObject;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class DoorController {

    @Autowired
    private DoorDashService doorDashService;

    @PostMapping("/doorDash/orders")
    public ResponseEntity<ResponseObject<Integer>> placeOrder(@Valid @RequestBody OrderRequest orderPayload,
                                                              @RequestHeader String API_KEY) {
//        OrderRequest orderRequest = orderPayload.getOrderRequest();
        System.out.println(API_KEY);
//        String API_KEY = orderPayload.getApiKey();
        ResponseObject<Integer> responseObject = doorDashService.createOrder(orderPayload,API_KEY);
        if (responseObject.isSuccess()) {
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }
}
