package com.example.smsapi.validation;

import com.example.smsapi.dto.SmsRequest;
import com.example.smsapi.exception.InvalidSmsException;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmsValidatorTest {

    @Test
    void shouldNotThrowWhenRequestIsValid() {
        SmsRequest request = new SmsRequest("1234567890", "Valid message.");
        assertDoesNotThrow(() -> SmsValidator.validate(request));
    }

    @Test
    void shouldThrowWhenPhoneNumberIsEmpty() {
        SmsRequest request = new SmsRequest("  ", "Valid message.");
        assertThrows(InvalidSmsException.class, () -> SmsValidator.validate(request));
    }

    @Test
    void shouldThrowWhenMessageIsEmpty() {
        SmsRequest request = new SmsRequest("1234567890", "   ");
        assertThrows(InvalidSmsException.class, () -> SmsValidator.validate(request));
    }

    @Test
    void shouldThrowWhenPhoneNumberIsNull() {
        SmsRequest request = new SmsRequest(null, "Hello");
        assertThrows(InvalidSmsException.class, () -> SmsValidator.validate(request));
    }

    @Test
    void shouldThrowWhenMessageIsNull() {
        SmsRequest request = new SmsRequest("1234567890", null);
        assertThrows(InvalidSmsException.class, () -> SmsValidator.validate(request));
    }

    @Test
    void validate_shouldThrow_whenPhoneIsNotNumeric() {
        SmsRequest request = new SmsRequest("abc123", "Hello");

        InvalidSmsException exception = assertThrows(
                InvalidSmsException.class,
                () -> SmsValidator.validate(request)
        );

        assertEquals("Phone number must be valid (8 to 15 digits, optional +)", exception.getMessage());
    }

}
