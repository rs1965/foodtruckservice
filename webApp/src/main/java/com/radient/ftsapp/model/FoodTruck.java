package com.radient.ftsapp.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FoodTruck {

    // Getters and setters for Facility class fields
    @JsonProperty("objectid")
    private String objectId;

    @JsonProperty("applicant")
    private String applicant;

    @JsonProperty("facilitytype")
    private String facilityType;

    @JsonProperty("cnn")
    private String cnn;

    @JsonProperty("locationdescription")
    private String locationDescription;

    @JsonProperty("address")
    private String address;

    @JsonProperty("blocklot")
    private String blockLot;

    @JsonProperty("block")
    private String block;

    @JsonProperty("lot")
    private String lot;

    @JsonProperty("permit")
    private String permit;

    @JsonProperty("status")
    private String status;

    @JsonProperty("x")
    private String x;

    @JsonProperty("y")
    private String y;

    @JsonProperty("latitude")
    private double latitude;

    @JsonProperty("longitude")
    private double longitude;

    @JsonProperty("schedule")
    private String schedule;

    @JsonProperty("received")
    private String received;

    @JsonProperty("priorpermit")
    private String priorPermit;

    @JsonProperty("expirationdate")
    private String expirationDate;

    @JsonProperty("location")
    private Location location;

    @JsonProperty(":@computed_region_yftq_j783")
    private String computedRegionYftqJ783;

    @JsonProperty(":@computed_region_p5aj_wyqh")
    private String computedRegionP5ajWyqh;

    @JsonProperty(":@computed_region_rxqg_mtj9")
    private String computedRegionRxqgMtj9;

    @JsonProperty(":@computed_region_bh8s_q3mv")
    private String computedRegionBh8sQ3mv;

    @JsonProperty(":@computed_region_fyvs_ahh9")
    private String computedRegionFyvsAhh9;

    // Nested Location class for the location field
    @Setter
    @Getter
    public static class Location {
        // Getters and setters for Location class
        @JsonProperty("latitude")
        private double latitude;

        @JsonProperty("longitude")
        private double longitude;

        @JsonProperty("human_address")
        private String humanAddress;

    }

}
