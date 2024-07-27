package com.radient.ftsapp.model;


import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class OrderInsert {
    // Getters and Setters
    @NotNull(message = "Foodtruck ID is required")
    private String foodtruckId;

    @NotNull(message = "Order ID is required")
    private String orderId;

    @NotNull(message = "Customer ID is required")
    private String customerId;

    @NotNull(message = "Order date is required")
    private LocalDate orderDate;

    @NotEmpty(message = "Items list cannot be empty")
    private List<@Valid Items> items;

    @Positive(message = "Total price must be greater than 0")
    private double totalPrice;

}