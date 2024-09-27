package com.radient.ftsapp.model;


import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class DoorDashAddress {
    private String city;
    private String state;
    private String street;
//    private String unit;
    private String zip_code;
//    private String fullAddress;

    public DoorDashAddress() {}
}
