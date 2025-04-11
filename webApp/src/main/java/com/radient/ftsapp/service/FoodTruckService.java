package com.radient.ftsapp.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.radient.ftsapp.model.FoodTruck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONObject;
import org.json.JSONArray;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.net.URLEncoder;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

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
       @Value("${foodtruck.mock.enabled}")
        private boolean mockEnabled;
    
        @Value("${google.api.key:YOUR_API_KEY_HERE}")
        private String GOOGLE_API_KEY;
    
        public List<Map<String, Object>> findNearbyFoodTrucks(String address, boolean useMock) throws IOException {
            if (useMock || mockEnabled) {
                return getMockedFoodTrucks(address);
            }
    
            String geoUrl = "https://maps.googleapis.com/maps/api/geocode/json?address=" +
                    URLEncoder.encode(address, "UTF-8") + "&key=" + GOOGLE_API_KEY;
            String geoResponse = sendHttpRequest(geoUrl);
            JSONObject geoJson = new JSONObject(geoResponse);
            JSONObject location = geoJson.getJSONArray("results")
                    .getJSONObject(0)
                    .getJSONObject("geometry")
                    .getJSONObject("location");
            double lat = location.getDouble("lat");
            double lng = location.getDouble("lng");
    
            String searchUrl = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=food+truck&location=" +
                    lat + "," + lng + "&radius=3000&key=" + GOOGLE_API_KEY;
            String searchResponse = sendHttpRequest(searchUrl);
            JSONObject searchJson = new JSONObject(searchResponse);
    
            JSONArray results = searchJson.getJSONArray("results");
            List<Map<String, Object>> trucks = new ArrayList<>();
            for (int i = 0; i < results.length(); i++) {
                JSONObject place = results.getJSONObject(i);
                Map<String, Object> truck = new HashMap<>();
                truck.put("name", place.optString("name"));
                truck.put("address", place.optString("formatted_address"));
                truck.put("rating", place.optDouble("rating", 0.0));
                trucks.add(truck);
            }
            return trucks;
        }
    
        private String sendHttpRequest(String urlStr) throws IOException {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) response.append(inputLine);
            in.close();
            return response.toString();
        }
    
        private List<Map<String, Object>> getMockedFoodTrucks(String address) {
            List<Map<String, Object>> trucks = new ArrayList<>();
    
            Map<String, Object> truck1 = new HashMap<>();
            truck1.put("name", "Mock Taco Truck");
            truck1.put("address", address + ", Street 101");
            truck1.put("rating", 4.5);
    
            Map<String, Object> truck2 = new HashMap<>();
            truck2.put("name", "Mock BBQ Truck");
            truck2.put("address", address + ", Avenue 42");
            truck2.put("rating", 4.2);
    
            trucks.add(truck1);
            trucks.add(truck2);
            return trucks;
        }
}
