package com.radient.ftsapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MetaData {
    private String latitude;
    private String longitude;
    private String item;
    private String status;

    public MetaData() {}

    public MetaData(String latitude, String longitude, String item,String status) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.item = item;
        this.status = status;
    }
}

