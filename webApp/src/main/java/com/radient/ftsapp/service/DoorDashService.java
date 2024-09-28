package com.radient.ftsapp.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.radient.ftsapp.model.OrderRequest;
import com.radient.ftsapp.utils.ResponseObject;
import jakarta.transaction.Transactional;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class DoorDashService {

    private ObjectMapper objectMapper;
    private static final String DOORDASH_URL = "https://openapi.doordash.com/drive/v2/quotes";
            //"https://api.doordash.com/v1/orders";
    public DoorDashService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Transactional
    public ResponseObject<Object> createOrder(OrderRequest orderRequest, String API_KEY) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            // Set up headers for API Key
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + API_KEY);

            // Set up the request body
            HttpEntity<OrderRequest> requestEntity = new HttpEntity<>(orderRequest, headers);

            // Send the request
            ResponseEntity<String> response = restTemplate.exchange(DOORDASH_URL, HttpMethod.POST, requestEntity, String.class);
            // Assuming the response will contain the order ID or success indicator
            System.out.println(response.getBody());
            if (response.getStatusCode().is2xxSuccessful()) {
                JsonNode responseData = objectMapper.readTree(response.getBody());

                return new ResponseObject<>(true, "Order Created Successfully", responseData); // Replace `1` with actual order ID or a relevant integer value if available
            } else {
                return new ResponseObject<>(false, "Failed to create order: " + response.getStatusCode(), null);
            }
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            e.printStackTrace();
            return new ResponseObject<>(false, "HTTP error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseObject<>(false, "Unexpected error: " + e.getMessage(), null);
        }
    }

}
