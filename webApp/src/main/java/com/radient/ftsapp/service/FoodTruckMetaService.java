package com.radient.ftsapp.service;

import com.radient.ftsapp.model.MetaData;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FoodTruckMetaService {

    private final RestTemplate restTemplate;
    @Setter
    @Getter
    private FoodTruckService foodTruckService;
    @Getter
    @Setter
    private boolean aBoolean;

    public FoodTruckMetaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<MetaData> fetchFoodTruckData(String city,boolean status) {
        if(Objects.equals(city, "sf")){
            return fetchSFTruckData(status);
        }
        if (Objects.equals(city, "ny")) {
            return fetchNYTruckData(status);
        }
        // Return an empty list as a fallback
        return new ArrayList<>();
    }

    private List<MetaData> fetchSFTruckData(boolean status) {
        List<MetaData> foodTrucks = new ArrayList<>();
        ResponseEntity<SFTruck[]> sfResponse = restTemplate.getForEntity("https://data.sfgov.org/resource/rqzj-sfat.json", SFTruck[].class);
        SFTruck[] sfTrucks = sfResponse.getBody();
        if (sfTrucks != null) {
            for (SFTruck sfTruck : sfTrucks) {
                if(!status && "expired".equalsIgnoreCase(sfTruck.getStatus())){
                    continue;
                }
                MetaData foodTruck = buildMetaData(sfTruck.getLatitude(), sfTruck.getLongitude(), sfTruck.getFoodItem(), sfTruck.getStatus());
                foodTrucks.add(foodTruck);
            }
        }
        return foodTrucks;
    }

    private List<MetaData> fetchNYTruckData(boolean status) {
        this.aBoolean = status;
        List<MetaData> foodTrucks = new ArrayList<>();
        ResponseEntity<NYTruck[]> nyResponse = restTemplate.getForEntity("https://data.cityofnewyork.us/resource/9w7m-hzhe.json", NYTruck[].class);
        NYTruck[] nyTrucks = nyResponse.getBody();
        if (nyTrucks != null) {
            for (NYTruck nyTruck : nyTrucks) {
                MetaData foodTruck = buildMetaData(nyTruck.getLatitude(), nyTruck.getLongitude(), nyTruck.getCuisineDescription(), null);
                foodTrucks.add(foodTruck);
            }
        }
        return foodTrucks;
    }

    private MetaData buildMetaData(String latitude, String longitude, String item, String status) {
        // throw an exception or return optional
        // other validations for longitude and latitude format
        MetaData foodTruck = new MetaData();
        foodTruck.setLatitude(latitude);
        foodTruck.setLongitude(longitude);
        foodTruck.setItem(item);
        foodTruck.setStatus(status);
        return foodTruck;
    }

    // Model classes for API responses
    @Setter
    @Getter
    private static class SFTruck {
        // Getters and Setters
        private String latitude;
        private String longitude;
        private String fooditems;
        private String status;
        public String getFoodItem() {
            return fooditems;
        }

        public void setFoodItem(String fooditem) {
            this.fooditems = fooditem;
        }
    }
    @Setter
    @Getter
    private static class NYTruck {
        private String latitude;
        private String longitude;
        private String cuisine_description;

        public String getCuisineDescription() {
            return cuisine_description;
        }

        public void setCuisineDescription(String cuisine_description) {
            this.cuisine_description = cuisine_description;
        }
    }
}
