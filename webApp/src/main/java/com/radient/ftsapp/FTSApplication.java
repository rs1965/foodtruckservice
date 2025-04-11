package com.radient.ftsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.radient.ftsapp.repository")
@EntityScan(basePackages = "com.radient.ftsapp.model") // Add this line to ensure your entities are scanned
public class FTSApplication {

	public static void main(String[] args) {
		SpringApplication.run(FTSApplication.class, args);
	}
}
