package com.radient.ftsapp.controller;

import com.radient.ftsapp.model.Order;
import com.radient.ftsapp.service.OrderService;
import com.radient.ftsapp.utils.ResponseObject;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/insertOrder")
    public ResponseEntity<ResponseObject<Integer>> insertOrder(@Valid @RequestBody Order orderRequest) {
        ResponseObject<Integer> responseObject = orderService.insertOrder(orderRequest);
        if (responseObject.isSuccess()) {
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable String id) {
        return orderService.getOrderById(id);
    }
}
