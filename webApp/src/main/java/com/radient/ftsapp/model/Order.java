package com.radient.ftsapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotNull(message = "Order ID is required")
    @Size(min = 1, message = "Order ID is required")
    private String orderId;

    @Column(nullable = false)
    @NotNull(message = "Food Truck ID is required")
    @Size(min = 1, message = "Food Truck ID is required")
    private String foodtruckId;

    @Column(nullable = false)
    @NotNull(message = "Customer ID is required")
    @Size(min = 1, message = "Customer ID is required")
    private String customerId;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(nullable = false)
    @NotNull(message = "Total Price is required")
    @Positive(message = "Total Price must be positive")
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Items> items;
}