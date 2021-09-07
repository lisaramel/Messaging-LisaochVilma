package se.nackademin.messaging.business;

import org.junit.jupiter.api.Test;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.*;

class OpenAccountEventTest {

    @Test
    void serialize() throws JsonProcessingException {
        OpenAccountEvent openAccountEvent = new OpenAccountEvent(123);
        String s = new ObjectMapper().writeValueAsString(openAccountEvent);

        assertTrue(s.contains("123"));
    }
}