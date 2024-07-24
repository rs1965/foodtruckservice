package com.radient.ftsapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class Items {
    // Getters and Setters
    @Positive(message = "Item Id is Required")
    private String itemId;

    @Positive(message = "Quantity is Required")
    private int qty;

    @Positive(message = "Item Price is Required")
    private double itemPrice;

}