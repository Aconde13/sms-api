package com.example.smsapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SmsServiceImplTest {

    private SmsServiceImpl smsService;

    @BeforeEach
    void setUp() {
        smsService = new SmsServiceImpl();
    }

    @Test
    void shouldNotThrowWhenMessageIsShorterThan160() {
        String phone = "1234567890";
        String message = "This is a short message.";
        assertDoesNotThrow(() -> smsService.sendMessage(phone, message));
    }

    @Test
    void shouldNotThrowWhenMessageIsExactly160Characters() {
        String phone = "1234567890";
        String message = "A".repeat(160);
        assertDoesNotThrow(() -> smsService.sendMessage(phone, message));
    }

    @Test
    void shouldNotThrowWhenMessageIsLongerThan160Characters() {
        String phone = "1234567890";
        String message = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
                + "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. "
                + "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris.";

        assertDoesNotThrow(() -> smsService.sendMessage(phone, message));
    }

    @Test
    void splitMessage_shouldReturnSinglePart_whenMessageIsShort() {
        String message = "Short message under 160 characters.";
        List<String> parts = smsService.splitMessage(message);

        assertEquals(1, parts.size());
        assertEquals(message, parts.get(0));
    }

    @Test
    void splitMessage_shouldReturnMultipleParts_whenMessageIsLong() {
        String message = "A".repeat(400);
        List<String> parts = smsService.splitMessage(message);

        assertTrue(parts.size() > 1);
        for (String part : parts) {
            assertTrue(part.length() <= 160);
        }
    }

}
