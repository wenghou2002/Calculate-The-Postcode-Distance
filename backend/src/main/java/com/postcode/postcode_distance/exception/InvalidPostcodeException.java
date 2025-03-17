package com.postcode.postcode_distance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Exception thrown when postcode format is invalid
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidPostcodeException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public InvalidPostcodeException(String message) {
        super(message);
    }
    
    public InvalidPostcodeException(String message, Throwable cause) {
        super(message, cause);
    }
} 