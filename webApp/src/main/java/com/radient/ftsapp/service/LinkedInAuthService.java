package com.radient.ftsapp.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.radient.ftsapp.utils.ResponseObject;
import jakarta.transaction.Transactional;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Service
public class LinkedInAuthService {
    private final ObjectMapper objectMapper;

    private static final String LINKEDIN_TOKEN_URL = "https://www.linkedin.com/oauth/v2/accessToken";
    private static final String LINKEDIN_USERINFO_URL = "https://api.linkedin.com/v2/userinfo"; // New endpoint for user info
    private static final String CLIENT_ID = "86tgjuy5776q1a"; // Use environment variables
    private static final String CLIENT_SECRET = "6iTDQMRBoQ7LQqq9"; // Use environment variables
    private static final String REDIRECT_URI = "http://localhost:3000/callback";

    public LinkedInAuthService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Retrieves LinkedIn profile details using the provided authorization code.
     *
     * @param code The authorization code obtained from LinkedIn.
     * @return A ResponseObject containing user profile information or an error message.
     */
    @Transactional
    public ResponseObject<Object> linkedInProfileDetails(String code) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            // Set up headers for API Key
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            // Create the body as form parameters
            MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
            requestParams.add("grant_type", "authorization_code");
            requestParams.add("code", code);
            requestParams.add("redirect_uri", REDIRECT_URI);
            requestParams.add("client_id", CLIENT_ID);
            requestParams.add("client_secret", CLIENT_SECRET);

            // Combine the headers and the form data into a request
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestParams, headers);

            // Send POST request to LinkedIn for access token
            ResponseEntity<Map> tokenResponse = restTemplate.exchange(
                    LINKEDIN_TOKEN_URL,
                    HttpMethod.POST,
                    request,
                    Map.class
            );

            // Extract the access token and expiration time from the response
            String accessToken = (String) tokenResponse.getBody().get("access_token");
            Integer expiresIn = (Integer) tokenResponse.getBody().get("expires_in");

            // Fetch user info using the access token
            HttpHeaders userInfoHeaders = new HttpHeaders();
            userInfoHeaders.setBearerAuth(accessToken); // Set Bearer token
            HttpEntity<String> userInfoRequest = new HttpEntity<>(userInfoHeaders);

            // Call the LinkedIn userinfo endpoint
            ResponseEntity<Map> userInfoResponse = restTemplate.exchange(
                    LINKEDIN_USERINFO_URL,
                    HttpMethod.GET,
                    userInfoRequest,
                    Map.class
            );

            // Check if the user info response is successful
            if (userInfoResponse.getStatusCode().is2xxSuccessful()) {
                JsonNode responseData = objectMapper.convertValue(userInfoResponse.getBody(), JsonNode.class);

                // Add expires_in to the user info response directly
                ((ObjectNode) responseData).put("expires_in", expiresIn);

                return new ResponseObject<>(true, "User Info Retrieved Successfully", responseData);
            } else {
                // Return the error message from the LinkedIn user info response
                return new ResponseObject<>(false, "Failed to Retrieve User Info: " + userInfoResponse.getStatusCode(), null);
            }
        } catch (HttpClientErrorException e) {
            // Handle client errors (4xx)
            return new ResponseObject<>(false, "Client error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString(), null);
        } catch (HttpServerErrorException e) {
            // Handle server errors (5xx)
            return new ResponseObject<>(false, "Server error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString(), null);
        } catch (Exception e) {
            // Handle unexpected exceptions
            return new ResponseObject<>(false, "Unexpected error: " + e.getMessage(), null);
        }
    }
}
