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

        int totalMessageLength = message.length();
        int estimatedParts = (int) Math.ceil((double) totalMessageLength / MAX_SMS_LENGTH);
        int actualParts = 0;
        int contentPerPart = 0;

        while (estimatedParts != actualParts) {
            actualParts = estimatedParts;

            String suffixExample = " - Part " + actualParts + " of " + actualParts;
            int suffixLength = suffixExample.length();

            contentPerPart = MAX_SMS_LENGTH - suffixLength;
            estimatedParts = (int) Math.ceil((double) totalMessageLength / contentPerPart);
        }

        List<String> parts = new ArrayList<>();
        int index = 0;

        for (int i = 1; i <= actualParts; i++) {
            int end = Math.min(index + contentPerPart, message.length());
            String content = message.substring(index, end);
            String suffix = " - Part " + i + " of " + actualParts;
            parts.add(content + suffix);
            index = end;
        }

        return parts;
    }

}
