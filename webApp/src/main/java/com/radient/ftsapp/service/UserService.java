package com.radient.ftsapp.service;

import com.radient.ftsapp.model.User;
import com.radient.ftsapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public User getUserById(String userId) {
        Optional<User> user = userRepository.findByUserId(userId);
        return user.orElse(null);
    }

    public boolean isValidToken(String userId, String token) {
        User user = getUserById(userId);
        if (user == null) {
            return false;
        }
        return user.getToken().equals(token) && user.getExpiryDateTime().isAfter(LocalDateTime.now());
    }
}
