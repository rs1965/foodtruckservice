package com.radient.ftsapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@jakarta.persistence.Entity
@Table(name = "orderitems")
public class OrderItem {

    @jakarta.persistence.Id
    @GeneratedValue
    @Column(name = "serialNumber", nullable = false)
    private Long serialNumber;

//    @ManyToOne
//    @JoinColumn(name = "orderId", nullable = false)
//    private UUID order;

//    @ManyToOne
//    @JoinColumn(name = "itemId", nullable = false)
//    private FoodItem item;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "type", nullable = false)
//    private ItemType type;

    @Column(name = "itemQty", nullable = false)
    private int itemQty;

    public enum ItemType {
        SIDEORDER,
        DRINKS,
        TOPPINGS
    }
}
