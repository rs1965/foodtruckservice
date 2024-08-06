package com.radient.ftsapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "OrderItems")
@Getter
@Setter
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "Item ID is required")
    @Size(min = 1, message = "Item ID is required")
    private String itemId;

    @Column(nullable = false)
    @NotNull(message = "Order ID is required")
    @Size(min = 1, message = "Order ID is required")
    private String orderId;

    @Column(nullable = false)
    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be positive")
    private int qty;

    @Column(nullable = false)
    @NotNull(message = "Item Price is required")
    @Positive(message = "Item Price must be positive")
    private float itemPrice;


    @Column(nullable = false)
    @NotNull(message = "Item Image is required")
    @Lob
    private byte[] itemImg;


    @ManyToOne
    @JoinColumn(name = "orderId", insertable = false, updatable = false)
    private Order order;
}