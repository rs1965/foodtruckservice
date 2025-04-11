package com.radient.ftsapp.service;

import com.radient.ftsapp.model.User;
import com.radient.ftsapp.repository.UserRepository;
import com.radient.ftsapp.utils.ResponseObject;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public ResponseObject<Integer> createUser(User user) {
        Optional<User> existingUserOpt = userRepository.findByUserId(user.getUserId());

        try {

            if (existingUserOpt.isPresent()) {
                // User exists, perform update
                User existingUser = existingUserOpt.get();
                existingUser.setRole(user.getRole());
                existingUser.setExpiryDateTime(user.getExpiryDateTime());
                existingUser.setType(user.getType());
                existingUser.setName(user.getName());
                existingUser.setEmailId(user.getEmailId());
                existingUser.setTimeStamp(LocalDateTime.now());
                userRepository.save(existingUser);

                return new ResponseObject<>(true, "User updated successfully", 1);
            } else {
                // User doesn't exist, perform insertion
                User newUser = new User();
                newUser.setUserId(user.getUserId());
                newUser.setRole(user.getRole());
                newUser.setExpiryDateTime(user.getExpiryDateTime());
                newUser.setType(user.getType());
                newUser.setName(user.getName());
                newUser.setEmailId(user.getEmailId());
                newUser.setTimeStamp(LocalDateTime.now());
                userRepository.save(newUser);

                return new ResponseObject<>(true, "User created successfully", 1);
            }
        } catch (DataIntegrityViolationException e) {
            return new ResponseObject<>(false, "Database integrity violation: " + e.getMessage(), null);
        } catch (ConstraintViolationException e) {
            return new ResponseObject<>(false, "Validation error: " + e.getMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseObject<>(false, "Unexpected error: " + e.getMessage(), null);
        }
    }


    public ResponseObject<User> getUserById(String userId) {
        Optional<User> user = userRepository.findByUserId(userId);
        return user.map(value -> new ResponseObject<>(true, "User found", value)).orElseGet(() -> new ResponseObject<>(false, "User not found", null));
    }
}
