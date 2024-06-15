package com.radient.ftsapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "orderId", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID orderId;

    @Column(name = "foodtruckId", columnDefinition = "BINARY(16)", nullable = false)
    private UUID foodtruckId;

    @Column(name = "customerId", columnDefinition = "BINARY(16)", nullable = false)
    private UUID customerId;

    @Column(name = "orderDate", nullable = false)
    private LocalDateTime orderDate;
}
