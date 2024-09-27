package com.radient.ftsapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoorDashItem {
    private String name;
    private String description;
    private String barcode;
    private int quantity;
    private String externalId;
    private double volume;
    private double weight;
    private double length;
    private double width;
    private double height;
    private double price;

    public DoorDashItem() {}
}
