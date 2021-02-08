package com.iljaust.theapp.util;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


public class Sender {
    @Value("${twilio.account.sid}")
    private  String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    private final String MESSAGE = "Your one time verification password is : ";



    @PostConstruct
    public void twilioInit() {
        Twilio.init(accountSid, authToken);
    }



    public void sendCodeSms(String phoneNumber, String code) {
        Message.creator(
                new PhoneNumber(phoneNumber),
                new PhoneNumber(twilioPhoneNumber),
                MESSAGE + code);
    }

}
