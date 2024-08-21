package com.radient.ftsapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users") // Specify the table name if different from the class name
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Adjust generation strategy as per your needs
    private Long id; // Assuming you have an ID field

    private String type; // LinkedIn/Google/FB
    private String role; // FTAdmin/Consumer
    private String userId; // UserID from the authentication method
    private String token; // Token or password
    private LocalDateTime expiryDateTime; // Expiry DateTime

    public User() {
    }
}

