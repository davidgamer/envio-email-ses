package com.email.envio.exceptions;

import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionStatusHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({CustomFieldValidationException.class})
    public ErrorResponse handleCustomFieldException(CustomFieldValidationException ex) {
        return new ErrorResponse(ex.getMessage(), ex.getDetails());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ErrorResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach((error) -> {
                 String fieldName = ((FieldError) error).getField();
                 String errorMessage = error.getDefaultMessage();
                 errors.put(fieldName, errorMessage);
        });

        return new ErrorResponse(ErrorResponse.INVALID_FIELD, errors);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ValidationException.class, IllegalArgumentException.class})
    public ErrorResponse handleFieldExceptions( ValidationException ex) {
        return new ErrorResponse(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    public ErrorResponse  handleUnknown( Exception ex) {
        return new ErrorResponse(ex.getMessage());
    }
}