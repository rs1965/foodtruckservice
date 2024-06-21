package com.radient.ftsapp;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = {"com.radient.ftsapp.repository", "com.radient.ftsapp.service"})
@Configuration
public class AppConfig {

    // Define a RestTemplate bean to be used for API calls
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

