package com.example.smsapi.exception;

public class InvalidSmsException extends RuntimeException{
    public InvalidSmsException(String message) {
        super(message);
    }
}
