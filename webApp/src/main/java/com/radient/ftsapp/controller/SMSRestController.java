package com.radient.ftsapp.controller;


import com.radient.ftsapp.model.SMSSendRequest;
import com.radient.ftsapp.service.SMSService;
import com.radient.ftsapp.utils.ResponseObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vi")
@Slf4j
public class SMSRestController {

    @Autowired
    public SMSService smsService;

    @PostMapping("/processSMS")
    public ResponseEntity<ResponseObject<Object>> processSMS(@RequestBody SMSSendRequest sendRequest) {

//        log.info("Process SMS Started sendRequest: %s".formatted(sendRequest.toString()));
        ResponseObject<Object> responseObject = smsService.sendSMS(sendRequest.getDestinationSMSNumber(),sendRequest.getMessageText());

        if (responseObject.isSuccess()) {
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }
}