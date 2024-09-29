package com.radient.ftsapp.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.radient.ftsapp.model.DoorDashCreateOrder;
import com.radient.ftsapp.model.DoorDashQuoteAccept;
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

    private final ObjectMapper objectMapper;
//    private static final String DOORDASH_URL = "https://openapi.doordash.com/drive/v2/quotes";
            //"https://api.doordash.com/v1/orders";
    public DoorDashService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Transactional
    public ResponseObject<Object> quoteOrder(OrderRequest orderRequest, String API_KEY) {
        String DOORDASH_URL_CREATE_QUOTE = "https://openapi.doordash.com/drive/v2/quotes";
        try {
            RestTemplate restTemplate = new RestTemplate();
            // Set up headers for API Key
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + API_KEY);

            // Set up the request body
            HttpEntity<OrderRequest> requestEntity = new HttpEntity<>(orderRequest, headers);

            // Send the request
            ResponseEntity<String> response = restTemplate.exchange(DOORDASH_URL_CREATE_QUOTE, HttpMethod.POST, requestEntity, String.class);
            // Assuming the response will contain the order ID or success indicator
            if (response.getStatusCode().is2xxSuccessful()) {
                JsonNode responseData = objectMapper.readTree(response.getBody());

                return new ResponseObject<>(true, "Quote Generated Successfully", responseData); // Replace `1` with actual order ID or a relevant integer value if available
            } else {
                return new ResponseObject<>(false, "Failed to Create Quote: " + response.getStatusCode(), null);
            }
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            e.printStackTrace();
            return new ResponseObject<>(false, "HTTP error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseObject<>(false, "Unexpected error: " + e.getMessage(), null);
        }
    }

    @Transactional
    public ResponseObject<Object> acceptQuote(DoorDashQuoteAccept orderRequest, String API_KEY,String external_order_id) {
        String DOORDASH_URL_ACCEPT_QUOTE = "https://openapi.doordash.com/drive/v2/quotes/"+external_order_id+"/accept";
        try {
            RestTemplate restTemplate = new RestTemplate();
            // Set up headers for API Key
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + API_KEY);

            // Set up the request body
            HttpEntity<DoorDashQuoteAccept> requestEntityAcceptQuote = new HttpEntity<>(orderRequest, headers);

            // Send the request
            ResponseEntity<String> responseAcceptQuote = restTemplate.exchange(DOORDASH_URL_ACCEPT_QUOTE, HttpMethod.POST, requestEntityAcceptQuote, String.class);
            // Assuming the response will contain the order ID or success indicator
            if (responseAcceptQuote.getStatusCode().is2xxSuccessful()) {
                JsonNode responseData = objectMapper.readTree(responseAcceptQuote.getBody());

                return new ResponseObject<>(true, "Quote Generated Successfully", responseData);
            } else {
                return new ResponseObject<>(false, "Failed to Create Quote: " + responseAcceptQuote.getStatusCode(), null);
            }
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            e.printStackTrace();
            return new ResponseObject<>(false, "HTTP error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseObject<>(false, "Unexpected error: " + e.getMessage(), null);
        }
    }

    @Transactional
    public ResponseObject<Object> createDelivery(DoorDashCreateOrder doorDashCreateOrder, String API_KEY) {
        String DOORDASH_URL_CREATE_ORDER = "https://openapi.doordash.com/drive/v2/deliveries";
        try {
            RestTemplate restTemplate = new RestTemplate();
            // Set up headers for API Key
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + API_KEY);

            // Set up the request body
            HttpEntity<DoorDashCreateOrder> requestEntityAcceptQuote = new HttpEntity<>(doorDashCreateOrder, headers);

            // Send the request
            ResponseEntity<String> responseCreateOrder = restTemplate.exchange(DOORDASH_URL_CREATE_ORDER, HttpMethod.POST, requestEntityAcceptQuote, String.class);
            // Assuming the response will contain the order ID or success indicator
            if (responseCreateOrder.getStatusCode().is2xxSuccessful()) {
                JsonNode responseData = objectMapper.readTree(responseCreateOrder.getBody());

                return new ResponseObject<>(true, "Quote Generated Successfully", responseData);
            } else {
                return new ResponseObject<>(false, "Failed to Create Quote: " + responseCreateOrder.getStatusCode(), null);
            }
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            e.printStackTrace();
            return new ResponseObject<>(false, "HTTP error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseObject<>(false, "Unexpected error: " + e.getMessage(), null);
        }
    }

    @Transactional
    public ResponseObject<Object> getDeliveryStatus(String id,String API_KEY) {
        String DOORDASH_URL_GET_ORDER_STATUS = "https://openapi.doordash.com/drive/v2/deliveries/"+id;
        try {
            RestTemplate restTemplate = new RestTemplate();
            // Set up headers for API Key
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + API_KEY);

            HttpEntity<String> requestEntityGetDeliveryStatus = new HttpEntity<>(headers);
            ResponseEntity<String> responseEntityGetDeliveryStatus = restTemplate.exchange(DOORDASH_URL_GET_ORDER_STATUS, HttpMethod.GET, requestEntityGetDeliveryStatus, String.class);
            // Send the request
//            ResponseEntity<String> responseCreateOrder = restTemplate.exchange(DOORDASH_URL_GET_CREATE_ORDER, HttpMethod.POST, requestEntityAcceptQuote, String.class);
            // Assuming the response will contain the order ID or success indicator
            if (responseEntityGetDeliveryStatus.getStatusCode().is2xxSuccessful()) {
                JsonNode responseData = objectMapper.readTree(responseEntityGetDeliveryStatus.getBody());

                return new ResponseObject<>(true, "Quote Generated Successfully", responseData);
            } else {
                return new ResponseObject<>(false, "Failed to Create Quote: " + responseEntityGetDeliveryStatus.getStatusCode(), null);
            }
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            e.printStackTrace();
            return new ResponseObject<>(false, "HTTP error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseObject<>(false, "Unexpected error: " + e.getMessage(), null);
        }
    }


