package com.radient.ftsapp.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Getter
@Setter
@Configuration
public class ApiResponsesConfig {

    private boolean isSuccess;
    private String message;

    // Default constructor
    public ApiResponsesConfig() {
    }

    // Parameterized constructor
    public ApiResponsesConfig(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    // Example of a bean method
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
