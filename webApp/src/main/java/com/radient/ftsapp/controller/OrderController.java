package com.radient.ftsapp.controller;

import com.radient.ftsapp.model.Order;
import com.radient.ftsapp.model.OrderItem;
import com.radient.ftsapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order, @RequestBody List<OrderItem> items) {
        return orderService.createOrder(order, items);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable UUID id) {
        return orderService.getOrderById(id);
    }
}
