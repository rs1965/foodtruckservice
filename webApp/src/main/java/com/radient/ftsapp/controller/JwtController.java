package com.radient.ftsapp.controller;

import com.radient.ftsapp.service.JwtTokenService;
import com.radient.ftsapp.utils.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class JwtController {

    private final JwtTokenService jwtTokenService;

    @Autowired
    public JwtController(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @GetMapping("/generate-token")
    public ResponseEntity<ResponseObject<String>> generateToken() {
        ResponseObject<String> responseObject = jwtTokenService.generateToken();

        if (responseObject.isSuccess()) {
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }
}
