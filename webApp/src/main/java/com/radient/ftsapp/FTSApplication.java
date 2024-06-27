package com.radient.ftsapp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@ComponentScan("com.radient")
@EnableJpaRepositories("com.radient.*")
@EntityScan("com.radient.*")
public class FTSApplication {

	public static void main(String[] args) {
		SpringApplication.run(FTSApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
