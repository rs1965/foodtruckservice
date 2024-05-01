package com.radient.ftsapp.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class FoodTruck {

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
    public static class Location {
        @JsonProperty("latitude")
        private double latitude;

        @JsonProperty("longitude")
        private double longitude;

        @JsonProperty("human_address")
        private String humanAddress;

        // Getters and setters for Location class
        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public String getHumanAddress() {
            return humanAddress;
        }

        public void setHumanAddress(String humanAddress) {
            this.humanAddress = humanAddress;
        }
    }

    // Getters and setters for Facility class fields
    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(String facilityType) {
        this.facilityType = facilityType;
    }

    public String getCnn() {
        return cnn;
    }

    public void setCnn(String cnn) {
        this.cnn = cnn;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBlockLot() {
        return blockLot;
    }

    public void setBlockLot(String blockLot) {
        this.blockLot = blockLot;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public String getPermit() {
        return permit;
    }

    public void setPermit(String permit) {
        this.permit = permit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getReceived() {
        return received;
    }

    public void setReceived(String received) {
        this.received = received;
    }

    public String getPriorPermit() {
        return priorPermit;
    }

    public void setPriorPermit(String priorPermit) {
        this.priorPermit = priorPermit;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getComputedRegionYftqJ783() {
        return computedRegionYftqJ783;
    }

    public void setComputedRegionYftqJ783(String computedRegionYftqJ783) {
        this.computedRegionYftqJ783 = computedRegionYftqJ783;
    }

    public String getComputedRegionP5ajWyqh() {
        return computedRegionP5ajWyqh;
    }

    public void setComputedRegionP5ajWyqh(String computedRegionP5ajWyqh) {
        this.computedRegionP5ajWyqh = computedRegionP5ajWyqh;
    }

    public String getComputedRegionRxqgMtj9() {
        return computedRegionRxqgMtj9;
    }

    public void setComputedRegionRxqgMtj9(String computedRegionRxqgMtj9) {
        this.computedRegionRxqgMtj9 = computedRegionRxqgMtj9;
    }

    public String getComputedRegionBh8sQ3mv() {
        return computedRegionBh8sQ3mv;
    }

    public void setComputedRegionBh8sQ3mv(String computedRegionBh8sQ3mv) {
        this.computedRegionBh8sQ3mv = computedRegionBh8sQ3mv;
    }

    public String getComputedRegionFyvsAhh9() {
        return computedRegionFyvsAhh9;
    }

    public void setComputedRegionFyvsAhh9(String computedRegionFyvsAhh9) {
        this.computedRegionFyvsAhh9 = computedRegionFyvsAhh9;
    }
}

