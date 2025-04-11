package com.radient.ftsapp.model;


import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class DoorDashAddress {
    private String verification_type;
    private String verification_code;

    public DoorDashAddress() {}
}
