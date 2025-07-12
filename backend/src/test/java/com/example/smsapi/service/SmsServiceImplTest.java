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
    void splitMessage_shouldReturnCorrectParts_whenMessageIsLong() {
        String message = "A".repeat(350);
        SmsServiceImpl service = new SmsServiceImpl();

        List<String> parts = service.splitMessage(message);

        assertEquals(3, parts.size(), "Expected 3 parts for 350 characters");

        for (String part : parts) {
            assertTrue(part.length() <= 160, "Each part should be <= MAX_SMS_LENGTH");
            assertTrue(part.contains(" - Part "), "Each part should include a suffix");
        }

        String lastPart = parts.get(2);
        String expectedSuffix = " - Part 3 of 3";
        assertTrue(lastPart.endsWith(expectedSuffix), "Last part should end with correct suffix");
        assertEquals(72, lastPart.length(), "Last part should be 58 content + 14 suffix = 72");
    }

    @Test
    void splitMessage_shouldHandleMoreThan99Parts() {
        String message = "A".repeat(15_500);
        List<String> parts = smsService.splitMessage(message);

        int totalParts = parts.size();

        assertTrue(totalParts > 99);

        for (String part : parts) {
            assertTrue(part.length() <= 160);
            assertTrue(part.contains(" - Part "));
            assertTrue(part.endsWith(" of " + totalParts));
        }

        String last = parts.get(parts.size() - 1);
        String expectedSuffix = " - Part " + totalParts + " of " + totalParts;
        assertTrue(last.endsWith(expectedSuffix));
    }

}
