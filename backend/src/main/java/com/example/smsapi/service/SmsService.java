package com.example.smsapi.service;

import java.util.List;

public interface SmsService {

    void sendMessage(String phoneNumber, String message);
    List<String> splitMessage(String message);

}
