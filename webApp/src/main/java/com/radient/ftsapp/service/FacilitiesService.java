package com.radient.ftsapp.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.radient.ftsapp.model.FoodTruck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class FacilitiesService {

    private static final Logger logger = LoggerFactory.getLogger(FacilitiesService.class);
    private static final String SF_API_URL = "https://data.sfgov.org/resource/rqzj-sfat.json";
    private static final String NY_API_URL = "https://data.cityofnewyork.us/resource/9w7m-hzhe.json";
    private static final String SF_CACHE_FILE_PATH = "sft.data.json";
    private static final String NY_CACHE_FILE_PATH = "ny.data.json";

    private final RestTemplate restTemplate;
    private final List<FoodTruck> sfCachedResponse;
    private final List<FoodTruck> nyCachedResponse;
    private final ObjectMapper objectMapper;

    @Autowired
    public FacilitiesService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.sfCachedResponse = new CopyOnWriteArrayList<>(); // Thread-safe list
        this.nyCachedResponse = new CopyOnWriteArrayList<>(); // Thread-safe list
        this.objectMapper = objectMapper;
        loadCacheFromFile(SF_CACHE_FILE_PATH, sfCachedResponse); // Load cached data from file on startup
        loadCacheFromFile(NY_CACHE_FILE_PATH, nyCachedResponse); // Load cached data from file on startup
    }

    @Scheduled(fixedRateString = "${facilities.cache.refresh.rate:3600000}") // Default to 1 hour
    public void fetchFacilitiesFromAPI() {
        fetchAndCacheData(SF_API_URL, sfCachedResponse, SF_CACHE_FILE_PATH);
        fetchAndCacheData(NY_API_URL, nyCachedResponse, NY_CACHE_FILE_PATH);
    }

    private void fetchAndCacheData(String apiUrl, List<FoodTruck> cache, String cacheFilePath) {
        try {
            // Fetch data from API and convert it to a list of FoodTruck objects
            FoodTruck[] foodTruckArray = restTemplate.getForObject(apiUrl, FoodTruck[].class);
            if (foodTruckArray != null) {
                cache.clear();
                Collections.addAll(cache, foodTruckArray);
                logger.info("Fetched and cached {} food trucks from API: {}", foodTruckArray.length, apiUrl);
                saveCacheToFile(cacheFilePath, cache); // Save the fetched data to the JSON file
            } else {
                logger.error("Received null response from API: {}", apiUrl);
            }
        } catch (HttpClientErrorException | ResourceAccessException e) {
            // Handle exceptions related to API call
            logger.error("Error fetching data from API {}: {}", apiUrl, e.getMessage(), e);
        }
    }

    private void saveCacheToFile(String filePath, List<FoodTruck> cache) {
        try {
            objectMapper.writeValue(new File(filePath), cache);
        } catch (IOException e) {
            logger.error("Error saving cached data to file {}: {}", filePath, e.getMessage(), e);
        }
    }

    private void loadCacheFromFile(String filePath, List<FoodTruck> cache) {
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
