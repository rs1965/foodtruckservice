package com.radient.ftsapp.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.radient.ftsapp.model.FoodTruck;
import org.slf4j.Logger;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public abstract class BaseFoodTruckService {

    protected final RestTemplate restTemplate;
    protected final ObjectMapper objectMapper;
    protected final Logger logger;

    protected BaseFoodTruckService(RestTemplate restTemplate, ObjectMapper objectMapper, Logger logger) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.logger = logger;
    }

    protected List<FoodTruck> fetchData(String apiUrl, List<FoodTruck> cache) {
        try {
            FoodTruck[] foodTrucks = restTemplate.getForObject(apiUrl, FoodTruck[].class);
            if (foodTrucks != null) {
                cache.clear();
                cache.addAll(Arrays.asList(foodTrucks));
                return Arrays.asList(foodTrucks);
            } else {
                return cache;
            }
        } catch (HttpClientErrorException | ResourceAccessException e) {
            logger.error("Error fetching data from API {}: {}", apiUrl, e.getMessage(), e);
            return cache;
        }
    }

    protected void loadCacheFromFile(String filePath, List<FoodTruck> cache) {
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

    protected void saveCacheToFile(String filePath, List<FoodTruck> cache) {
        try {
            objectMapper.writeValue(new File(filePath), cache);
        } catch (IOException e) {
            logger.error("Error saving cached data to file {}: {}", filePath, e.getMessage(), e);
        }
    }
}
