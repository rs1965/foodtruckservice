package com.radient.ftsapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "fooditems")
public class FoodItem {

    @Id
    @GeneratedValue
    @Column(name = "itemId", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID itemId;

    @Column(name = "itemPrice", nullable = false)
    private double itemPrice;
}
