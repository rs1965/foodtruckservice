package com.radient.ftsapp.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
@Setter
@Getter
public class OrderRequest {

    // Getters and Setters
    @JsonProperty("pickup_address")
    private DoorDashAddress pickupAddress;
    @JsonProperty("dropoff_address")
    private DropOffAddress dropoffAddress;
    @JsonProperty("order_value")
    private int orderValue;
//    @JsonProperty("delivery_time")
//    private LocalDateTime deliveryTime;
//    @JsonProperty("pickup_time")
//    private LocalDateTime pickupTime;
    @JsonProperty("external_business_name")
    private String externalBusinessName;
    @JsonProperty("external_business_id")
    private String externalBusinessId;
    @JsonProperty("external_store_id")
    private String externalStoreId;
    @JsonProperty("promotion_id")
    private String promotionId;

    private List<DoorDashItem> items;
}