package com.radient.ftsapp.controller;

import com.radient.ftsapp.model.User;
import com.radient.ftsapp.service.UserService;
import com.radient.ftsapp.utils.ResponseObject;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/addUser")
    public ResponseEntity<ResponseObject<Integer>> createUser(@Valid @RequestBody User user) {
        ResponseObject<Integer> responseObject = userService.createUser(user);
        if (responseObject.isSuccess()) {
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/getUser/{userId}")
    public ResponseEntity<ResponseObject<User>> getUser(@PathVariable String userId) {
        ResponseObject<User> responseObject =  userService.getUserById(userId);
        if (responseObject.isSuccess()) {
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(responseObject, HttpStatus.NOT_FOUND);
        }
    }
}
