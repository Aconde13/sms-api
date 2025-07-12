package com.example.smsapi.controller;

import com.example.smsapi.dto.MessageResponse;
import com.example.smsapi.dto.SmsRequest;
import com.example.smsapi.service.SmsService;
import com.example.smsapi.validation.SmsValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/sms")
public class SmsController {

    private final SmsService smsService;

    public SmsController(SmsService smsService) {
        this.smsService = smsService;
    }

    @PostMapping("/send")
    public ResponseEntity<MessageResponse> sendSms(@RequestBody SmsRequest request) {
        SmsValidator.validate(request);
        smsService.sendMessage(request.getPhone(), request.getMessage());
        return ResponseEntity.ok(new MessageResponse("Message sent successfully"));
    }

}
