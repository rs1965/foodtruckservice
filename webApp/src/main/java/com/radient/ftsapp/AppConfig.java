package com.radient.ftsapp;

import com.radient.ftsapp.model.FoodTruck;
import com.radient.ftsapp.service.FacilitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;
import java.util.List;

@Configuration
public class AppConfig {

    // Define a RestTemplate bean to be used for API calls
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

