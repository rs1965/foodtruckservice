package com.radient.ftsapp.controller;

import com.radient.ftsapp.model.User;
import com.radient.ftsapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }

    @GetMapping("/login")
    public String login(@RequestParam("user") String userId, @RequestParam("token") String token) {
        if (userService.isValidToken(userId, token)) {
            return "Valid token";
        } else {
            return "Invalid token or token expired";
        }
    }
}
