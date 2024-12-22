package com.radient.ftsapp.service;

import com.radient.ftsapp.utils.ResponseObject;
import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SMSService {

    @Value("ACae5a803865dba1c1527f9ad65cb3b73c")
    String ACCOUNT_SID;

    @Value("39f077966d5f9c895309becf8556591d")
    String AUTH_TOKEN;

    @Value("+12564149614")
    String OUTGOING_SMS_NUMBER;


    @PostConstruct
    private void setup() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public ResponseObject<Object> sendSMS(String smsNumber, String smsMessage) {
        Message message = Message.creator(
                new PhoneNumber(smsNumber),
                new PhoneNumber(OUTGOING_SMS_NUMBER),
                smsMessage).create();
        if(message.getStatus().toString().equalsIgnoreCase("queued") && message.getErrorCode() == null && message.getErrorMessage() == null)
        {
            return new ResponseObject<>(true, "Success ", message.getStatus().toString());
        }else{
            return new ResponseObject<>(false, "Failed "+message.getErrorMessage()+message.getErrorCode(), null);
        }
    }
}

