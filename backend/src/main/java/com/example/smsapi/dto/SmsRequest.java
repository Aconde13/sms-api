package com.example.smsapi.dto;

public class SmsRequest {

    private String message;

    private String phone;

    public SmsRequest() {
    }

    public SmsRequest(String phone, String message) {
        this.message = message;
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
