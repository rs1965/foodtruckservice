package com.radient.ftsapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @Column(nullable = false)
    @NotNull(message = "Type is required")
    private String type; // LinkedIn/Google/FB

    @Column(nullable = false)
    @NotNull(message = "Role required")
    private String role; // FTAdmin/Consumer

    @Column(nullable = false)
    @NotNull(message = "userId required")
    private String userId; // UserID from the authentication method

    @Column(nullable = false)
    @NotNull(message = "ExpiryDate is required")
    private LocalDateTime expiryDateTime; // Expiry DateTime

    @Column(nullable = false)
    @NotNull(message = "emailId required")
    private String emailId;

    @Column(nullable = false)
    @NotNull(message = "name required")
    private String name;

    private LocalDateTime timeStamp;
    public User() {
    }
}

