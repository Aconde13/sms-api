package com.example.smsapi.validation;

import com.example.smsapi.dto.SmsRequest;
import com.example.smsapi.exception.InvalidSmsException;

public class SmsValidator {

    public static void validate(SmsRequest request) {
        if (request.getPhone() == null || request.getPhone().trim().isEmpty()) {
            throw new InvalidSmsException("Phone number must not be empty");
        }

        if (!request.getPhone().matches("^\\+?\\d{8,15}$")) {
            throw new InvalidSmsException("Phone number must be valid (8 to 15 digits, optional +)");
        }

        if (request.getMessage() == null || request.getMessage().trim().isEmpty()) {
            throw new InvalidSmsException("Message must not be empty");
        }
    }

}