//    @Transactional
//    public ResponseObject<Object> updateDelivery(DoorDashQuoteAccept orderRequest, String API_KEY,String external_order_id) {
//        String DOORDASH_URL_ACCEPT_QUOTE = "https://openapi.doordash.com/drive/v2/quotes/"+external_order_id+"/accept";
//        try {
//            RestTemplate restTemplate = new RestTemplate();
//            // Set up headers for API Key
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            headers.set("Authorization", "Bearer " + API_KEY);
//
//            // Set up the request body
//            HttpEntity<DoorDashQuoteAccept> requestEntityAcceptQuote = new HttpEntity<>(orderRequest, headers);
//
//            // Send the request
//            ResponseEntity<String> responseAcceptQuote = restTemplate.exchange(DOORDASH_URL_ACCEPT_QUOTE, HttpMethod.POST, requestEntityAcceptQuote, String.class);
//            // Assuming the response will contain the order ID or success indicator
//            if (responseAcceptQuote.getStatusCode().is2xxSuccessful()) {
//                JsonNode responseData = objectMapper.readTree(responseAcceptQuote.getBody());
//
//                return new ResponseObject<>(true, "Quote Generated Successfully", responseData);
//            } else {
//                return new ResponseObject<>(false, "Failed to Create Quote: " + responseAcceptQuote.getStatusCode(), null);
//            }
//        } catch (HttpClientErrorException | HttpServerErrorException e) {
//            e.printStackTrace();
//            return new ResponseObject<>(false, "HTTP error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString(), null);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseObject<>(false, "Unexpected error: " + e.getMessage(), null);
//        }
//    }

    public ResponseObject<Object> cancelDelivery(String id,String API_KEY) {
        String DOORDASH_URL_GET_ORDER_STATUS = "https://openapi.doordash.com/drive/v2/deliveries/"+id+"/cancel";
        try {
            RestTemplate restTemplate = new RestTemplate();
            // Set up headers for API Key
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + API_KEY);

            HttpEntity<String> requestEntityGetDeliveryStatus = new HttpEntity<>(headers);
            ResponseEntity<String> responseEntityGetDeliveryStatus = restTemplate.exchange(DOORDASH_URL_GET_ORDER_STATUS, HttpMethod.PUT, requestEntityGetDeliveryStatus, String.class);
            // Send the request
//            ResponseEntity<String> responseCreateOrder = restTemplate.exchange(DOORDASH_URL_GET_CREATE_ORDER, HttpMethod.POST, requestEntityAcceptQuote, String.class);
            // Assuming the response will contain the order ID or success indicator
            if (responseEntityGetDeliveryStatus.getStatusCode().is2xxSuccessful()) {
                JsonNode responseData = objectMapper.readTree(responseEntityGetDeliveryStatus.getBody());

                return new ResponseObject<>(true, "Quote Generated Successfully", responseData);
            } else {
                return new ResponseObject<>(false, "Failed to Create Quote: " + responseEntityGetDeliveryStatus.getStatusCode(), null);
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
