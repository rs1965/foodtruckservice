package com.radient.ftsapp.model;

import lombok.Data;

@Data
public class SMSSendRequest {
    private String destinationSMSNumber;
    private String messageText;
}
