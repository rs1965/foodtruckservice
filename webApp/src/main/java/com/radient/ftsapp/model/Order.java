package com.radient.ftsapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@jakarta.persistence.Entity
@Table(name = "orders")
public class Order {

    @jakarta.persistence.Id
    @GeneratedValue
    @Column(name = "orderId", nullable = false)
    private UUID orderId;

    @Column(name = "foodtruckId", nullable = false)
    private UUID foodtruckId;

    @Column(name = "customerId", nullable = false)
    private UUID customerId;

    @Column(name = "orderDate", nullable = false)
    private LocalDateTime orderDate;
}
