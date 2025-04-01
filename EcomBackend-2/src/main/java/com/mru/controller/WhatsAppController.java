package com.mru.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import com.twilio.rest.api.v2010.account.Message;

@RestController
@CrossOrigin("*")
public class WhatsAppController {

    @Value("${twilio.accountSid}")
    private String ACCOUNT_SID;

    @Value("${twilio.authToken}")
    private String AUTH_TOKEN;

    @Value("${twilio.whatsappFrom}")
    private String WHATSAPP_FROM;

    @PostMapping("/send-whatsapp")
    public String sendWhatsApp(@RequestParam String mobile, @RequestParam String message) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        
        Message sentMessage = Message.creator(
                new PhoneNumber("whatsapp:+91" + mobile), // Dynamic clearly
                new PhoneNumber("whatsapp:" + WHATSAPP_FROM),
                message
        ).create();

        return sentMessage.getSid();
    }
}

