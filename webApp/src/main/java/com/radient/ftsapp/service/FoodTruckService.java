package com.radient.ftsapp.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.radient.ftsapp.model.FoodTruck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Service
public class FoodTruckService{

    private static final Logger logger = LoggerFactory.getLogger(FoodTruckService.class);
    private static final String SF_API_URL = "https://data.sfgov.org/resource/rqzj-sfat.json";
    private static final String NY_API_URL = "https://data.cityofnewyork.us/resource/9w7m-hzhe.json";
    private static final String SF_CACHE_FILE_PATH = "sft.data.json";
    private static final String NY_CACHE_FILE_PATH = "ny.data.json";

    private final RestTemplate restTemplate;
    private final List<FoodTruck> sfCachedResponse;
    private final List<FoodTruck> nyCachedResponse;
    private final ObjectMapper objectMapper;

    @Autowired
    public FoodTruckService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.sfCachedResponse = new CopyOnWriteArrayList<>(); // Thread-safe list
        this.nyCachedResponse = new CopyOnWriteArrayList<>(); // Thread-safe list
        this.objectMapper = objectMapper;
        loadCacheFromFile(SF_CACHE_FILE_PATH, sfCachedResponse); // Load cached data from file on startup
        loadCacheFromFile(NY_CACHE_FILE_PATH, nyCachedResponse); // Load cached data from file on startup
    }

    public List<FoodTruck> fetchSFFoodTrucks(boolean status) {
        return fetchData(SF_API_URL, sfCachedResponse,status);
    }

    public List<FoodTruck> fetchNYFoodTrucks(boolean status) {
        return fetchData(NY_API_URL, nyCachedResponse,status);
    }

    private List<FoodTruck> fetchData(String apiUrl, List<FoodTruck> cache, boolean status) {
        try {
            FoodTruck[] foodTrucks = restTemplate.getForObject(apiUrl, FoodTruck[].class);
            if (foodTrucks != null) {
                cache.clear();
                cache.addAll(Arrays.asList(foodTrucks));
                return filterActiveFoodTrucks(foodTrucks,status);
            } else {
                // Handle the case where the API response is null
                return filterActiveFoodTrucks(cache.toArray(new FoodTruck[0]),status); // Return the filtered cached data
            }
        } catch (HttpClientErrorException | ResourceAccessException e) {
            logger.error("Error fetching data from API {}: {}", apiUrl, e.getMessage(), e);
            return filterActiveFoodTrucks(cache.toArray(new FoodTruck[0]),status); // Return the filtered cached data in case of an error
        }
    }

    public List<FoodTruck> filterActiveFoodTrucks(FoodTruck[] foodTrucks,boolean status) {
        if(!status){
            return Arrays.stream(foodTrucks)
                    .filter(foodTruck -> !"expired".equalsIgnoreCase(foodTruck.getStatus()))
                    .collect(Collectors.toList());
        } else{
            return Arrays.stream(foodTrucks)
                    .collect(Collectors.toList());
        }

    }

    public void loadCacheFromFile(String filePath, List<FoodTruck> cache) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                List<FoodTruck> foodTrucks = objectMapper.readValue(file, new TypeReference<>() {});
                cache.clear();
                cache.addAll(foodTrucks);
                logger.info("Loaded cached data from file: {}", filePath);
            }
        } catch (IOException e) {
            logger.error("Error loading cached data from file {}: {}", filePath, e.getMessage(), e);
        }
    }
}
