package com.radient.ftsapp.controller;


import com.radient.ftsapp.model.SMSSendRequest;
import com.radient.ftsapp.service.SMSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vi")
@Slf4j
public class SMSRestController {

    @Autowired
    public SMSService smsService;

    @PostMapping("/processSMS")
    public String processSMS(@RequestBody SMSSendRequest sendRequest) {

        log.info("Process SMS Started sendRequest: %s".formatted(sendRequest.toString()));
        return smsService.sendSMS(sendRequest.getDestinationSMSNumber(),sendRequest.getSenderSMSNumber());
    }
}