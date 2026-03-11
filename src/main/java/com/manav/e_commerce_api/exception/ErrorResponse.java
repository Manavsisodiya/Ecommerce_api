package com.manav.e_commerce_api.exception;

import java.time.LocalDateTime;
import java.util.Map;

public class ErrorResponse {
    @SuppressWarnings("FieldMayBeFinal")
    private LocalDateTime timestamp;
    private String message;
    private Map<String, String> details; 

    public ErrorResponse(String message, Map<String, String> details) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.details = details;
    }

    public LocalDateTime getTimestamp() { return timestamp; }
    public String getMessage() { return message; }
    public Map<String, String> getDetails() { return details; }
}