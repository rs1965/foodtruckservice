package com.radient.ftsapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@jakarta.persistence.Entity
@Table(name = "fooditems")
public class FoodItem {

    @jakarta.persistence.Id
    @GeneratedValue
    @Column(name = "itemId", nullable = false)
    private Long itemId;

    @Column(name = "itemPrice", nullable = false)
    private double itemPrice;
}
