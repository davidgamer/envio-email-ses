package com.email.envio.exceptions;

import jakarta.validation.ValidationException;

import java.util.Map;

public class CustomFieldValidationException extends ValidationException {
    private Map<String, String> details;

    public CustomFieldValidationException(String message, Map<String, String> details) {
        super(message);
        this.details = details;
    }


    public Map<String, String> getDetails() {
        return details;
    }

    public void setDetails(Map<String, String> details) {
        this.details = details;
    }
}
