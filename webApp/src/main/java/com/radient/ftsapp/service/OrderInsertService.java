package com.radient.ftsapp.service;

import com.radient.ftsapp.model.OrderInsert;
import org.springframework.stereotype.Service;

@Service
public class OrderInsertService {

    public OrderInsert createOrder(OrderInsert order) {
        // Business logic to create and save the order
        // e.g., calculating total price, saving to the database, etc.
        return order;
    }
}
