package com.radient.ftsapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "foodItems")
@Getter
@Setter
public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @Column(nullable = false)
    @NotNull(message = "Food Truck ID is required")
    private String foodTruckId;

    @Column(nullable = false)
    @NotNull(message = "item description is required")
    private String itemDescription;

    @Column(nullable = false)
    @NotNull(message = "item name is required")
    private String itemName;

    @Column(nullable = false)
    @NotNull(message = "Item Price is required")
    @Positive(message = "Item Price must be positive")
    private double itemPrice;

    @Lob
    @Column(nullable = false)
    @NotNull(message = "Item Image is required")
    private String itemImages;
}