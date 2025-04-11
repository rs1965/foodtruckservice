package com.radient.ftsapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodTruck {

    // Fields for San Francisco dataset
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

    // Fields for New York dataset
    @JsonProperty("camis")
    private String camis;

    @JsonProperty("dba")
    private String dba;

    @JsonProperty("boro")
    private String boro;

    @JsonProperty("building")
    private String building;

    @JsonProperty("street")
    private String street;

    @JsonProperty("zipcode")
    private String zipcode;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("cuisine_description")
    private String cuisineDescription;

    @JsonProperty("inspection_date")
    private String inspectionDate;

    @JsonProperty("action")
    private String action;

    @JsonProperty("violation_code")
    private String violationCode;

    @JsonProperty("violation_description")
    private String violationDescription;

    @JsonProperty("critical_flag")
    private String criticalFlag;

    @JsonProperty("score")
    private String score;

    @JsonProperty("record_date")
    private String recordDate;

    @JsonProperty("inspection_type")
    private String inspectionType;

    @JsonProperty("community_board")
    private String communityBoard;

    @JsonProperty("council_district")
    private String councilDistrict;

    @JsonProperty("census_tract")
    private String censusTract;

    @JsonProperty("bin")
    private String bin;

    @JsonProperty("bbl")
    private String bbl;

    @JsonProperty("nta")
    private String nta;

    // Nested Location class for the location field
    @Getter
    @Setter
    public static class Location {
        @JsonProperty("latitude")
        private double latitude;

        @JsonProperty("longitude")
        private double longitude;

        @JsonProperty("human_address")
        private String humanAddress;
    }
}
