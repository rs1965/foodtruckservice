package com.radient.ftsapp.controller;
import com.radient.ftsapp.service.DoorDashService;
import com.radient.ftsapp.service.LinkedInAuthService;
import com.radient.ftsapp.utils.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LinkedInAuthController {

    @Autowired
    private LinkedInAuthService linkedInAuthService;

    @PostMapping("/linkedin/token")
    public ResponseEntity<ResponseObject<Object>> getAccessToken(@RequestParam("code") String code) {
        ResponseObject<Object> responseObject = linkedInAuthService.linkedInProfileDetails(code);
        if (responseObject.isSuccess()) {
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }
}