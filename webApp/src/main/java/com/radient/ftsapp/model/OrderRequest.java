package com.radient.ftsapp.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderRequest {
    private String external_delivery_id;
    private String dropoff_address;
    private String dropoff_phone_number;
    private String pickup_address;
    private DoorDashAddress door_dash_address;
    private double order_value;
}