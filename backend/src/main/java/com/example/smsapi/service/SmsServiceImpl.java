package com.example.smsapi.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SmsServiceImpl implements SmsService {

    private static final int MAX_SMS_LENGTH = 160;
    private static final String PART_SUFFIX_TEMPLATE = "... - Part %d of %d";

    @Override
    public void sendMessage(String phoneNumber, String message) {
        List<String> parts = splitMessage(message);
        for (String part : parts) {
            System.out.printf("Sending to %s: %s%n", phoneNumber, part);
        }
    }

    @Override
    public List<String> splitMessage(String message) {

        if (message.length() <= MAX_SMS_LENGTH) {
            return List.of(message);
        }

        List<String> parts = new ArrayList<>();
        int start = 0;
        while (start < message.length()) {
            int end = Math.min(start + MAX_SMS_LENGTH, message.length());
            parts.add(message.substring(start, end));
            start = end;
        }

        int totalParts = parts.size();
        List<String> finalMessages = new ArrayList<>();

        for (int i = 0; i < totalParts; i++) {
            String suffix = String.format(PART_SUFFIX_TEMPLATE, i + 1, totalParts);
            String part = parts.get(i);
            finalMessages.add(part + suffix);
        }

        return finalMessages;
    }

}
