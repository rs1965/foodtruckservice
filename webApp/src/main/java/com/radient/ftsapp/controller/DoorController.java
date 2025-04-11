package com.radient.ftsapp.controller;

import com.radient.ftsapp.model.DoorDashCreateOrder;
import com.radient.ftsapp.model.DoorDashQuoteAccept;
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

    @PostMapping("/doorDash/quoteOrder")
    public ResponseEntity<ResponseObject<Object>> quoteOrder(@Valid @RequestBody OrderRequest orderPayload,
                                                              @RequestHeader String API_KEY) {
//        OrderRequest orderRequest = orderPayload.getOrderRequest();
//        System.out.println(API_KEY);
//        String API_KEY = orderPayload.getApiKey();
        ResponseObject<Object> responseObject = doorDashService.quoteOrder(orderPayload,API_KEY);
        if (responseObject.isSuccess()) {
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/doorDash/acceptQuote")
    public ResponseEntity<ResponseObject<Object>> acceptQuote(@Valid @RequestBody DoorDashQuoteAccept doorDashQuoteAccept,
                                                             @RequestHeader String API_KEY,@RequestParam String id) {
//        OrderRequest orderRequest = orderPayload.getOrderRequest();
//        System.out.println(API_KEY);
//        String API_KEY = orderPayload.getApiKey();
        ResponseObject<Object> responseObject = doorDashService.acceptQuote(doorDashQuoteAccept,API_KEY,id);
        if (responseObject.isSuccess()) {
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/doorDash/createDelivery")
    public ResponseEntity<ResponseObject<Object>> createDelivery(@Valid @RequestBody DoorDashCreateOrder doorDashCreateOrder,
                                                              @RequestHeader String API_KEY) {
        ResponseObject<Object> responseObject = doorDashService.createDelivery(doorDashCreateOrder,API_KEY);
        if (responseObject.isSuccess()) {
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/doorDash/getDeliveryStatus")
    public ResponseEntity<ResponseObject<Object>> getDeliveryStatus(@RequestParam String id, @RequestHeader String API_KEY) {
        ResponseObject<Object> responseObject = doorDashService.getDeliveryStatus(id,API_KEY);
        if (responseObject.isSuccess()) {
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

//    @PatchMapping("/doorDash/updateDelivery")
//    public ResponseEntity<ResponseObject<Object>> updateDelivery(@Valid @RequestBody DoorDashCreateOrder doorDashCreateOrder,
//                                                                 @RequestHeader String API_KEY, @RequestParam String id) {
//        ResponseObject<Object> responseObject = doorDashService.updateDelivery(doorDashCreateOrder,API_KEY,id);
//        if (responseObject.isSuccess()) {
//            return new ResponseEntity<>(responseObject, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
//        }
//    }

    @PutMapping("/doorDash/cancelDelivery")
    public ResponseEntity<ResponseObject<Object>> cancelDelivery(@RequestParam String id, @RequestHeader String API_KEY) {
        ResponseObject<Object> responseObject = doorDashService.cancelDelivery(id,API_KEY);
        if (responseObject.isSuccess()) {
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }
}
