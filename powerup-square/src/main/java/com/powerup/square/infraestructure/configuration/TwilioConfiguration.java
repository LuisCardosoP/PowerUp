package com.powerup.square.infraestructure.configuration;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class TwilioConfiguration {

    @Autowired
    private Environment env;

  // envio de MS
    public void sendSMS(String body) {
        Twilio.init(env.getProperty("twilio.account.sid"), env.getProperty("twilio.auth.token"));
        Message.creator(new PhoneNumber("+573013237026"), new PhoneNumber("+15074897258"), body).create();
    }

}
